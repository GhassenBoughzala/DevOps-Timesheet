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

			stage('Deploy Nexus'){
				steps{
					bat "mvn package"
					bat "mvn deploy"
				}				
			}

			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
                }
			} 

			
	}
}