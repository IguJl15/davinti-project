import Course from '../../../../Courses/models/course';
import styles from './styles.module.css';
import { IconButton } from '../../../../../core/components/IconButton';
import { useState } from 'react';
import { httpClient } from '../../../../../core/providers/AuthProvider/AuthProvider';
import { useNavigate } from 'react-router-dom';

export default function CourseRow({ course }: { course: Course }) {
  const navigate = useNavigate();
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
            <span className={'body-medium'}>{course.lessons.length} aulas</span>
          </div>
          {expanded && (
            <div className={styles.lessons_list}>
              {course.lessons.map((lesson) => (
                <div>
                  <span>{lesson.title}</span>
                  <div>
                    <IconButton
                      buttonStyle="Text"
                      icon={<span className="material-symbols-outlined">delete</span>}
                    />
                  </div>
                </div>
              ))}
              <span></span>
            </div>
          )}
        </div>
      </td>
      <td className={styles.actionsTd}>
        <div className={styles.actions}>
          <IconButton icon={<span className="material-symbols-outlined">edit</span>} />

          <IconButton
            buttonStyle="Text"
            icon={<span className="material-symbols-outlined">delete</span>}
            onClick={async () => {
              await httpClient.delete(`/courses/${course.id}`);

              navigate(0);
            }}
          />
        </div>
      </td>
    </tr>
  );
}
