// @ts-nocheck
import { ForwardRefRenderFunction, forwardRef } from 'react';
import '@material/web/textfield/outlined-text-field';
import styles from './style.module.css';
import { MdOutlinedTextField } from '@material/web/textfield/outlined-text-field';

interface InputBaseProps {
  inputType: string;
  label: string;
  autoComplete?: string;
}

const InputBase: ForwardRefRenderFunction<HTMLInputElement, InputBaseProps> = (
  { inputType, label, autoComplete, ...rest },
  ref
) => {
  return (
    <md-outlined-text-field
      class={styles.input}
      label={label}
      type={inputType}
      {...rest}
      ref={ref}
      autoComplete={autoComplete}
    ></md-outlined-text-field>
  );
};

export const Input = forwardRef(InputBase);
