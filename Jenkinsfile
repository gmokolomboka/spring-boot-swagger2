def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
    stages {
        stage ('Compile Stage') {
            steps {
                    bat 'mvn clean compile'
            }
        }
        
        stage ('Test Stage') {
            steps {
                    bat 'mvn clean test'
            }
        }
        
        stage ('Mutation test Stage') {
        	 steps {
                    bat 'mvnw org.pitest:pitest-maven:mutationCoverage'
            }
        }
        
        stage ('Install Stage') {
            steps {
                    bat 'mvn install'
            }
        }
        
        
        
    }
}