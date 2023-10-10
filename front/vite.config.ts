import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  preview : {
    port: 3001
  },
  server: {
    port: 3001
  }
})
