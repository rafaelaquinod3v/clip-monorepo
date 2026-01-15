plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
  kotlin("plugin.jpa") version "1.9.22"
	id("org.springframework.boot") version "4.0.1"
	id("io.spring.dependency-management") version "1.1.7"
  id("org.flywaydb.flyway") version "11.20.1"
}

group = "sv.com.clip"
version = "0.0.1-SNAPSHOT"
description = "Clip Learn English "

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springModulithVersion"] = "2.0.1"

dependencies {
  // Dependencia principal de Flyway
  implementation("org.flywaydb:flyway-core:11.20.1")

  // IMPORTANTE: A partir de Flyway 10, debes añadir el módulo específico de tu base de datos
  // Ejemplo para PostgreSQL o MySQL:
  implementation("org.flywaydb:flyway-database-postgresql:11.20.1")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.modulith:spring-modulith-starter-core")
  implementation("org.springframework.modulith:spring-modulith-events-api")
  runtimeOnly("org.springframework.modulith:spring-modulith-starter-jpa")
  implementation("tools.jackson.module:jackson-module-kotlin")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.modulith:spring-modulith-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  // Importar el BOM para manejar versiones
  implementation(platform("org.jmolecules:jmolecules-bom:2025.0.2")) // Verifica la última versión en 2026

  // Conceptos básicos de DDD (Anotaciones)
  implementation("org.jmolecules:jmolecules-ddd")

  // Si usas Kotlin, puedes usar kMolecules para una mejor experiencia idiomática
  implementation("org.jmolecules:kmolecules-ddd")
  implementation("org.jmolecules.integrations:jmolecules-spring")
  implementation("org.jmolecules.integrations:jmolecules-jpa")

  // Driver oficial de PostgreSQL
  runtimeOnly("org.postgresql:postgresql")

  // Abstracción para persistencia de datos
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  implementation("com.opencsv:opencsv:5.9")

  // pgvector para Java/Kotlin
  implementation("com.pgvector:pgvector:0.1.6")
  implementation("org.hibernate.orm:hibernate-vector:7.2.0.Final")

  //implementation("com.pgvector:pgvector:0.2.0")


  // Importar el BOM para gestionar versiones automáticamente
  implementation(platform("ai.djl:bom:0.31.1")) // Versión estable en 2026

  // API principal de DJL
  implementation("ai.djl:api")

  // Motor de PyTorch y sus archivos JNI
  implementation("ai.djl.pytorch:pytorch-engine")

  // ESTA ES LA CLAVE: Incluye los binarios nativos para que no intente descargarlos
  // El clasificador "linux-x86_64" es para tu entorno WSL
  runtimeOnly("ai.djl.pytorch:pytorch-native-cpu::linux-x86_64")

  // Para procesamiento de texto (Embeddings)
  implementation("ai.djl:model-zoo")
  //implementation("ai.djl.pytorch:pytorch-jni")
  // DJL para Embeddings Locales (Free)
  //implementation("ai.djl:api:0.31.1")
  implementation("ai.djl.huggingface:tokenizers:0.31.0")
  //runtimeOnly("ai.djl.pytorch:pytorch-engine:0.31.0")
 // runtimeOnly("ai.djl.pytorch:pytorch-native-cpu:2.1.1") // O gpu si prefieres
  // Standard DJL PyTorch Engine
  //implementation("ai.djl.pytorch:pytorch-engine:0.26.0") // Match your current DJL version

  // Add the specific native library for your platform (Linux x86_64)
  //implementation("ai.djl.pytorch:pytorch-native-cpu:2.1.1:linux-x86_64")

  // Ensure the JNI adapter is present
  //implementation("ai.djl.pytorch:pytorch-jni:0.26.0")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
