import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICancelamento, defaultValue } from 'app/shared/model/cancelamento.model';

export const ACTION_TYPES = {
  FETCH_CANCELAMENTO_LIST: 'cancelamento/FETCH_CANCELAMENTO_LIST',
  FETCH_CANCELAMENTO: 'cancelamento/FETCH_CANCELAMENTO',
  CREATE_CANCELAMENTO: 'cancelamento/CREATE_CANCELAMENTO',
  UPDATE_CANCELAMENTO: 'cancelamento/UPDATE_CANCELAMENTO',
  DELETE_CANCELAMENTO: 'cancelamento/DELETE_CANCELAMENTO',
  SET_BLOB: 'cancelamento/SET_BLOB',
  RESET: 'cancelamento/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICancelamento>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CancelamentoState = Readonly<typeof initialState>;

// Reducer

export default (state: CancelamentoState = initialState, action): CancelamentoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CANCELAMENTO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CANCELAMENTO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CANCELAMENTO):
    case REQUEST(ACTION_TYPES.UPDATE_CANCELAMENTO):
    case REQUEST(ACTION_TYPES.DELETE_CANCELAMENTO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CANCELAMENTO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CANCELAMENTO):
    case FAILURE(ACTION_TYPES.CREATE_CANCELAMENTO):
    case FAILURE(ACTION_TYPES.UPDATE_CANCELAMENTO):
    case FAILURE(ACTION_TYPES.DELETE_CANCELAMENTO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CANCELAMENTO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CANCELAMENTO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CANCELAMENTO):
    case SUCCESS(ACTION_TYPES.UPDATE_CANCELAMENTO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CANCELAMENTO):
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

const apiUrl = 'api/cancelamentos';

// Actions

export const getEntities: ICrudGetAllAction<ICancelamento> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CANCELAMENTO_LIST,
  payload: axios.get<ICancelamento>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICancelamento> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CANCELAMENTO,
    payload: axios.get<ICancelamento>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICancelamento> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CANCELAMENTO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICancelamento> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CANCELAMENTO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICancelamento> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CANCELAMENTO,
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
