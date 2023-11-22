// fazer if para saber se o professor tem cursos ou nao, e assim decidir o que vai ser posto em tela
import { PropsWithChildren } from "react"
import style from "./style.module.css"

function CourseListCard({ children }:PropsWithChildren){
  return (
    <div className={style.card}>
      {children}
    </div>  
  )
}

export { CourseListCard  }