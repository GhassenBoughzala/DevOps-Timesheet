pipeline {
	agent any

	stages{

			stage('Clean'){
				steps{
					bat "mvn clean"
				}				
			}

			stage('Test'){
				steps{
					bat "mvn test"
				}				
			}

			stage('Deploy'){
				steps{
					bat "mvn package"
					bat "mvn deploy"
				}				
			}

			stage('Sonar'){
				steps{
                    script{
                      withSonarQubeEnv('sonarserver') { 
                      sh "mvn sonar:sonar"
                       }
                  }
                }
			} 

			
	}
}