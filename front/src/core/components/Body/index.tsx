import { PropsWithChildren } from "react";
import styles from './style.module.css'

function Body({ children }: PropsWithChildren){
  return (
    <main className={styles.main}>
      {children}
    </main>
  )
}

export { Body }