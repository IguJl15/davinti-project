import axios, { Axios } from 'axios';
import { API_URL } from '../../config/api_key';

type Params = { [key: string]: any };

export default interface HttpClient {
  baseUrl: string;

  get<T>(path: string, params?: Params): Promise<T>;
  post<T>(path: string, data: any): Promise<T>;
  delete<T>(path: string, data?: any): Promise<T | void>;
  patch<T>(path: string, data: any): Promise<T>;
}

export class AxiosClient implements HttpClient {
  axios!: Axios;

  constructor(public baseUrl: string = API_URL) {
    this.axios = axios.create({ baseURL: baseUrl });
  }

  async get<T>(path: string, params?: Params | undefined): Promise<T> {
    const axiosResponse = await this.axios.get(path, { params: params });

    return axiosResponse.data;
  }

  async post<T>(path: string, data: any): Promise<T> {
    const axiosResponse = await this.axios.post(path, { data: data });

    return axiosResponse.data;
  }

  async delete<T>(path: string, data?: any): Promise<void | T> {
    const axiosResponse = await this.axios.delete(path, { data: data });

    return axiosResponse.data;
  }

  async patch<T>(path: string, data: any): Promise<T> {
    const axiosResponse = await this.axios.patch(path, { data: data });

    return axiosResponse.data
  }
}
