import Root from '../App';
import { createBrowserRouter } from 'react-router-dom';
import { HomePage } from '../modules/Home/pages/HomePage';
import { LoginPage } from '../modules/Auth/pages/LoginPage';
import { RegisterPage } from '../modules/Auth/pages/RegisterPage';
import { AnonymusRoute } from './AnonymusRoute';
import { CoursesPage, coursesPageLoader } from '../modules/Courses/pages/CoursesPage';
import { CourseHomePage, coursePageLoader } from '../modules/Courses/pages/CoursesHomePage';
import { StudentCourses } from '../modules/Courses/pages/StudentCourses';
import { CoursesList } from '../modules/Instructor/pages/CoursesList';
import { CourseClass, courseClassLoader } from '../modules/Courses/pages/CourseClass';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    children: [
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
        element: <CourseHomePage />,
      },
      {
        path: '/my-courses',
        loader: coursesPageLoader,
        element: <StudentCourses />,
      },
      {
        path: '/my-courses/:courseId',
        loader: courseClassLoader,
        element: <CourseClass />,
      },
      {
        path: 'instructor/:instructorId/courses',
        element: <CoursesList />,
      },
    ],
  },
]);
