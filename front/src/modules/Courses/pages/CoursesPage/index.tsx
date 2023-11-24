import { useLoaderData } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import { useAuth } from '../../../../core/hooks/useAuth';
import { CourseContextActions } from '../../context/Provider';
import Course from '../../models/course';
import AvailableCourseCard from './components/AvailableCourseCard';
import styles from './style.module.css';
import { Greetings } from './components/Greetings';

async function coursesPageLoader(): Promise<{
  courses: Course[];
  enrolledCourses: Course[];
}> {
  const actions = new CourseContextActions();

  return {
    courses: await actions.getAllAvailableCourses(),
    enrolledCourses: await actions.getUserCourses(),
  };
}

function CoursesPage() {
  const { isSignedIn } = useAuth();
  const { courses, enrolledCourses } = useLoaderData() as {
    courses: Course[];
    enrolledCourses: Course[];
  };

  return (
    <Body>
      <Greetings />
      <div className={styles.main_wrapper}>
        {isSignedIn && (
          <div className={styles.courses_content}>
            <div className={styles.title}>
              <h3>Continue seus cursos</h3>
            </div>
            {enrolledCourses.length == 0 ? (
              <>Você ainda não começou nenhum curso</>
            ) : (
              <div className={styles.courses_list}>
                {enrolledCourses.map((course) => (
                  <AvailableCourseCard key={course.id} {...course} />
                ))}
              </div>
            )}
          </div>
        )}
        <div className={styles.courses_content}>
          <div className={styles.title}>
            <h3>Cursos disponíveis</h3>
          </div>
          {courses.length == 0 ? (
            <>Nenhum curso ainda</>
          ) : (
            <div className={styles.courses_list}>
              {courses.map((course) => (
                <AvailableCourseCard key={course.id} {...course} />
              ))}
            </div>
          )}
        </div>
      </div>
    </Body>
  );
}

export { CoursesPage, coursesPageLoader };
