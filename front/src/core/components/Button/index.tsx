import { ReactNode } from 'react';
import styles from './style.module.css';

type ButtonStyle = 'Primary' | 'Text' | 'Tonal';

type ButtonProps = {
  label: string;
  onClick?: () => void;
  type?: 'button' | 'reset' | 'submit';
  buttonStyle?: ButtonStyle;
  icon?: ReactNode;
  disabled?: boolean;
};

// ${styles.disabled}

function PrimaryButton({
  label,
  onClick,
  icon = undefined,
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
        {label}
      </span>
    </button>
  );
}

export { PrimaryButton };
