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

     save(key: string, data: any) {
          window.localStorage.setItem(key, data);
     }

     delete(key: string): void {
          window.localStorage.removeItem(key);
     }
} 