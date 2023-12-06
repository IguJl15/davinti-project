import { PrimaryButton } from '../../../../../../core/components/Button';
import styles from './styles.module.css';

type LessonGreetingsprops = {
  courseName: string;
  lessonName: string;
};

function LessonGreetings({ courseName, lessonName }: LessonGreetingsprops) {
  return (
    <div className={styles.greetings_wrapper}>
      <div className={styles.text_wrapper}>
        <p>Curso {courseName}</p>
        <h2 className="headline-small">Atividade {lessonName}</h2>
      </div>
    </div>
  );
}

export { LessonGreetings };
