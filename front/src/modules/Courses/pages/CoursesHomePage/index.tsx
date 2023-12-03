import { LoaderFunctionArgs, useLoaderData } from 'react-router-dom';
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
import { User } from 'core/interfaces/User';
import { PrimaryButton } from '../../../../core/components/Button';

async function coursePageLoader({ params }: LoaderFunctionArgs): Promise<{ course: Course }> {
  const actions = new CourseContextActions();
  const course = await actions.getCourseById(Number(params.courseId));
  return {
    course,
  };
}

function CourseHomePage() {
  const { enrollCurrentUserOnCourse } = useCoursesActions();
  const { course } = useLoaderData() as { course: Course; instructor: User };

  console.table(course);

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
          <PrimaryButton label="Iniciar Curso" onClick={() => enrollCurrentUserOnCourse(course)} />
        </div>
        <div className={styles.body}>
          <div className={styles.main}>
            <DescriptionCard description={course.description} />
            <ContentCard course={course} />
          </div>
          <aside>
            <UserCard
              name={course.instructor.completeName}
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
