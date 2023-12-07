import { LoaderFunctionArgs, useLoaderData, useNavigate } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import { ContentCard } from './components/ContentCard';
import { DescriptionCard } from './components/DescripitionCard';
import { UserCard } from './components/UserCard';
import styles from './style.module.css';
import {
  CourseContextActions,
  useCoursesActions,
} from '../../../../modules/Courses/context/Provider';
import Course from '../../../../modules/Courses/models/course';
import { PrimaryButton } from '../../../../core/components/Button';

type CoursePageLoaderData = { course: Course; alreadyEnrolled: boolean };

async function coursePageLoader({ params }: LoaderFunctionArgs): Promise<CoursePageLoaderData> {
  const actions = new CourseContextActions();
  const course = await actions.getCourseById(Number(params.courseId));

  const userCourses = await actions.getUserCourses();

  const alreadyEnrolled = userCourses.some((c) => c.id == course.id);

  return {
    course,
    alreadyEnrolled,
  };
}

function CourseHomePage() {
  const navigate = useNavigate();
  const { enrollCurrentUserOnCourse } = useCoursesActions();
  const { course, alreadyEnrolled } = useLoaderData() as CoursePageLoaderData;

  return (
    <Body>
      <div className={styles.page}>
        <div className={styles.header}>
          <div className={styles.titles}>
            <h2 className="headline-small">{course.name}</h2>
            <p className="body-large">
              Spener agon respektive gall fulfillment. Neodiktisk nende med önyrad tret facial
              recognition lebel såväl som teniledes, laras.
            </p>
          </div>
          {alreadyEnrolled ? (
            <PrimaryButton
              buttonStyle="Tonal"
              label="Continuar Curso"
              onClick={() => navigate('/mycourses/' + course.id)}
            />
          ) : (
            <PrimaryButton
              label="Iniciar Curso"
              onClick={() => enrollCurrentUserOnCourse(course)}
            />
          )}
        </div>
        <div className={styles.body}>
          <div className={styles.main}>
            <DescriptionCard description={course.description} />
            <ContentCard course={course} />
          </div>
          <aside>
            <UserCard
              name={course.instructor.name}
              job="Analise e Desenvolvimento"
              role="Instituto Federal do Piaui IFPI"
            />
          </aside>
        </div>
      </div>
    </Body>
  );
}

export { CourseHomePage, coursePageLoader };
