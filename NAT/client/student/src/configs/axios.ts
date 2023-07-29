import axios from "axios";
import keys from "configs/keys";
import { HOST_URL } from "configs/api";

export const $axios = ({ multipart = false }: { multipart?: boolean } = {}) => {
  const instance = axios.create({
    baseURL: HOST_URL,
    headers: {
      "Content-Type": multipart ? "multipart/form-data" : "application/json",
      Accept: "*/*",
    },
  });

  instance.interceptors.request.use(async (config) => {
    // AxiosRequestConfig 型指定を削除
    if (typeof window !== "undefined") {
      if (window.localStorage.getItem(keys.accessToken) !== null) {
        const token = window.localStorage.getItem(keys.accessToken);
        // @ts-ignore
        config.headers.common["Authorization"] = `Bearer ${token}`;
      }
    }
    return config;
  });

  instance.interceptors.response.use(
    (response) => response,
    (error) => {
      return Promise.reject(error);
    }
  );

  return instance;
};
