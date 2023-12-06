import styles from './styles.module.css';
import { PrimaryButton } from '../../../../../../core/components/Button';
import { useNavigate } from 'react-router-dom';
import Course from 'modules/Courses/models/course';

function MyCourseCard({ course }: { course: Course }) {
  const navigate = useNavigate();

  return (
    <div className={styles.card}>
      <div className={styles.courseName}>
        <p className="title-medium">{course.name}</p>
        <p className="body-medium">{course.name}</p>
      </div>
      <div className={styles.courseProgress}>
        <span>Progresso: {32}%</span>
      </div>
      <div style={{ display: 'flex', gap: '8px' }}>
        <PrimaryButton label="Continuar" onClick={() => navigate('/my-courses/' + course.id)} />
        <PrimaryButton
          label="Remover"
          buttonStyle="Text"
          onClick={() => null /* unenrollCourse(course)*/}
        />
      </div>
    </div>
  );
}

export { MyCourseCard };
