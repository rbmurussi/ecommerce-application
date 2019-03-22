import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInutilizacao, defaultValue } from 'app/shared/model/inutilizacao.model';

export const ACTION_TYPES = {
  FETCH_INUTILIZACAO_LIST: 'inutilizacao/FETCH_INUTILIZACAO_LIST',
  FETCH_INUTILIZACAO: 'inutilizacao/FETCH_INUTILIZACAO',
  CREATE_INUTILIZACAO: 'inutilizacao/CREATE_INUTILIZACAO',
  UPDATE_INUTILIZACAO: 'inutilizacao/UPDATE_INUTILIZACAO',
  DELETE_INUTILIZACAO: 'inutilizacao/DELETE_INUTILIZACAO',
  SET_BLOB: 'inutilizacao/SET_BLOB',
  RESET: 'inutilizacao/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInutilizacao>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InutilizacaoState = Readonly<typeof initialState>;

// Reducer

export default (state: InutilizacaoState = initialState, action): InutilizacaoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INUTILIZACAO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INUTILIZACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INUTILIZACAO):
    case REQUEST(ACTION_TYPES.UPDATE_INUTILIZACAO):
    case REQUEST(ACTION_TYPES.DELETE_INUTILIZACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INUTILIZACAO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INUTILIZACAO):
    case FAILURE(ACTION_TYPES.CREATE_INUTILIZACAO):
    case FAILURE(ACTION_TYPES.UPDATE_INUTILIZACAO):
    case FAILURE(ACTION_TYPES.DELETE_INUTILIZACAO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INUTILIZACAO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INUTILIZACAO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INUTILIZACAO):
    case SUCCESS(ACTION_TYPES.UPDATE_INUTILIZACAO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INUTILIZACAO):
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

const apiUrl = 'api/inutilizacaos';

// Actions

export const getEntities: ICrudGetAllAction<IInutilizacao> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INUTILIZACAO_LIST,
  payload: axios.get<IInutilizacao>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInutilizacao> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INUTILIZACAO,
    payload: axios.get<IInutilizacao>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInutilizacao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INUTILIZACAO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInutilizacao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INUTILIZACAO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInutilizacao> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INUTILIZACAO,
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
