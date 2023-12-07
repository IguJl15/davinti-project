import Root from '../App';
import { Navigate, createBrowserRouter } from 'react-router-dom';
import { LoginPage } from '../modules/Auth/pages/LoginPage';
import { RegisterPage } from '../modules/Auth/pages/RegisterPage';
import { AnonymusRoute } from './AnonymusRoute';
import { CoursesPage, coursesPageLoader } from '../modules/Courses/pages/CoursesPage';
import { CourseHomePage, coursePageLoader } from '../modules/Courses/pages/CoursesHomePage';
import { StudentCourses, enrolledCoursesLoader } from '../modules/Courses/pages/StudentCourses';
import { CoursesList, instructorCoursesLoader } from '../modules/Instructor/pages/CoursesList';
import { CourseClass, courseClassLoader } from '../modules/Courses/pages/CourseClass';
import { ProtectedRoute } from './PrivateRoute';
import { Role } from '../core/interfaces/Role';
import InstructorProvider from '../modules/Instructor/context/InstructorProvider';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    children: [
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
        path: 'mycourses',
        loader: enrolledCoursesLoader,
        element: <StudentCourses />,
      },
      {
        path: 'mycourses/:courseId',
        loader: courseClassLoader,
        element: <CourseClass />,
      },
    ],
  },
  {
    path: '/',
    element: (
      <AnonymusRoute>
        <Root />
      </AnonymusRoute>
    ),
    children: [
      {
        path: 'register',
        element: <RegisterPage />,
      },
      {
        path: 'login',
        element: <LoginPage />,
      },
    ],
  },
  {
    path: '/instructor',
    element: (
      <ProtectedRoute role={Role.INSTRUCTOR}>
        <InstructorProvider>
          <Root />
        </InstructorProvider>
      </ProtectedRoute>
    ),
    children: [
      {
        path: '',
        index: true,
        element: <Navigate to="mycourses" />,
      },

      {
        path: 'mycourses',
        loader: instructorCoursesLoader,
        element: <CoursesList />,
      },
    ],
  },
]);
