import { PropsWithChildren, createContext, useContext } from 'react';
import { authRepository, httpClient } from '../../../core/providers/AuthProvider/AuthProvider';
import Course from '../models/course';

export class CourseContextActions {
  // private httpClient: HttpClient;

  constructor() {
    // this.httpClient = new AxiosClient();
  }

  getAllAvailableCourses(): Promise<Course[]> {
    return httpClient.get('/courses');
  }

  getCourseById(id: number): Promise<Course> {
    {
      return httpClient.get<Course>(`/courses/${id}`);
    }
  }

  async enrollCurrentUserOnCourse(course: Course): Promise<unknown | void> {
    const user = authRepository.getLocalAuthData()?.user;

    if (!user) return;

    return httpClient.post(`/students/${user.id}/courses/${course.id}`, {});
  }

  async getUserCourses(): Promise<Course[]> {
    const user = authRepository.getLocalAuthData()?.user;

    if (!user) return [];

    return httpClient.get<Course[]>(`/students/${user.id}/courses`);
  }

  // public unenrollCourse(course: Course) {
  // return httpClient.delete()
  // }
}

const CourseActionsContext = createContext<CourseContextActions>({} as CourseContextActions);

export function CourseActionsProvider({ children }: PropsWithChildren) {
  const actions = new CourseContextActions();

  return <CourseActionsContext.Provider value={actions}>{children}</CourseActionsContext.Provider>;
}

export function useCoursesActions() {
  return useContext(CourseActionsContext);
}
