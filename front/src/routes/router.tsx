import { createBrowserRouter } from 'react-router-dom';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';
import { RegisterPage } from '../pages/RegisterPage';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <HomePage />,
    children: [],
  },
  {
    path: 'register',
    element: <RegisterPage />,
  },
  {
     path: 'login',
     element: <LoginPage />,
  },
]);
