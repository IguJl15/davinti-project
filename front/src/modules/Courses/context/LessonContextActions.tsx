import { LocalStorage } from '../../../core/helpers/local_storage/localStorage';
import Course from '../models/course';

export type LessonProgression = {
  [key: number]: { lesson: number };
};

export class LessonContextActions {
  private localStorage = LocalStorage.instance;

  public getLastConcludedLesson(course: Course): { lesson: number } {
    const progressString = localStorage.getItem('lessons_progress');

    if (!progressString) {
      return this.setProgressAt(course, 1);
    }

    const progress: LessonProgression = JSON.parse(progressString);

    if (!progress[course.id]) {
      return this.setProgressAt(course, 1);
    }

    return progress[course.id];
  }

  public setProgressAt(course: Course, lesson: number) {
    const progressString = localStorage.getItem('lessons_progress') ?? '{}';

    const progress: LessonProgression = JSON.parse(progressString);

    progress[course.id] = { lesson };

    this.localStorage.save('lessons_progress', progress);

    return progress[course.id];
  }
}
