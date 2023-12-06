import Course, { Lessson } from 'modules/Courses/models/course';
import { Body } from '../../../../core/components/Body';
import { Content, ContentType } from './components/Content';
import { LessonGreetings } from './components/LessonGreetings';
import styles from './style.module.css';
import SupportContentCard from './components/SupportContent';

type CourseClassLoaderData = {
  course: Course;
  courseProgress: { currentLesson: number };
};

function CourseClass() {
  return (
    <Body>
      <LessonGreetings courseName="Vender Celta" lessonName="Celta rebaixado" />
      <div className={styles.body}>
        <Content
          title="Video bala pra voces verem"
          contentType={ContentType.LINK}
          urlLink='https://www.example.com'
          // text={
          //   'O que é Lorem Ipsum?\nLorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.\n\nPorque nós o usamos?\nÉ um fato conhecido de todos que um leitor se distrairá com o conteúdo de texto legível de uma página quando estiver examinando sua diagramação. A vantagem de usar Lorem Ipsum é que ele tem uma distribuição normal de letras, ao contrário de "Conteúdo aqui, conteúdo aqui", fazendo com que ele tenha uma aparência similar a de um texto legível. Muitos softwares de publicação e editores de páginas na internet agora usam Lorem Ipsum como texto-modelo padrão, e uma rápida busca por \'lorem ipsum\' mostra vários websites ainda em sua fase de construção. Várias versões novas surgiram ao longo dos anos, eventualmente por acidente, e às vezes de propósito (injetando humor, e coisas do gênero).\n\n\nDe onde ele vem?\nAo contrário do que se acredita, Lorem Ipsum não é simplesmente um texto randômico.'
          // }
          videoLink="https://www.youtube.com/embed/tf6yhMynTRo"
        />
        <aside>
          <SupportContentCard lesson={{} as Lessson} />
        </aside>
      </div>
    </Body>
  );
}

export { CourseClass };
