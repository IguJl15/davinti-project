import axios, { Axios, InternalAxiosRequestConfig } from "axios";
import { API_URL } from "../../../config/api_key";
import { AuthData } from "../../contexts/AuthContext/AuthContext";
import { AuthRepository } from "../../providers/AuthProvider/AuthRepository";
import { LocalStorage } from "../local_storage/localStorage";

type Params = { [key: string]: unknown };
type Headers = { [key: string]: string };

export default interface HttpClient {
  baseUrl: string;

  get<T>(path: string, params?: Params, headers?: Headers): Promise<T>;
  post<T>(path: string, data: unknown): Promise<T>;
  delete<T>(path: string, data?: unknown): Promise<T | void>;
  patch<T>(path: string, data: unknown): Promise<T>;
}

export class AxiosClient implements HttpClient {
  axios!: Axios;

  constructor(public baseUrl: string = API_URL) {
    this.axios = axios.create({ baseURL: baseUrl });

    this.axios.interceptors.request.use(this.addAccessTokenHeader);
  }

  addAccessTokenHeader(config: InternalAxiosRequestConfig) {
    const localResponse = LocalStorage.instance.read<AuthData>(
      AuthRepository.localStorageKey
    );

    if (localResponse?.accessToken && config) {
      config.headers["Authorization"] = localResponse.accessToken;
    }

    return config;
  }

  async get<T>(
    path: string,
    params?: Params | undefined,
    headers?: Headers
  ): Promise<T> {
    const axiosResponse = await this.axios.get(path, {
      params: params,
      headers: headers,
    });

    return axiosResponse.data;
  }

  async post<T>(path: string, data: unknown): Promise<T> {
    const axiosResponse = await this.axios.post(path, data);

    return axiosResponse.data;
  }

  async delete<T>(path: string, data?: unknown): Promise<void | T> {
    const axiosResponse = await this.axios.delete(path, { data: data });

    return axiosResponse.data;
  }

  async patch<T>(path: string, data: unknown): Promise<T> {
    const axiosResponse = await this.axios.patch(path, { data: data });

    return axiosResponse.data;
  }
}
