import { useLoaderData } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import { MyCourseList } from './components/MyCourseList';
import { ProgresCard } from './components/ProgressCard';
import styles from './styles.module.css';
import React from 'react';
import Course from '../../models/course';
import {
  CoursesProgression,
  LessonContextActions,
} from '../../../../modules/Courses/context/LessonContextActions';
import { CourseContextActions } from '../../../../modules/Courses/context/Provider';

type EnrolledCoursesLoader = {
  courses: Course[];
  coursesProgress: CoursesProgression;
};

export async function enrolledCoursesLoader(): Promise<EnrolledCoursesLoader> {
  const courseActions = new CourseContextActions();
  const lessonActions = new LessonContextActions();

  const courses = await courseActions.getUserCourses();
  const progress: CoursesProgression = {};
  courses.forEach((course) => (progress[course.id] = lessonActions.getLastConcludedLesson(course)));

  return { courses, coursesProgress: progress };
}

function StudentCourses(): React.JSX.Element {
  const { courses, coursesProgress } = useLoaderData() as EnrolledCoursesLoader;

  const numberOfCourses = courses.length;
  const finishedCourses = courses.filter((c) => coursesProgress[c.id].concluded).length;
  const lessonsCount = courses.reduce(
    (count, course) => count + coursesProgress[course.id].lesson,
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
          <MyCourseList courses={courses} coursesProgression={coursesProgress} />
        </div>
      </div>
    </Body>
  );
}

export { StudentCourses };
