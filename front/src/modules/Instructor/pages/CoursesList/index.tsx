import { PrimaryButton } from '../../../../core/components/Button';
import { Body } from '../../../../core/components/Body';
import style from './styles.module.css';
import { CourseListCard } from '../../components/CourseListCard';
import { Modal } from '../../../../core/components/Modal';
import React from 'react';

function CoursesList() {
  const [state, setState] = React.useState(false)

  function handleModal(){
    setState(!state)
  }

  return (
    <>
      <Modal state={state} changeState={handleModal} />
      <Body>
        <h1 style={{ width: 'clamp(0px,100%,1000px)', marginTop: '15px' }}>Hello, Kelson</h1>
        <div className={style.cardList}>
          <div className={style.contentWrapper}>
            <div className={style.frame1}>
              <h2>Seus cursos</h2>
              <PrimaryButton label="Adcionar novos cursos" onClick={handleModal} />
            </div>
            <div className="frame2">
              <CourseListCard>
                
              </CourseListCard>
            </div>
          </div>
        </div>
      </Body>
    </>
  );
}

export { CoursesList };
