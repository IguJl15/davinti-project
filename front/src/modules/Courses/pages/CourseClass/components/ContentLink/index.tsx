import React from 'react';
import styles from './style.module.css';

interface ContentLinkProps {
  title: string;
  urlLink: string;
}

function ContentLink({ title, urlLink }: ContentLinkProps): React.JSX.Element {
  return (
    <div className={styles.link_wrapper}>
      <a href={urlLink}>
        <span className="material-symbols-outlined">link</span> 
        {title}
      </a>
    </div>
  );
}

export { ContentLink };
