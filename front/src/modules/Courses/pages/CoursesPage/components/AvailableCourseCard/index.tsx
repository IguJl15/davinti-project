import styles from './style.module.css';
import Course from '../../../../models/course';
import { useCoursesActions } from '../../../../context/Provider';
import { useAuth } from '../../../../../../core/hooks/useAuth';
import { PrimaryButton } from '../../../../../../core/components/Button';
import { useNavigate } from 'react-router-dom';

export default function AvailableCourseCard(course: Course) {
  const navigate = useNavigate();
  const { isSignedIn } = useAuth();

  return (
    <div className={styles.available_course_card}>
      <div className={styles.post_card_content}>
        <div className={styles.main}>
          <div className={styles.title}>{course.name}</div>
          <div className="body-small">
            {course.description.substring(0, 150) + (course.description.length > 150 ? '...' : '')}
          </div>
        </div>
        <div className={styles.footer}>
          <span className="label-small on-surface-variant-text">
            {course.instructor.completeName}
          </span>
          {isSignedIn && (
            <>
              <PrimaryButton
                label="Detalhes"
                style="Text"
                onClick={() => {
                  navigate('/courses/' + course.id);
                }}
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
}
