import style from './style.module.css';

function DescriptionCard({ description }: { description: string }) {
  return (
    <div className={style.cardWrapper}>
      <div className={style.contentWrapper}>
        <h2>Descrição</h2>
        <p className='body-medium'>{description}</p>
      </div>
    </div>
  );
}

export { DescriptionCard };
