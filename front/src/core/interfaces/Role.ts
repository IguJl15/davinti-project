export enum Role {
  STUDENT,
  ADMIN,
}

export function parseRole(value: string) {
  switch (value.trim().toLocaleLowerCase()) {
    case "user":
    case "student":
    case "role_user":
    case "role_student":
      return Role.STUDENT;

    // case "instructor":
    // case "role_instructor":
    //   return Role.INSTRUCTOR;

    case "admin":
    case "role_admin":
      return Role.ADMIN;

    default:
      return Role.STUDENT;
  }
}
