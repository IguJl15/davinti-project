import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")

	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "com.davintiproject"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

sourceSets.main {
	kotlin.srcDir("src")
	resources.srcDir("resources")
}

dependencies {
	// Data
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
	implementation("org.flywaydb:flyway-core:9.22.1")
	runtimeOnly("org.postgresql:postgresql:42.5.4")

	// Security
	implementation("com.auth0:java-jwt:4.4.0")
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")
	implementation("org.springframework.boot:spring-boot-starter-security:3.0.4")
	implementation("org.springframework.security:spring-security-oauth2-resource-server:6.1.5")


	// Web
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

	implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")

	// Dev needs
	runtimeOnly("org.springframework.boot:spring-boot-devtools:3.0.4")
//	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
	testImplementation("org.springframework.security:spring-security-test:6.0.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
