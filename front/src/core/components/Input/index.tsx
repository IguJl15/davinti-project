// @ts-nocheck
import { ForwardRefRenderFunction, forwardRef } from 'react';
import '@material/web/textfield/outlined-text-field';
import styles from './style.module.css';

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
    <div>
      {/* https://github.com/material-components/material-web/blob/main/docs/components/text-field.md */}
      <md-outlined-text-field
        class={styles.input}
        label={label}
        type={inputType}
        {...rest}
        ref={ref}
        autoComplete={autoComplete}
      />
    </div>
  );
};

export const Input = forwardRef(InputBase);
