import {
  AuthData,
  LoginParams,
  RegisterParams,
} from "../../contexts/AuthContext/AuthContext";
import HttpClient from "../../helpers/http/http_client";
import { LocalStorage } from "../../helpers/local_storage/localStorage";
import { User } from "../../interfaces/User";

export class AuthRepository {
  constructor(
    private httpClient: HttpClient,
    private localStorage: LocalStorage
  ) {}

  static localStorageKey = "auth_data";

  getLocalAuthData(): AuthData | null {
    const localResponse = this.localStorage.read<AuthData>(
      AuthRepository.localStorageKey
    );

    return localResponse;
  }

  deleteLocalAuthData() {
    this.localStorage.delete(AuthRepository.localStorageKey);
  }

  saveLocalAuthData(authData: AuthData) {
    this.localStorage.save(AuthRepository.localStorageKey, authData);
  }

  // async refreshTokens(): Promise<AuthData> {
  //   const refreshToken = this.getLocalAuthData().refreshToken;

  //   const response = await this.httpClient.post<AuthData>('/auth/refresh', {
  //     refreshToken: refreshToken,
  //   });

  //   this.saveLocalAuthData(response);

  //   return response;
  // }

  async register(data: RegisterParams): Promise<void> {
    await this.httpClient.post("/students", data);
  }

  async login(data: LoginParams): Promise<AuthData> {
    const response = await this.httpClient.post<{
      accessToken: string;
      refreshToken: string;
    }>("/session", data);

    const user: User = await this.httpClient.get<User>(
      "/me",
      {},
      { Authorization: response.accessToken }
    );

    const authData = { user, ...response };
    this.saveLocalAuthData(authData);

    return authData;
  }
}
