def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
    stages {
        stage ('Compile Stage') {
            steps {
                    bat 'mvn clean compile'
            }
        }
    }
}