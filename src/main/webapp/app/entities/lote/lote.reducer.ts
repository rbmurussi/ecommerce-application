import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILote, defaultValue } from 'app/shared/model/lote.model';

export const ACTION_TYPES = {
  FETCH_LOTE_LIST: 'lote/FETCH_LOTE_LIST',
  FETCH_LOTE: 'lote/FETCH_LOTE',
  CREATE_LOTE: 'lote/CREATE_LOTE',
  UPDATE_LOTE: 'lote/UPDATE_LOTE',
  DELETE_LOTE: 'lote/DELETE_LOTE',
  SET_BLOB: 'lote/SET_BLOB',
  RESET: 'lote/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILote>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LoteState = Readonly<typeof initialState>;

// Reducer

export default (state: LoteState = initialState, action): LoteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOTE):
    case REQUEST(ACTION_TYPES.UPDATE_LOTE):
    case REQUEST(ACTION_TYPES.DELETE_LOTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOTE):
    case FAILURE(ACTION_TYPES.CREATE_LOTE):
    case FAILURE(ACTION_TYPES.UPDATE_LOTE):
    case FAILURE(ACTION_TYPES.DELETE_LOTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOTE):
    case SUCCESS(ACTION_TYPES.UPDATE_LOTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOTE):
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

const apiUrl = 'api/lotes';

// Actions

export const getEntities: ICrudGetAllAction<ILote> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LOTE_LIST,
  payload: axios.get<ILote>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILote> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOTE,
    payload: axios.get<ILote>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILote> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILote> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILote> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOTE,
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
