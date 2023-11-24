import { Body } from '../../../../core/components/Body';
import { Content, ContentType } from './components/Content';
import { LessonGreetings } from './components/LessonGreetings';
import styles from './style.module.css';

function CourseClass() {
  return (
    <Body>
      <div className={styles.page_wrapper}>
        <LessonGreetings courseName="Vender Celta" lessonName="Celta rebaixado" />
        <div className={styles.content_wrapper}>
          <Content
            title='Video bala pra voces verem'
            contentType={ContentType.VIDEO}
            videoLink='https://www.youtube.com/embed/tf6yhMynTRo'
          />
        </div>
      </div>
    </Body>
  );
}

export { CourseClass };
