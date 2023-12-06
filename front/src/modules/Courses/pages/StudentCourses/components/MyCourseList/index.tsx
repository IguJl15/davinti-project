import { MyCourseCard } from '../MyCourseCard';
import Course from '../../../../models/course';
import styles from './styles.module.css';
import { CoursesProgression } from 'modules/Courses/context/LessonContextActions';

interface MyCourseCardProps {
  courses: Course[];
  coursesProgression: CoursesProgression;
}

function MyCourseList({ courses, coursesProgression }: MyCourseCardProps) {
  return (
    <ul className={styles.card}>
      {courses.map((course: Course) => {
        const concludedLessons = coursesProgression[course.id].lesson;
        var progress: number;
        if (coursesProgression[course.id].concluded) {
          progress = 1;
        } else if (course.lessons.length != 0) {
          progress = concludedLessons / course.lessons.length;
        } else {
          progress = 0;
        }

        return <MyCourseCard course={course} progression={progress} />;
      })}
    </ul>
  );
}

export { MyCourseList };
