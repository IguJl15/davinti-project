import { MyCourseCard } from '../MyCourseCard';
import Course from '../../../../models/course';
import styles from './styles.module.css';

interface MyCourseCardProps {
  courses: Course[];
}

function MyCourseList({ courses }: MyCourseCardProps) {
  return (
    <ul className={styles.card}>
      {courses.map((course: Course) => (
        <MyCourseCard courseName={course.name} progression={10} />
      ))}
    </ul>
  );
}

export { MyCourseList };
