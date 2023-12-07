import React from 'react';
import { useLoaderData } from 'react-router-dom';
import { Body } from '../../../../core/components/Body';
import { PrimaryButton } from '../../../../core/components/Button';
import { IconButton } from '../../../../core/components/IconButton';
import { Modal } from '../../../../core/components/Modal';
import { authRepository } from '../../../../core/providers/AuthProvider/AuthProvider';
import Course from '../../../Courses/models/course';
import { CourseListCard } from '../../components/CourseListCard';
import { CreateCourseFormData } from '../../context/InstructorActionsContext';
import style from './styles.module.css';
import { useInstructorActions } from '../../context/InstructorProvider';
import CourseRow from './CourseRow';

type InstructorCousesLoaderData = {
  courses: Course[];
};

export async function instructorCoursesLoader(): Promise<InstructorCousesLoaderData> {
  const userData = await authRepository.getAllDataFromCurrentUser();

  const courses = userData?.courses ?? [];

  return { courses };
}

function CoursesList() {
  const { createCourse, instructor } = useInstructorActions();
  const { courses } = useLoaderData() as InstructorCousesLoaderData;

  const [state, setState] = React.useState(false);

  function handleModal() {
    setState(!state);
  }

  function createCourseButtonClicked(data: CreateCourseFormData) {
    try {
      createCourse(data).then(() => setState(false));
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <Modal state={state} onFormSubmit={createCourseButtonClicked} changeState={handleModal} />
      <Body>
        <h1 className="headline-small">Olá, {instructor.completeName}</h1>
        <div className={style.cardList}>
          <div className={style.contentWrapper}>
            <div className={style.frame1}>
              <h2 className="headline-medium">Seus cursos</h2>
              <PrimaryButton label="Criar Novo Curso" onClick={handleModal} />
            </div>
            <div className="frame2">
              <CourseListCard>
                <table cellSpacing={0}>
                  <thead>
                    <tr className="title-medium">
                      <th className={style.courseTh}>Curso</th>
                      <th>Ações</th>
                    </tr>
                  </thead>
                  <tbody>
                    {courses.map((course) => (
                      <CourseRow key={course.id} course={course} />
                    ))}
                  </tbody>
                </table>
              </CourseListCard>
            </div>
          </div>
        </div>
      </Body>
    </>
  );
}

export { CoursesList };
