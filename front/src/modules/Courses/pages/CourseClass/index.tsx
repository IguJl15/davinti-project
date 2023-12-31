import Course from 'modules/Courses/models/course';
import { LoaderFunctionArgs, useLoaderData, useNavigate } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import {
  CourseProgressionStatus,
  LessonContextActions,
} from '../../../../modules/Courses/context/LessonContextActions';
import { CourseContextActions } from '../../../../modules/Courses/context/Provider';
import { Content, ContentType } from './components/Content';
import { LessonGreetings } from './components/LessonGreetings';
import SupportContentCard from './components/SupportContent';
import styles from './style.module.css';

type CourseClassLoaderData = {
  course: Course;
  courseProgression: CourseProgressionStatus;
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
    courseProgression: progress,
  };
}

function CourseClass() {
  const navigate = useNavigate();
  const lessonsActions = new LessonContextActions();
  const { course, courseProgression: progression } = useLoaderData() as CourseClassLoaderData;

  const lesson = course.lessons[progression.lesson - 1];

  var mainContentType: ContentType;
  const mainContent = lesson.mainContent;

  if (mainContent.url != null) mainContentType = ContentType.LINK;
  else if (mainContent.videoUrl != null) mainContentType = ContentType.VIDEO;
  else if (mainContent.text != null) mainContentType = ContentType.TEXT;

  const isLastLesson = course.lessons.length <= progression.lesson;

  return (
    <Body>
      <LessonGreetings
        courseName={course.name}
        lessonName={lesson.title}
        isLastLesson={isLastLesson}
        nextLessonButtonClicked={() => {
          const newStatus = lessonsActions.goToNextLesson(course);
          if (newStatus.concluded) {
            navigate('/mycourses');
          } else {
            navigate(0); // reload page
          }
        }}
      />
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
