import { LoaderFunctionArgs, useLoaderData } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import { ContentCard } from './components/ContentCard';
import { DescriptionCard } from './components/DescripitionCard';
import { UserCard } from './components/UserCard';
import './style.css';
import {
  CourseContextActions,
  useCoursesActions,
} from '../../../../modules/Courses/context/Provider';
import Course from '../../../../modules/Courses/models/course';
import { User } from 'core/interfaces/User';
import { httpClient } from '../../../../core/providers/AuthProvider/AuthProvider';
import { PrimaryButton } from '../../../../core/components/Button';

async function coursePageLoader({
  params,
}: LoaderFunctionArgs): Promise<{ course: Course; instructor: User }> {
  const actions = new CourseContextActions();
  const course = await actions.getCourseById(Number(params.courseId));
  return {
    course,
    instructor: await httpClient.get('/users/' + course.instructorId),
  };
}

function HomeCoursePage() {
  const { enrollCurrentUserOnCourse } = useCoursesActions();
  const { course, instructor } = useLoaderData() as { course: Course; instructor: User };

  console.table(course);

  return (
    <Body>
      <div className="mainWrapper">
        <div className="section_1">
          <div className="sectionTitle">
            <div className="title">
              <h2>{course.name}</h2>
              <PrimaryButton
                label="Iniciar Curso"
                onClick={() => enrollCurrentUserOnCourse(course)}
              />
            </div>
            <p>
              Spener agon respektive gall fulfillment. Neodiktisk nende med önyrad tret facial
              recognition lebel såväl som teniledes, laras.
            </p>
          </div>
        </div>
        <div className="section_2">
          <div className="description">
            <DescriptionCard />
            <UserCard
              name={instructor.completeName}
              job="Analise e Desenvolvimento"
              role="Instituto Federal do Piaui IFPI"
            />
          </div>
          <div className="content">
            <ContentCard course={course} />
          </div>
        </div>
      </div>
    </Body>
  );
}

export { HomeCoursePage, coursePageLoader };
