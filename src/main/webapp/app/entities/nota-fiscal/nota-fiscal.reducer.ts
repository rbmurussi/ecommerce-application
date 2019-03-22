import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INotaFiscal, defaultValue } from 'app/shared/model/nota-fiscal.model';

export const ACTION_TYPES = {
  FETCH_NOTAFISCAL_LIST: 'notaFiscal/FETCH_NOTAFISCAL_LIST',
  FETCH_NOTAFISCAL: 'notaFiscal/FETCH_NOTAFISCAL',
  CREATE_NOTAFISCAL: 'notaFiscal/CREATE_NOTAFISCAL',
  UPDATE_NOTAFISCAL: 'notaFiscal/UPDATE_NOTAFISCAL',
  DELETE_NOTAFISCAL: 'notaFiscal/DELETE_NOTAFISCAL',
  SET_BLOB: 'notaFiscal/SET_BLOB',
  RESET: 'notaFiscal/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INotaFiscal>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type NotaFiscalState = Readonly<typeof initialState>;

// Reducer

export default (state: NotaFiscalState = initialState, action): NotaFiscalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NOTAFISCAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NOTAFISCAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NOTAFISCAL):
    case REQUEST(ACTION_TYPES.UPDATE_NOTAFISCAL):
    case REQUEST(ACTION_TYPES.DELETE_NOTAFISCAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NOTAFISCAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NOTAFISCAL):
    case FAILURE(ACTION_TYPES.CREATE_NOTAFISCAL):
    case FAILURE(ACTION_TYPES.UPDATE_NOTAFISCAL):
    case FAILURE(ACTION_TYPES.DELETE_NOTAFISCAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOTAFISCAL_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOTAFISCAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NOTAFISCAL):
    case SUCCESS(ACTION_TYPES.UPDATE_NOTAFISCAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NOTAFISCAL):
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

const apiUrl = 'api/nota-fiscals';

// Actions

export const getEntities: ICrudGetAllAction<INotaFiscal> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NOTAFISCAL_LIST,
    payload: axios.get<INotaFiscal>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<INotaFiscal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NOTAFISCAL,
    payload: axios.get<INotaFiscal>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INotaFiscal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NOTAFISCAL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INotaFiscal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NOTAFISCAL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INotaFiscal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NOTAFISCAL,
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
