import styles from "./style.module.css";
import { Body } from "../../../../core/components/Body";
import { useLoaderData } from "react-router-dom";
import Course from "../../models/course";
import AvailableCourseCard from "./components/AvailableCourseCard";
import { CourseContextActions } from "../../context/Provider";

async function coursesPageLoader(): Promise<{
  courses: Course[];
  enrolledCourses: Course[];
}> {
  const actions = new CourseContextActions();

  return {
    courses: await actions.getAllAvailableCourses(),
    enrolledCourses: await actions.getUserCourses(),
  };
}

function CoursesPage() {
  const { courses, enrolledCourses } = useLoaderData() as {
    courses: Course[];
    enrolledCourses: Course[];
  };

  console.table(courses);
  console.table(enrolledCourses);

  return (
    <Body>
      <div className={styles.page_wrapper}>
        <div className={styles.greeting}>
          <h2>Hello, Kelson Filho</h2>
        </div>
        <div className={styles.main_wrapper}>
          {enrolledCourses.length == 0 ? (
            <>Você ainda não começou nenhum curso</>
          ) : (
            <div className={styles.courses_content}>
              <div className={styles.title}>
                <h3>Continue seus cursos</h3>
              </div>
              <div className={styles.courses_list}>
                {enrolledCourses.map((course) => (
                  <AvailableCourseCard key={course.id} {...course} />
                ))}
              </div>
            </div>
          )}
          {courses.length == 0 ? (
            <>Nenhum curso ainda</>
          ) : (
            <div className={styles.courses_content}>
              <div className={styles.title}>
                <h3>Cursos disponíveis</h3>
              </div>
              <div className={styles.courses_list}>
                {courses.map((course) => (
                  <AvailableCourseCard key={course.id} {...course} />
                ))}
              </div>
            </div>
          )}
        </div>
      </div>
    </Body>
  );
}

export { CoursesPage, coursesPageLoader };
