def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
    stages {
     
     stage("Check code format") {
            steps {
                    bat 'mvn -B -Pdev spotless:check'
            }
        }
        
     stage ('Compile Stage') {
            steps {
                    bat 'mvn clean compile'
            }
        }
        
    stage("Unit tests Stage") {
            steps {
                bat 'mvn -B clean test -Pcode-coverage'
                junit '**/target/surefire-reports/TEST-*.xml'
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