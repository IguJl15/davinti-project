import { ForwardRefRenderFunction, forwardRef } from "react";
import style from "./style.module.css"

interface InputBaseProps {
  inputType: string;
  label: string;
  autoComplete?: string
}

const InputBase: ForwardRefRenderFunction<HTMLInputElement, InputBaseProps> = (
  { inputType, label, autoComplete, ...rest },
  ref
) => {
  return (
    <div className={style.input_text_wrapper}>
      <label>{label}</label>
      <input type={inputType} {...rest} ref={ref} autoComplete={autoComplete} />
    </div>
  );
};

export const Input = forwardRef(InputBase);
