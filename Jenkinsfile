def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
    
    options {
    	buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
  	}

    stages {

     stage ('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        
    stage("Unit test") {
            steps {
                bat 'mvn -B clean test -Pcode-coverage'
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
        
     stage ('Mutation test') {
        	 steps {
                    bat 'mvnw org.pitest:pitest-maven:mutationCoverage'
            }
        }

     stage("Integration test") {
            steps {
                bat "mvn -B verify -Dunit.tests.skip -Pcode-coverage"
            	junit testResults: '**/target/failsafe-reports/TEST-*.xml', allowEmptyResults: true
            }
        }

        stage('Performance test') {
        steps {
            bat 'mvn jmeter:jmeter jmeter:results'
        }
    }
    
   stage('Dependency vulnerability test') {
    steps {
        bat 'mvn dependency-check:check'
    	}
	}

	stage('SonarQube analysis') {
        steps {
                bat 'mvn clean package sonar:sonar'
        }
    }
        
	stage("Nexus deploy") {
			steps {
					bat 'mvn deploy -DskipTests -Dmaven.install.skip=true'
			}
		}
    }

     post {
    		success {
                    echo "MOST DEFINITELY FINISHED"
                }

            failure {
                    echo "I FAILED"
                }

            cleanup {
                    echo "I RAN ANYWAY"
                }
            always {
                    error "I AM FAILING NOW"
                }
    	}
}