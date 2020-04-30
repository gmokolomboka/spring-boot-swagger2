def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvn clean compile'
                }
            }
        }
        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvn test'
                    junit 'target/surefire-reports/*.xml'
            		jacoco execPattern: 'target/jacoco.exec'
                }
            }
        }
        
        stage ('Mutation test Stage') {
        		 steps {
                withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvnw org.pitest:pitest-maven:mutationCoverage'
                }
            }
        }
        
        stage ('Install Stage') {
            steps {
                withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvn install'
                }
            }
        }
        
       stage('Package') {
        steps {
         withMaven(maven : 'apache-maven-3.6.1') {
            bat 'mvn package -DskipTests'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    
    stage('Integration tests') {
        steps {
         withMaven(maven : 'apache-maven-3.6.1') {
            bat 'mvn failsafe:integration-test failsafe:verify'
        }
        }
    }
        
    }
}