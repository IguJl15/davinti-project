import { AuthData, RegisterParams, LoginParams } from '../contexts/AuthContext';
import HttpClient from '../http/http_client';

export class AuthRepository {
  constructor(
    private httpClient: HttpClient,
    private localStorage: LocalStorage
  ) {}

  static localStorageKey = 'auth_data';

  getLocalAuthData(): AuthData {
    const localResponse = this.localStorage.read<AuthData>(
      AuthRepository.localStorageKey
    );

    return localResponse as AuthData;
  }

  deleteLocalAuthData() {
    this.localStorage.delete(AuthRepository.localStorageKey);
  }

  saveLocalAuthData(authData: AuthData) {
    this.localStorage.save(AuthRepository.localStorageKey, authData);
  }

  async refreshTokens(): Promise<AuthData> {
    const refreshToken = this.getLocalAuthData().refreshToken;

    const response = await this.httpClient.post<AuthData>('/auth/refresh', {
      refreshToken: refreshToken,
    });

    this.saveLocalAuthData(response);

    return response;
  }

  async register(data: RegisterParams): Promise<AuthData> {
    const response = await this.httpClient.post<AuthData>(
      '/auth/register',
      data
    );

    this.saveLocalAuthData(response);

    return response;
  }

  async login(data: LoginParams): Promise<AuthData> {
    const response = await this.httpClient.post<AuthData>('/auth/login', data);
    this.saveLocalAuthData(response);

    return response;
  }
}
