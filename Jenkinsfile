pipeline {
	agent any

	stages{

			stage('Clean & Test'){
				steps{
					bat "mvn clean"
					bat "mvn test"
				}				
			}

			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }

            stage('Nexus Deploy'){
				steps{
					bat "mvn deploy"
				}				
			}
		} 

}
