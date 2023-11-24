import Root from '../App';
import { createBrowserRouter } from 'react-router-dom';
import { HomePage } from '../modules/Home/pages/HomePage';
import { LoginPage } from '../modules/Auth/pages/LoginPage';
import { RegisterPage } from '../modules/Auth/pages/RegisterPage';
import { AnonymusRoute } from './AnonymusRoute';
import { CoursesPage, coursesPageLoader } from '../modules/Courses/pages/CoursesPage';
import { HomeCoursePage, coursePageLoader } from '../modules/Courses/pages/HomeCoursePage';
import { CoursesList } from '../modules/Instructor/pages/CoursesList';
import { CourseClass } from '../modules/Courses/pages/CourseClass';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    children: [
      {
        path: 'lessons',
        element: <CourseClass />,
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
      {
        path: 'instructor',
        element: <CoursesList />,
      },
    ],
  },
]);
