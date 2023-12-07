import { PrimaryButton } from '../../../../core/components/Button';
import { Body } from '../../../../core/components/Body';
import style from './styles.module.css';
import { CourseListCard } from '../../components/CourseListCard';
import { Modal } from '../../../../core/components/Modal';
import React from 'react';
import { authRepository } from '../../../../core/providers/AuthProvider/AuthProvider';
import Course from '../../../Courses/models/course';
import { useLoaderData } from 'react-router-dom';
import { IconButton } from '../../../../core/components/IconButton';

type InstructorCousesLoaderData = {
  courses: Course[];
};

export async function instructorCoursesLoader(): Promise<InstructorCousesLoaderData> {
  const userData = await authRepository.getAllDataFromCurrentUser();

  const courses = userData?.courses ?? [];

  return { courses };
}

function CoursesList() {
  const { courses } = useLoaderData() as InstructorCousesLoaderData;

  const [state, setState] = React.useState(false);

  function handleModal() {
    setState(!state);
  }

  return (
    <>
      <Modal state={state} changeState={handleModal} />
      <Body>
        <h1>Hello, Kelson</h1>
        <div className={style.cardList}>
          <div className={style.contentWrapper}>
            <div className={style.frame1}>
              <h2>Seus cursos</h2>
              <PrimaryButton label="Adcionar novos cursos" onClick={handleModal} />
            </div>
            <div className="frame2">
              <CourseListCard>
                <table style={{ width: '100%' }}>
                  <thead>
                    <tr>
                      <th>Curso</th>
                      <th>Ações</th>
                    </tr>
                  </thead>
                  <tbody>
                    {courses.map((course) => (
                      <tr key={course.id}>
                        <td>
                          <div className={style.courseDetails}>
                            <span className="title-medium">{course.name}</span>
                            <span className="body-medium">{course.lessons.length} aulas</span>
                          </div>
                        </td>
                        <td className={style.actionsTd}>
                          <div className={style.actions}>
                            <IconButton
                              icon={<span className="material-symbols-outlined">edit</span>}
                            />

                            <IconButton
                              buttonStyle="Text"
                              icon={<span className="material-symbols-outlined">delete</span>}
                            />
                          </div>
                        </td>
                      </tr>
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
