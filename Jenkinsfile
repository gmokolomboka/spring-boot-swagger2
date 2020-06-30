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
        bat 'mvn org.owasp:dependency-check-maven:aggregate -Dformat=xml'
    	}
	}
/*
	stage('SonarQube analysis') {
        steps {
                bat 'mvn clean package sonar:sonar'
        }
    }
 */
	stage("Nexus deploy") {
			steps {
					bat 'mvn deploy -DskipTests -Dmaven.install.skip=true'
			}
		}
    /*
    stage('Deploy on dev to test'){
            steps {
                dir('deployment'){
                    echo 'Deploying to test'
                    bat 'wsl ansible-playbook -i dev-servers site.yml'
                }
            }
        }
     */
    }

post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'I succeeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed '
        }
        changed {
            echo 'Things were different before...'
        }
    }
}