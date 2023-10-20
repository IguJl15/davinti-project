import style from "./style.module.css";

interface ButtonProps {
  label: string;
}

function Button({ label }: ButtonProps) {
  return (
    <div className={style.button_wrapper}>
      <input type="submit" value={label} />
    </div>
  );
}

export { Button };
