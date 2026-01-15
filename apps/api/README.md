src/main/kotlin/sv/com/clip
│
├── library/               <-- Gestión de Fuentes (Videos, Libros, Noticias)
│   ├── api/               <-- Endpoints para subir videos, scrapear noticias
│   ├── application/       <-- Use Cases: "ProcessVideoContent", "ImportNews"
│   ├── domain/            <-- Entities: ContentSource, MediaResource (Value Objects para URL, Duración)
│   └── infrastructure/    <-- Adapters: YouTubeClient, S3Storage, NewsScraper
│
├── course/                <-- Gestión de la estructura educativa
│   ├── api/               <-- Endpoints para que el alumno vea su curso
│   ├── application/       <-- Use Cases: "AddContentToLesson"
│   ├── domain/            <-- Entities: Course, Lesson (solo referencias al ID del contenido)
│   └── infrastructure/    <-- Persistencia de la estructura del curso
│
├── student/               <-- Gestión de Estudiantes
│   ├── api/
│   ├── application/
│   ├── domain/
│   └── infrastructure/
│
├── progression/           <-- Gamificación/Motivación (Puntos, Logros, Niveles, Rachas)
│   ├── api/
│   ├── application/
│   ├── domain/
│   └── infrastructure/
│
├── vocabulary/            <-- (Palabras, Análisis de texto)
│
└── shared/                <-- Kernel Diferido (Código común a módulos)
