class LocalStorage {
     private constructor() { }

     static instanceof: LocalStorage = new LocalStorage();

     read<T>(key: string): T | null {
          const storedData = window.localStorage.getItem(key)

          if (!storedData) {
               return null
          }

          return JSON.parse(storedData) as T
     }

     save(key: string, data: object) {
          window.localStorage.setItem(key, JSON.stringify(data));
     }

     delete(key: string): void {
          window.localStorage.removeItem(key);
     }
} 