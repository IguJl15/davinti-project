import userLogo from '../../../../../../assets/user_logo.png';
import style from './style.module.css';

interface UserCardProps {
  name: string;
  job: string;
  role: string;
}

function UserCard({ name, job, role }: UserCardProps) {
  return (
    <div className={style.cardWrapper}>
      <div className={style.logoWrapper}>
        <img src={userLogo} alt="logo" />
      </div>
      <div className={style.contentWrapper}>
        <h4 className="title-large on-surface-text">{name}</h4>
        <p className="label-large on-surface-variant-text">{job}</p>
        <p className="label-large on-surface-variant-text">{role}</p>
      </div>
    </div>
  );
}

export { UserCard };
