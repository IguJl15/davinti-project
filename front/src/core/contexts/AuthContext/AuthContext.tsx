import { createContext } from "react";
import { User } from "../../interfaces/User";

export interface AuthData {
  accessToken: string;
  refreshToken: string;
  user?: User;
}

export interface RegisterParams {
  name: string;
  email: string;
  password: string;
}

export interface LoginParams {
  email: string;
  password: string;
}

export interface AuthContextData {
  isSignedIn: boolean;
  authData: AuthData | null;
  signUp: (params: RegisterParams) => Promise<void> | void;
  logIn: (params: LoginParams) => Promise<void> | void;
  logOut: () => Promise<void> | void;
}

export const AuthContext = createContext({} as AuthContextData);
