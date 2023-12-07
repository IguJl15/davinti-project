import Course from '../../../../Courses/models/course';
import styles from './styles.module.css';
import { IconButton } from '../../../../../core/components/IconButton';
import { useState } from 'react';

export default function CourseRow({ course }: { course: Course }) {
  const [expanded, setExpanded] = useState(false);
  return (
    <tr>
      <td>
        <div className={styles.courseDetails}>
          <span className="body-large">{course.name}</span>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <IconButton
              buttonStyle="Text"
              onClick={() => setExpanded(!expanded)}
              icon={
                <span className="material-symbols-outlined">
                  {expanded ? 'expand_less' : 'expand_more'}
                </span>
              }
            />
            <span className="body-medium">{course.lessons.length} aulas</span>
          </div>
          {expanded && course.lessons.map((lesson) => <>{lesson.title}</>)}
        </div>
      </td>
      <td className={styles.actionsTd}>
        <div className={styles.actions}>
          <IconButton icon={<span className="material-symbols-outlined">edit</span>} />

          <IconButton
            buttonStyle="Text"
            icon={<span className="material-symbols-outlined">delete</span>}
          />
        </div>
      </td>
    </tr>
  );
}
