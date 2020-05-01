def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any
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
            perfReport sourceDataFiles: 'target/jmeter/results/*.csv'
        }
    }
    
   stage('Dependency vulnerability tests') {
    steps {
        bat 'mvn dependency-check:check'
        dependencyCheckPublisher failedTotalHigh: '0', unstableTotalHigh: '1', failedTotalNormal: '2', unstableTotalNormal: '5'
    	}
	}
	
	
	stage('Code inspection & quality gate') {
        steps {
                bat 'mvn sonar:sonar'
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