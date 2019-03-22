import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IGenerator, defaultValue } from 'app/shared/model/generator.model';

export const ACTION_TYPES = {
  FETCH_GENERATOR_LIST: 'generator/FETCH_GENERATOR_LIST',
  FETCH_GENERATOR: 'generator/FETCH_GENERATOR',
  CREATE_GENERATOR: 'generator/CREATE_GENERATOR',
  UPDATE_GENERATOR: 'generator/UPDATE_GENERATOR',
  DELETE_GENERATOR: 'generator/DELETE_GENERATOR',
  RESET: 'generator/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IGenerator>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type GeneratorState = Readonly<typeof initialState>;

// Reducer

export default (state: GeneratorState = initialState, action): GeneratorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_GENERATOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GENERATOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GENERATOR):
    case REQUEST(ACTION_TYPES.UPDATE_GENERATOR):
    case REQUEST(ACTION_TYPES.DELETE_GENERATOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GENERATOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GENERATOR):
    case FAILURE(ACTION_TYPES.CREATE_GENERATOR):
    case FAILURE(ACTION_TYPES.UPDATE_GENERATOR):
    case FAILURE(ACTION_TYPES.DELETE_GENERATOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GENERATOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_GENERATOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GENERATOR):
    case SUCCESS(ACTION_TYPES.UPDATE_GENERATOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GENERATOR):
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

const apiUrl = 'api/generators';

// Actions

export const getEntities: ICrudGetAllAction<IGenerator> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_GENERATOR_LIST,
  payload: axios.get<IGenerator>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IGenerator> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GENERATOR,
    payload: axios.get<IGenerator>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IGenerator> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GENERATOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IGenerator> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GENERATOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGenerator> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GENERATOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
