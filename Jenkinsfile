def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
    
    options {
    	buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
  	}
    
    
    stages {

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
    
    
     stage("Integration tests") {
            steps {
                bat "mvn -B verify -Dunit.tests.skip -Pcode-coverage"
            	junit testResults: '**/target/failsafe-reports/TEST-*.xml', allowEmptyResults: true
            }
        }
        
        
        stage('Performance tests') {
        steps {
            bat 'mvn jmeter:jmeter jmeter:results'
        }
    }
    
   stage('Dependency vulnerability tests') {
    steps {
        bat 'mvn dependency-check:check'
    	}
	}
	
	
	stage('Code inspection & quality gate') {
        steps {
                bat 'mvn sonar:sonar -Dsonar.branch=${GIT_BRANCH}" -Dsonar.host.url=http://127.0.0.1:9000 -Dsonar.login=8456ddc4a9979e492429ffc1dce39e969db83c28'
        }   
    }
        
    stage('Package stage') {
        steps {
            bat 'mvn package -DskipTests'
             archiveArtifacts artifacts: 'target/*.jar', fingerprint: false
            }
    } 
        
    }
}