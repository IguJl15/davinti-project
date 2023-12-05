import React from 'react';
import styles from './styles.module.css';

interface ProgresCardProps {
  text: string;
  progressText: string;
}

export function ProgresCard({ text, progressText }: ProgresCardProps): React.JSX.Element {
  return (
    <div className={styles.card}>
      <p className="body-large">Voce já {text}</p>
      <p className="title-medium">{progressText}</p>
    </div>
  );
}
