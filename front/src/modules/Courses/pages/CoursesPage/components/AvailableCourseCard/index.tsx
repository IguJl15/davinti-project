import styles from "./style.module.css";
import Course from "../../../../models/course";
import { useCoursesActions } from "../../../../context/Provider";
import { useAuth } from "../../../../../../core/hooks/useAuth";

export default function AvailableCourseCard(course: Course) {
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
            <button
              className="text_button"
              onClick={() => enrollCurrentUserOnCourse(course)}
            >
              Começar <span>-</span>
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
