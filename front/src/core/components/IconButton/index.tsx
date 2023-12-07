import { ReactNode } from 'react';
import { ButtonStyle } from '../Button/index.tsx';
import styles from './style.module.css';

type ButtonProps = {
  onClick?: () => void;
  type?: 'button' | 'reset' | 'submit';
  buttonStyle?: ButtonStyle;
  icon: ReactNode;
  disabled?: boolean;
};

// ${styles.disabled}

function IconButton({
  onClick,
  icon,
  type = 'button',
  buttonStyle = 'Primary',
  disabled = false,
  ...rest
}: ButtonProps) {
  return (
    <button
      onClick={type == 'button' ? onClick : undefined}
      onSubmit={type == 'submit' ? onClick : undefined}
      className={
        `label-large ` +
        `${styles.button} ` +
        `${styles[buttonStyle]} ` +
        `${disabled && styles.disabled} ` +
        `${icon && styles.icon} `
      }
      type={type}
      {...rest}
    >
      <span className={styles.state}>
        {icon}
      </span>
    </button>
  );
}

export { IconButton };
