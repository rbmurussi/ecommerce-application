import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIcms, defaultValue } from 'app/shared/model/icms.model';

export const ACTION_TYPES = {
  FETCH_ICMS_LIST: 'icms/FETCH_ICMS_LIST',
  FETCH_ICMS: 'icms/FETCH_ICMS',
  CREATE_ICMS: 'icms/CREATE_ICMS',
  UPDATE_ICMS: 'icms/UPDATE_ICMS',
  DELETE_ICMS: 'icms/DELETE_ICMS',
  RESET: 'icms/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIcms>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type IcmsState = Readonly<typeof initialState>;

// Reducer

export default (state: IcmsState = initialState, action): IcmsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ICMS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ICMS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ICMS):
    case REQUEST(ACTION_TYPES.UPDATE_ICMS):
    case REQUEST(ACTION_TYPES.DELETE_ICMS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ICMS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ICMS):
    case FAILURE(ACTION_TYPES.CREATE_ICMS):
    case FAILURE(ACTION_TYPES.UPDATE_ICMS):
    case FAILURE(ACTION_TYPES.DELETE_ICMS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ICMS_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ICMS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ICMS):
    case SUCCESS(ACTION_TYPES.UPDATE_ICMS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ICMS):
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

const apiUrl = 'api/icms';

// Actions

export const getEntities: ICrudGetAllAction<IIcms> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ICMS_LIST,
    payload: axios.get<IIcms>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IIcms> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ICMS,
    payload: axios.get<IIcms>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIcms> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ICMS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IIcms> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ICMS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIcms> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ICMS,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
