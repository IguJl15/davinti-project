import Root from '../App';
import { Navigate, createBrowserRouter } from 'react-router-dom';
import { HomePage } from '../modules/Home/pages/HomePage';
import { LoginPage } from '../modules/Auth/pages/LoginPage';
import { RegisterPage } from '../modules/Auth/pages/RegisterPage';
// import { AnonymusRoute } from './AnonymusRoute';
import { CoursesPage, coursesPageLoader } from '../modules/Courses/pages/CoursesPage';
import { HomeCoursePage } from '../modules/Courses/pages/HomeCoursePage';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    children: [
      {
        index: true,
        element: <Navigate to="/home" />,
      },
      {
        path: 'home',
        element: <HomePage />,
      },
      {
        path: 'register',
        element: <RegisterPage />,
      },
      {
        path: 'login',
        element: <LoginPage />,
      },
      {
        path: 'courses',
        loader: coursesPageLoader,
        element: <CoursesPage />,
      },
      {
        path: 'teste',
        element: <HomeCoursePage />,
      },
    ],
  },
]);
