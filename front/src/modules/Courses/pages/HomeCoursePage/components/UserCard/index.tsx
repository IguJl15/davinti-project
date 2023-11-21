import userLogo from "../../../../../../assets/user_logo.png"
import style from "./style.module.css"

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
        <h4>{name}</h4>
        <p>{job}</p>
        <p>{role}</p>
      </div>
    </div>
  );
}

export { UserCard };
