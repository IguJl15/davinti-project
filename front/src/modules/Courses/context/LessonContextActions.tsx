import { LocalStorage } from '../../../core/helpers/local_storage/localStorage';
import Course from '../models/course';

export type CourseProgressionStatus = { lesson: number; concluded: boolean };
export type CoursesProgression = {
  [key: number]: CourseProgressionStatus;
};

export class LessonContextActions {
  private localStorage = LocalStorage.instance;

  public getLastConcludedLesson(course: Course): CourseProgressionStatus {
    const progressString = localStorage.getItem('lessons_progress');

    if (!progressString) {
      return this.startProgress(course);
    }

    const progress: CoursesProgression = JSON.parse(progressString);

    if (!progress[course.id]) {
      return this.startProgress(course);
    }

    return progress[course.id];
  }

  private startProgress(course: Course) {
    return this.setProgressAt(course, {
      lesson: course.lessons.length >= 1 ? 1 : 0,
      concluded: false,
    });
  }

  private setProgressAt(course: Course, status: CourseProgressionStatus) {
    const progressString = localStorage.getItem('lessons_progress') ?? '{}';

    const progress: CoursesProgression = JSON.parse(progressString);

    progress[course.id] = {
      lesson: status.lesson,
      concluded: status.concluded,
    };

    this.localStorage.save('lessons_progress', progress);

    return progress[course.id];
  }

  public goToNextLesson(course: Course) {
    const currentLesson = this.getLastConcludedLesson(course);

    if (course.lessons.length <= currentLesson.lesson) {
      return this.setProgressAt(course, { lesson: course.lessons.length, concluded: true });
    } else {
      return this.setProgressAt(course, { lesson: currentLesson.lesson + 1, concluded: false });
    }
  }
}
