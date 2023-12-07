import { PropsWithChildren, useEffect, useState } from 'react';
import {
  AuthContext,
  AuthContextData,
  AuthData,
  LoginParams,
  RegisterParams,
} from '../../contexts/AuthContext/AuthContext';
import { API_URL } from '../../../config/api_key';
import HttpClient, { AxiosClient } from '../../helpers/http/http_client';
import { AuthRepository } from './AuthRepository';
import { LocalStorage } from '../../helpers/local_storage/localStorage';

export const httpClient: HttpClient = new AxiosClient(API_URL);

export const authRepository = new AuthRepository(httpClient, LocalStorage.instance);

function AuthProvider({ children }: PropsWithChildren) {
  const [checking, setChecking] = useState(true);
  const [authData, setAuthData] = useState<AuthData | null>(null);

  async function logIn(params: LoginParams) {
    const loginResponse = await authRepository.login(params);

    setAuthData(loginResponse);
  }

  async function signUp(params: RegisterParams) {
    await authRepository.register(params);

    logIn({ ...params });
  }

  async function logOut() {
    authRepository.deleteLocalAuthData();

    setAuthData(null);
  }

  const data: AuthContextData = {
    authData: authData,
    isSignedIn: authData != null,
    signUp: signUp,
    logIn: logIn,
    logOut: logOut,
  };

  useEffect(() => {
    const exisitingAuthData = authRepository.getLocalAuthData();

    if (exisitingAuthData != null) {
      setChecking(false);
      setAuthData(exisitingAuthData);
    }
  }, []);

  return (
    <AuthContext.Provider value={data}>
      {checking ? <>Carregando</> : children}
    </AuthContext.Provider>
  );
}

export { AuthProvider };
