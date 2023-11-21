import Root from '../App';
import { Navigate, createBrowserRouter } from 'react-router-dom';
import { HomePage } from '../modules/Home/pages/HomePage';
import { LoginPage } from '../modules/Auth/pages/LoginPage';
import { RegisterPage } from '../modules/Auth/pages/RegisterPage';
// import { AnonymusRoute } from './AnonymusRoute';
import { CoursesPage, coursesPageLoader } from '../modules/Courses/pages/CoursesPage';
import { HomeCoursePage, coursePageLoader } from '../modules/Courses/pages/HomeCoursePage';
import { AnonymusRoute } from './AnonymusRoute';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    children: [
      {
        index: true,
        element: <Navigate to="/courses" />,
      },
      {
        path: 'home',
        element: <HomePage />,
      },
      {
        path: 'register',
        element: (
          <AnonymusRoute>
            <RegisterPage />
          </AnonymusRoute>
        ),
      },
      {
        path: 'login',
        element: (
          <AnonymusRoute>
            <LoginPage />
          </AnonymusRoute>
        ),
      },
      {
        path: 'courses',
        loader: coursesPageLoader,
        element: <CoursesPage />,
      },
      {
        path: 'courses/:courseId',
        loader: coursePageLoader,
        element: <HomeCoursePage />,
      },
    ],
  },
]);
