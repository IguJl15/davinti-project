import { ReactNode } from 'react';
import styles from './style.module.css';

type ButtonStyle = 'Primary' | 'Text' | 'Tonal';

type ButtonProps = {
  label: string;
  onClick?: () => void;
  type?: 'button' | 'reset' | 'submit';
  style?: ButtonStyle;
  icon?: ReactNode;
  disabled?: boolean;
};

// ${styles.disabled}

function PrimaryButton({
  label,
  onClick,
  icon = undefined,
  type = 'button',
  style = 'Primary',
  disabled = false,
}: ButtonProps) {
  return (
    <button
      onClick={type == 'button' ? onClick : undefined}
      onSubmit={type == 'submit' ? onClick : undefined}
      className={
        `label-large ` +
        `${styles.button} ` +
        `${styles[style]} ` +
        `${disabled && styles.disabled} ` +
        `${icon && styles.icon} `
      }
      type={type}
    >
      <span className={styles.state}>
        {icon}
        {label}
      </span>
    </button>
  );
}

export { PrimaryButton };
