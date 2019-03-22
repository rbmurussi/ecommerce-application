import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEmitente, defaultValue } from 'app/shared/model/emitente.model';

export const ACTION_TYPES = {
  FETCH_EMITENTE_LIST: 'emitente/FETCH_EMITENTE_LIST',
  FETCH_EMITENTE: 'emitente/FETCH_EMITENTE',
  CREATE_EMITENTE: 'emitente/CREATE_EMITENTE',
  UPDATE_EMITENTE: 'emitente/UPDATE_EMITENTE',
  DELETE_EMITENTE: 'emitente/DELETE_EMITENTE',
  SET_BLOB: 'emitente/SET_BLOB',
  RESET: 'emitente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEmitente>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EmitenteState = Readonly<typeof initialState>;

// Reducer

export default (state: EmitenteState = initialState, action): EmitenteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EMITENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EMITENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EMITENTE):
    case REQUEST(ACTION_TYPES.UPDATE_EMITENTE):
    case REQUEST(ACTION_TYPES.DELETE_EMITENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EMITENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EMITENTE):
    case FAILURE(ACTION_TYPES.CREATE_EMITENTE):
    case FAILURE(ACTION_TYPES.UPDATE_EMITENTE):
    case FAILURE(ACTION_TYPES.DELETE_EMITENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EMITENTE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EMITENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EMITENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_EMITENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EMITENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.SET_BLOB:
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType
        }
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/emitentes';

// Actions

export const getEntities: ICrudGetAllAction<IEmitente> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_EMITENTE_LIST,
    payload: axios.get<IEmitente>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEmitente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EMITENTE,
    payload: axios.get<IEmitente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEmitente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EMITENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEmitente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EMITENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEmitente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EMITENTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType
  }
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
