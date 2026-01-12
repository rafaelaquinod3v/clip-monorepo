src/main/kotlin/sv/com/clip
│
├── content/               <-- NUEVO: Gestión de fuentes originales
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
├── student/               <-- Módulo (Bounded Context): Gestión de Estudiantes
│   ├── api/
│   ├── application/
│   ├── domain/
│   └── infrastructure/
│
├── progression/           <-- Módulo (Bounded Context): Gamificación/Progreso
│   ├── api/
│   ├── application/
│   ├── domain/
│   └── infrastructure/
│
└── shared/                <-- Kernel Diferido (Código común a módulos)
