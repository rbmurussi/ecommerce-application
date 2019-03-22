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

import { IIpi, defaultValue } from 'app/shared/model/ipi.model';

export const ACTION_TYPES = {
  FETCH_IPI_LIST: 'ipi/FETCH_IPI_LIST',
  FETCH_IPI: 'ipi/FETCH_IPI',
  CREATE_IPI: 'ipi/CREATE_IPI',
  UPDATE_IPI: 'ipi/UPDATE_IPI',
  DELETE_IPI: 'ipi/DELETE_IPI',
  RESET: 'ipi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIpi>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type IpiState = Readonly<typeof initialState>;

// Reducer

export default (state: IpiState = initialState, action): IpiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IPI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IPI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IPI):
    case REQUEST(ACTION_TYPES.UPDATE_IPI):
    case REQUEST(ACTION_TYPES.DELETE_IPI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_IPI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IPI):
    case FAILURE(ACTION_TYPES.CREATE_IPI):
    case FAILURE(ACTION_TYPES.UPDATE_IPI):
    case FAILURE(ACTION_TYPES.DELETE_IPI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_IPI_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_IPI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IPI):
    case SUCCESS(ACTION_TYPES.UPDATE_IPI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IPI):
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

const apiUrl = 'api/ipis';

// Actions

export const getEntities: ICrudGetAllAction<IIpi> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_IPI_LIST,
    payload: axios.get<IIpi>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IIpi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IPI,
    payload: axios.get<IIpi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIpi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IPI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IIpi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IPI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIpi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IPI,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
