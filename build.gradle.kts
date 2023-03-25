
plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.8.10'
    id 'application'
}

repositories {
    mavenCentral()
}

dependencies {
    // Tests
    testImplementation(Testing.junit4)
    testImplementation(Kotlin.test.testng)
}

application {
    mainClass = 'MainKt.class'
}

tasks.named('test') {
    useJUnitPlatform()
}