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

  return (
    <Body>
      <div className={styles.content_wrapper}>
        <div style={{ display: 'flex', gap: '8px', width: '100%' }}>
          <ProgresCard text="iniciou" progressText="3 cursos" />
          <ProgresCard text="concluiu" progressText="1 curso" />
          <ProgresCard text="conferiu" progressText="12 aulas" />
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
