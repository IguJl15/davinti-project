import React from 'react';
import styles from './styles.module.css';
import { PrimaryButton } from '../../../../../../core/components/Button';

interface MyCourseCardProps {
  progression: number;
  courseName: string;
}

function MyCourseCard({ progression, courseName }: MyCourseCardProps): React.JSX.Element {
  return (
    <div className={styles.card}>
      <div className="courseName">
        <p className='title-medium' >{courseName}</p>
        <p className='body-medium'>{courseName}</p>
      </div>
      <div className="courseProgress">
        <span>Progresso: {progression}%</span>
      </div>
      <div className="buttonWrapper">
        <PrimaryButton label="Continuar" />
        <PrimaryButton label="Remover" buttonStyle='Text'  />
      </div>
    </div>
  );
}

export { MyCourseCard };
