import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITransportadora, defaultValue } from 'app/shared/model/transportadora.model';

export const ACTION_TYPES = {
  FETCH_TRANSPORTADORA_LIST: 'transportadora/FETCH_TRANSPORTADORA_LIST',
  FETCH_TRANSPORTADORA: 'transportadora/FETCH_TRANSPORTADORA',
  CREATE_TRANSPORTADORA: 'transportadora/CREATE_TRANSPORTADORA',
  UPDATE_TRANSPORTADORA: 'transportadora/UPDATE_TRANSPORTADORA',
  DELETE_TRANSPORTADORA: 'transportadora/DELETE_TRANSPORTADORA',
  RESET: 'transportadora/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITransportadora>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TransportadoraState = Readonly<typeof initialState>;

// Reducer

export default (state: TransportadoraState = initialState, action): TransportadoraState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRANSPORTADORA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRANSPORTADORA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRANSPORTADORA):
    case REQUEST(ACTION_TYPES.UPDATE_TRANSPORTADORA):
    case REQUEST(ACTION_TYPES.DELETE_TRANSPORTADORA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TRANSPORTADORA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRANSPORTADORA):
    case FAILURE(ACTION_TYPES.CREATE_TRANSPORTADORA):
    case FAILURE(ACTION_TYPES.UPDATE_TRANSPORTADORA):
    case FAILURE(ACTION_TYPES.DELETE_TRANSPORTADORA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSPORTADORA_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSPORTADORA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRANSPORTADORA):
    case SUCCESS(ACTION_TYPES.UPDATE_TRANSPORTADORA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRANSPORTADORA):
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

const apiUrl = 'api/transportadoras';

// Actions

export const getEntities: ICrudGetAllAction<ITransportadora> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TRANSPORTADORA_LIST,
    payload: axios.get<ITransportadora>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITransportadora> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRANSPORTADORA,
    payload: axios.get<ITransportadora>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITransportadora> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRANSPORTADORA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITransportadora> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRANSPORTADORA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITransportadora> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRANSPORTADORA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
