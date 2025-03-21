import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';
import { ApiResponse, PagedResponse } from '../types/api';

// Create axios instance with default config
const apiClient = axios.create({
  baseURL: process.env.REACT_APP_API_URL || 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add request interceptor for auth token
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Add response interceptor for error handling
apiClient.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    // Handle 401 Unauthorized errors (redirect to login)
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

// Generic GET request
export const get = async <T>(
  url: string,
  config?: AxiosRequestConfig
): Promise<T> => {
  try {
    const response: AxiosResponse<ApiResponse<T>> = await apiClient.get(url, config);
    return response.data.data;
  } catch (error) {
    console.error(`Error fetching data from ${url}:`, error);
    throw error;
  }
};

// Generic POST request
export const post = async <T, D>(
  url: string,
  data: D,
  config?: AxiosRequestConfig
): Promise<T> => {
  try {
    const response: AxiosResponse<ApiResponse<T>> = await apiClient.post(url, data, config);
    return response.data.data;
  } catch (error) {
    console.error(`Error posting data to ${url}:`, error);
    throw error;
  }
};

// Generic PUT request
export const put = async <T, D>(
  url: string,
  data: D,
  config?: AxiosRequestConfig
): Promise<T> => {
  try {
    const response: AxiosResponse<ApiResponse<T>> = await apiClient.put(url, data, config);
    return response.data.data;
  } catch (error) {
    console.error(`Error updating data at ${url}:`, error);
    throw error;
  }
};

// Generic DELETE request
export const del = async <T>(
  url: string,
  config?: AxiosRequestConfig
): Promise<T> => {
  try {
    const response: AxiosResponse<ApiResponse<T>> = await apiClient.delete(url, config);
    return response.data.data;
  } catch (error) {
    console.error(`Error deleting data at ${url}:`, error);
    throw error;
  }
};

// Get paginated results
export const getPaginated = async <T>(
  url: string,
  page: number = 0,
  size: number = 10,
  sort?: string
): Promise<PagedResponse<T>> => {
  const params: Record<string, string | number> = { page, size };
  if (sort) {
    params.sort = sort;
  }

  try {
    const response: AxiosResponse<ApiResponse<PagedResponse<T>>> = await apiClient.get(url, { params });
    return response.data.data;
  } catch (error) {
    console.error(`Error fetching paginated data from ${url}:`, error);
    throw error;
  }
};

// Upload file(s)
export const uploadFile = async <T>(
  url: string,
  files: File | File[],
  additionalData?: Record<string, any>
): Promise<T> => {
  const formData = new FormData();
  
  // Add file(s) to form data
  if (Array.isArray(files)) {
    files.forEach((file) => formData.append('files', file));
  } else {
    formData.append('file', files);
  }
  
  // Add additional data to form data
  if (additionalData) {
    Object.entries(additionalData).forEach(([key, value]) => {
      formData.append(key, value);
    });
  }
  
  try {
    const response: AxiosResponse<ApiResponse<T>> = await apiClient.post(url, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data.data;
  } catch (error) {
    console.error(`Error uploading file(s) to ${url}:`, error);
    throw error;
  }
};

export default {
  get,
  post,
  put,
  del,
  getPaginated,
  uploadFile,
}; 