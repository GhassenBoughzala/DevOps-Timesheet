pipeline {
	agent any

	stages{

			stage('Clean and test'){
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
			
			stage('Deploy'){
				steps{
				bat "mvn deploy"

				}				
			}
		} 

}
