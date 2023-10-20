import { ForwardRefRenderFunction, forwardRef } from "react";
import style from "./style.module.css"

interface InputBaseProps {
  inputType: string;
  label: string;
}

const InputBase: ForwardRefRenderFunction<HTMLInputElement, InputBaseProps> = (
  { inputType, label, ...rest },
  ref
) => {
  return (
    <div className={style.input_text_wrapper}>
      <label>{label}</label>
      <input type={inputType} {...rest} ref={ref} />
    </div>
  );
};

export const Input = forwardRef(InputBase);
