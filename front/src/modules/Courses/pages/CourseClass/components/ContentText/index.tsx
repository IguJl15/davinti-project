import React from 'react';
import styles from './styles.module.css';

interface ContentTextProps {
  text?: string;
  title?: string
}

function ContentText({ text, title }: ContentTextProps): React.JSX.Element {
  return (
    <div className={styles.text_card}>
      <span className='text-large' >{title}</span>
      <div className={styles.content_wrapper}>
        {text}
      </div>
    </div>
  );
}

export { ContentText };
