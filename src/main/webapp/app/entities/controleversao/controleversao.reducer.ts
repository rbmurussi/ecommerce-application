import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IControleversao, defaultValue } from 'app/shared/model/controleversao.model';

export const ACTION_TYPES = {
  FETCH_CONTROLEVERSAO_LIST: 'controleversao/FETCH_CONTROLEVERSAO_LIST',
  FETCH_CONTROLEVERSAO: 'controleversao/FETCH_CONTROLEVERSAO',
  CREATE_CONTROLEVERSAO: 'controleversao/CREATE_CONTROLEVERSAO',
  UPDATE_CONTROLEVERSAO: 'controleversao/UPDATE_CONTROLEVERSAO',
  DELETE_CONTROLEVERSAO: 'controleversao/DELETE_CONTROLEVERSAO',
  RESET: 'controleversao/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IControleversao>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ControleversaoState = Readonly<typeof initialState>;

// Reducer

export default (state: ControleversaoState = initialState, action): ControleversaoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CONTROLEVERSAO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CONTROLEVERSAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CONTROLEVERSAO):
    case REQUEST(ACTION_TYPES.UPDATE_CONTROLEVERSAO):
    case REQUEST(ACTION_TYPES.DELETE_CONTROLEVERSAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CONTROLEVERSAO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CONTROLEVERSAO):
    case FAILURE(ACTION_TYPES.CREATE_CONTROLEVERSAO):
    case FAILURE(ACTION_TYPES.UPDATE_CONTROLEVERSAO):
    case FAILURE(ACTION_TYPES.DELETE_CONTROLEVERSAO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONTROLEVERSAO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONTROLEVERSAO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CONTROLEVERSAO):
    case SUCCESS(ACTION_TYPES.UPDATE_CONTROLEVERSAO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CONTROLEVERSAO):
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

const apiUrl = 'api/controleversaos';

// Actions

export const getEntities: ICrudGetAllAction<IControleversao> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CONTROLEVERSAO_LIST,
  payload: axios.get<IControleversao>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IControleversao> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CONTROLEVERSAO,
    payload: axios.get<IControleversao>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IControleversao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CONTROLEVERSAO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IControleversao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CONTROLEVERSAO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IControleversao> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CONTROLEVERSAO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
