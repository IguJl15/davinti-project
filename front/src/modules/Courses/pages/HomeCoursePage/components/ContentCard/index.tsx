import Course from '../../../../../../modules/Courses/models/course';
import { ContentCardItem } from '../ContentCardItem';
import style from './style.module.css';

function ContentCard({ course }: { course: Course }) {
  return (
    <div className={style.cardWrapper}>
      <div className={style.contentWrapper}>
        <section className={style.text}>
          <h2>Conteúdos do curso</h2>
        </section>
        <section className={style.contentCards}>
          {course.lessons.map((value) => (
            <ContentCardItem key={value.id} text={value.title} />
          ))}
        </section>
      </div>
    </div>
  );
}

export { ContentCard };
