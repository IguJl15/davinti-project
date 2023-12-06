import { useLoaderData } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import { MyCourseList } from './components/MyCourseList';
import { ProgresCard } from './components/ProgressCard';
import styles from './styles.module.css';
import React from 'react';
import Course from '../../models/course';

function StudentCourses(): React.JSX.Element {
  const { courses } = useLoaderData() as {
    courses: Course[];
  };

  const numberOfCourses = courses.length;
  const finishedCourses = [...courses].filter((c) => /* c.progress == 1 */ false).length;
  const lessonsCount = [...courses].reduce(
    (lessonsCount, course) => (lessonsCount += course.lessons.length),
    0
  );

  return (
    <Body>
      <div className={styles.content_wrapper}>
        <div style={{ display: 'flex', gap: '8px', width: '100%' }}>
          <ProgresCard text="iniciou" progressText={numberOfCourses + ' cursos'} />
          <ProgresCard text="concluiu" progressText={finishedCourses + ' cursos'} />
          <ProgresCard text="conferiu" progressText={lessonsCount + ' aulas'} />
        </div>
        <div className={styles.title}>
          <p className="headline-small">Meus Cursos</p>
          <MyCourseList courses={courses} />
        </div>
      </div>
    </Body>
  );
}

export { StudentCourses };
