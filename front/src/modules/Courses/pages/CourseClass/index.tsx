import Course, { Lessson } from 'modules/Courses/models/course';
import { Body } from '../../../../core/components/Body';
import { Content, ContentType } from './components/Content';
import { LessonGreetings } from './components/LessonGreetings';
import styles from './style.module.css';
import SupportContentCard from './components/SupportContent';
import { LoaderFunctionArgs, useLoaderData } from 'react-router-dom';
import { CourseContextActions } from '../../../../modules/Courses/context/Provider';
import { LessonContextActions } from '../../../../modules/Courses/context/LessonContextActions';

type CourseClassLoaderData = {
  course: Course;
  courseLessonIndex: number;
};

export async function courseClassLoader({
  params,
}: LoaderFunctionArgs): Promise<CourseClassLoaderData> {
  const actions = new CourseContextActions();
  const lessonsActions = new LessonContextActions();

  const course = await actions.getCourseById(Number(params.courseId));
  const progress = lessonsActions.getLastConcludedLesson(course);

  return {
    course,
    courseLessonIndex: progress.lesson,
  };
}

function CourseClass() {
  const { course, courseLessonIndex } = useLoaderData() as CourseClassLoaderData;

  const lesson = course.lessons[courseLessonIndex - 1];

  var mainContentType: ContentType;
  const mainContent = lesson.mainContent;

  if (mainContent.url != null) mainContentType = ContentType.LINK;
  else if (mainContent.videoUrl != null) mainContentType = ContentType.VIDEO;
  else if (mainContent.text != null) mainContentType = ContentType.TEXT;

  return (
    <Body>
      <LessonGreetings courseName={course.name} lessonName={lesson.title} />
      <div className={styles.body}>
        <Content
          title={mainContent.title}
          contentType={mainContentType!}
          urlLink={mainContent.url}
          videoLink={mainContent.videoUrl}
          text={mainContent.text}
        />
        <aside>
          <SupportContentCard contents={lesson.supportContent} />
        </aside>
      </div>
    </Body>
  );
}

export { CourseClass };
