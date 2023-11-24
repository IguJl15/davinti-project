import style from './style.module.css';

function DescriptionCard({ description }: { description: string }) {
  return (
    <div className={style.cardWrapper}>
      <h2 className="title-large">Descrição</h2>
      <p className="body-medium">{description}</p>
    </div>
  );
}

export { DescriptionCard };
