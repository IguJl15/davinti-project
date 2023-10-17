import { PropsWithChildren, useState } from 'react';
import {
  AuthContext,
  AuthContextData,
  AuthData,
  LoginParams,
  RegisterParams,
} from '../contexts/AuthContext';
import { API_URL } from '../../config/api_key';
import HttpClient, { AxiosClient } from '../http/http_client';
import { AuthRepository } from './AuthRepository';

export const httpClient: HttpClient = new AxiosClient(API_URL);

export const authRepository = new AuthRepository(
  httpClient,
  localStorage.instance
);

function AuthProvider({ children }: PropsWithChildren) {
  const [authData, setAuthData] = useState<AuthData | null>(null);

  async function logIn(params: LoginParams) {
    const loginResponse = await authRepository.login(params);

    data.authData = loginResponse;
    data.isSignedIn = true;

    setAuthData(loginResponse);
  }

  async function register(params: RegisterParams) {
    const registerResponse = await authRepository.register(params);

    data.authData = registerResponse;
    data.isSignedIn = true;

    setAuthData(registerResponse);
  }

  function logOut() {
    authRepository.deleteLocalAuthData();

    setAuthData(null);
  }

  const data: AuthContextData = {
    register: register,
    authData: authData,
    isSignedIn: authData !== null,
    logIn: logIn,
    logOut: logOut,
  };

  return <AuthContext.Provider value={data}>{children}</AuthContext.Provider>;
}

export { AuthProvider };
