import style from './style.module.css';

interface CardContentItemProps {
  text: string;
}

function ContentCardItem({ text }: CardContentItemProps) {
  return (
    <div className={style.cardItem + ' title-medium'}>
      <span className={style.text}>{text}</span>
    </div>
  );
}

export { ContentCardItem };
