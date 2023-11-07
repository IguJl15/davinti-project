import {
  AuthData,
  RegisterParams,
  LoginParams,
} from "../../contexts/AuthContext/AuthContext";
import { JwtPayload, jwtDecode } from "jwt-decode";
import HttpClient from "../../helpers/http/http_client";
import { User } from "../../interfaces/User";
import { parseRole } from "../../interfaces/Role";
import { LocalStorage } from "../../helpers/local_storage/localStorage";

interface FullJwtPayload extends JwtPayload {
  name: string;
  email: string;
  roles: string;
}

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
    await this.httpClient.post("/users", data);
  }

  async login(data: LoginParams): Promise<AuthData> {
    console.table(data);
    const response = await this.httpClient.post<{ accessToken: string }>(
      "/session",
      data
    );

    const payload = jwtDecode<FullJwtPayload>(response.accessToken);
    const userData: User = {
      id: payload.sub!,
      email: payload.email,
      name: payload.name,
      role: parseRole(payload.roles),
      password: "",
    };
    const authData = { user: userData, accessToken: response.accessToken };
    this.saveLocalAuthData(authData);

    return authData;
  }
}
