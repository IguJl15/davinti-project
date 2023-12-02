import { PropsWithChildren } from "react";

function ErrorMessage({ children }: PropsWithChildren) {
  return <p style={{ color: "red", fontSize: "1rem" }}>{children}</p>;
}

export { ErrorMessage };
