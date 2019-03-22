import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPropriedade, defaultValue } from 'app/shared/model/propriedade.model';

export const ACTION_TYPES = {
  FETCH_PROPRIEDADE_LIST: 'propriedade/FETCH_PROPRIEDADE_LIST',
  FETCH_PROPRIEDADE: 'propriedade/FETCH_PROPRIEDADE',
  CREATE_PROPRIEDADE: 'propriedade/CREATE_PROPRIEDADE',
  UPDATE_PROPRIEDADE: 'propriedade/UPDATE_PROPRIEDADE',
  DELETE_PROPRIEDADE: 'propriedade/DELETE_PROPRIEDADE',
  RESET: 'propriedade/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPropriedade>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PropriedadeState = Readonly<typeof initialState>;

// Reducer

export default (state: PropriedadeState = initialState, action): PropriedadeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROPRIEDADE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROPRIEDADE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROPRIEDADE):
    case REQUEST(ACTION_TYPES.UPDATE_PROPRIEDADE):
    case REQUEST(ACTION_TYPES.DELETE_PROPRIEDADE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROPRIEDADE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROPRIEDADE):
    case FAILURE(ACTION_TYPES.CREATE_PROPRIEDADE):
    case FAILURE(ACTION_TYPES.UPDATE_PROPRIEDADE):
    case FAILURE(ACTION_TYPES.DELETE_PROPRIEDADE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPRIEDADE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPRIEDADE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROPRIEDADE):
    case SUCCESS(ACTION_TYPES.UPDATE_PROPRIEDADE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROPRIEDADE):
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

const apiUrl = 'api/propriedades';

// Actions

export const getEntities: ICrudGetAllAction<IPropriedade> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROPRIEDADE_LIST,
  payload: axios.get<IPropriedade>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPropriedade> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROPRIEDADE,
    payload: axios.get<IPropriedade>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPropriedade> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROPRIEDADE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPropriedade> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROPRIEDADE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPropriedade> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROPRIEDADE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
