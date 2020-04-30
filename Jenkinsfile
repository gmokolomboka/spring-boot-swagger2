@Library('rss-lib') _

def gitUrl = "https://github.com/gmokolomboka/spring-boot-swagger2"

pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
        timeout(time: 45, unit: 'MINUTES')
     }

    environment {
		PROJECT_VERSION = readMavenPom().getVersion()
	}

	parameters {
		booleanParam(name: "NEXUS_DEPLOY",
						description: "Force deployment of generated artifact to Nexus?",
						defaultValue: false)
	}

    stages {

    	stage("Start") {
			steps {
				postBuildResult steps:this, gitUrl: "${gitUrl}", buildResult: 'pending', label: 'build-pending'
			}
		}

        stage("Check code format") {
            when {
                not {
                    branch 'develop'
                }
            }
            steps {
                withMaven(maven: 'maven-3.5.2') {
                    sh "mvn -B -Pdev spotless:check"
                }
            }
        }

        stage("Unit tests") {
            steps {
                withMaven(maven: 'maven-3.5.2', options: [junitPublisher(disabled: true)]) {
                    sh "mvn -B clean test -Pcode-coverage"
                }
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }

        stage("Integration tests") {
            steps {
                withMaven(maven: 'maven-3.5.2', options: [junitPublisher(disabled: true)]) {
                    sh "mvn -B verify -Dunit.tests.skip -Pcode-coverage"
                }
                junit testResults: '**/target/failsafe-reports/TEST-*.xml', allowEmptyResults: true
            }
        }

		stage("Nexus deploy") {
			when {
				anyOf {
					branch 'develop'
					branch pattern: "(^(hotfix|release)\\/\\d.*)", comparator: "REGEXP"
					expression { params.NEXUS_DEPLOY }
				}
			}
			steps {
				withMaven(maven: 'maven-3.5.2', options: [junitPublisher(disabled: true)]) {
					sh "mvn -B -U deploy -Prelease,ci,code-coverage -Dunit.tests.skip=true -Dintegration.tests.skip=true"
				}
			}
		}
    }

    post {
		success {
			postBuildResult steps:this, gitUrl: "${gitUrl}", buildResult: 'success', label: 'build-success'
		}
		failure {
			postBuildResult steps:this, gitUrl: "${gitUrl}", buildResult: 'failure', label: 'build-failure'
			emailext to: "mokolommboka@gmail.com",
					 subject: "Jenkins Build for Sample ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
					 body: "${currentBuild.currentResult}: ${env.JOB_NAME} - ${env.BUILD_NUMBER} Build failure\n More info at: ${env.BUILD_URL}"
		}
		aborted {
			postBuildResult steps:this, gitUrl: "${gitUrl}", buildResult: 'aborted', label: 'build-aborted'
		}
		always {
			cleanWs()
		}
	}
}