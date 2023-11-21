import styles from './style.module.css';
import Course from '../../../../models/course';
import { useCoursesActions } from '../../../../context/Provider';
import { useAuth } from '../../../../../../core/hooks/useAuth';
import { PrimaryButton } from '../../../../../../core/components/Button';
import { useNavigate } from 'react-router-dom';

export default function AvailableCourseCard(course: Course) {
  const navigate = useNavigate();
  const { isSignedIn } = useAuth();
  const { enrollCurrentUserOnCourse } = useCoursesActions();

  return (
    <div className={styles.available_course_card}>
      <div className={styles.post_card_content}>
        <div className={styles.main}>
          <div className={styles.title}>{course.name}</div>
        </div>
        <div className={styles.footer}>
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
