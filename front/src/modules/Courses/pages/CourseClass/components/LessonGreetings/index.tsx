import { PrimaryButton } from '../../../../../../core/components/Button';
import styles from './styles.module.css';

type LessonGreetingsprops = {
  courseName: string;
  lessonName: string;
  isLastLesson: boolean;
  nextLessonButtonClicked: () => void;
};

function LessonGreetings({
  courseName,
  lessonName,
  isLastLesson,
  nextLessonButtonClicked,
}: LessonGreetingsprops) {
  const icon = isLastLesson ? 'check' : 'arrow_right_alt';

  return (
    <div className={styles.greetings_wrapper}>
      <div className={styles.text_wrapper}>
        <p>{courseName}</p>
        <h2 className="headline-small">Atividade {lessonName}</h2>
      </div>
      <div>
        <PrimaryButton
          label={isLastLesson ? 'Finalizar Curso' : 'Próxima Aula'}
          onClick={nextLessonButtonClicked}
          icon={<span className="material-symbols-outlined">{icon}</span>}
          rightIcon
        />
      </div>
    </div>
  );
}

export { LessonGreetings };
