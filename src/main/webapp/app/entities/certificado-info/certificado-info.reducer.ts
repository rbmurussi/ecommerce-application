import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICertificadoInfo, defaultValue } from 'app/shared/model/certificado-info.model';

export const ACTION_TYPES = {
  FETCH_CERTIFICADOINFO_LIST: 'certificadoInfo/FETCH_CERTIFICADOINFO_LIST',
  FETCH_CERTIFICADOINFO: 'certificadoInfo/FETCH_CERTIFICADOINFO',
  CREATE_CERTIFICADOINFO: 'certificadoInfo/CREATE_CERTIFICADOINFO',
  UPDATE_CERTIFICADOINFO: 'certificadoInfo/UPDATE_CERTIFICADOINFO',
  DELETE_CERTIFICADOINFO: 'certificadoInfo/DELETE_CERTIFICADOINFO',
  RESET: 'certificadoInfo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICertificadoInfo>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CertificadoInfoState = Readonly<typeof initialState>;

// Reducer

export default (state: CertificadoInfoState = initialState, action): CertificadoInfoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CERTIFICADOINFO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CERTIFICADOINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CERTIFICADOINFO):
    case REQUEST(ACTION_TYPES.UPDATE_CERTIFICADOINFO):
    case REQUEST(ACTION_TYPES.DELETE_CERTIFICADOINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CERTIFICADOINFO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CERTIFICADOINFO):
    case FAILURE(ACTION_TYPES.CREATE_CERTIFICADOINFO):
    case FAILURE(ACTION_TYPES.UPDATE_CERTIFICADOINFO):
    case FAILURE(ACTION_TYPES.DELETE_CERTIFICADOINFO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CERTIFICADOINFO_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CERTIFICADOINFO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CERTIFICADOINFO):
    case SUCCESS(ACTION_TYPES.UPDATE_CERTIFICADOINFO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CERTIFICADOINFO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/certificado-infos';

// Actions

export const getEntities: ICrudGetAllAction<ICertificadoInfo> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CERTIFICADOINFO_LIST,
    payload: axios.get<ICertificadoInfo>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICertificadoInfo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CERTIFICADOINFO,
    payload: axios.get<ICertificadoInfo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICertificadoInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CERTIFICADOINFO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICertificadoInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CERTIFICADOINFO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICertificadoInfo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CERTIFICADOINFO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
