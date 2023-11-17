import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [react()],
    server: {
        proxy: {
            '/api': 'http://localhost:8080'
        }
    },
    build: {
        rollupOptions: {
            input: 'src/main.jsx', // Specify the correct entry point
        },
    },
});
