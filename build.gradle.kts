import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply(plugin = "war")
apply(plugin = "liberty")
group = "dk.toldst.dms"
version = "0.0.1-SNAPSHOT"
plugins{
	kotlin("jvm") version "1.3.72"
}
java.sourceCompatibility = JavaVersion.VERSION_11
repositories {
	mavenCentral()
}

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("io.openliberty.tools:liberty-gradle-plugin:3.0")
	}
}

dependencies {
		// provided dependencies
	implementation("jakarta.platform:jakarta.jakartaee-api:8.0.0")
	implementation("org.eclipse.microprofile:microprofile:3.3")
	implementation("org.springframework.integration:spring-integration-ftp:5.3.1.RELEASE")


	// test dependencies
	testImplementation ("org.junit.jupiter:junit-jupiter:5.6.2")
	testImplementation ("commons-httpclient:commons-httpclient:3.1")
	implementation(kotlin("stdlib-jdk8"))

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

ext.apply{
	set("liberty.server.var.'default.http.port", "9080")
	set("liberty.server.var.'default.https.port", "9443")
	set("liberty.server.var.'app.context.root", project.name)
}
tasks["clean"].dependsOn("libertyStop")
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}