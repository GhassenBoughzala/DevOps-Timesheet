pipeline {
	agent any 
	
	environment { 
        registry = "amiryazidi/devopstimesheet" 
        registryCredential = 'dockerHub'
        dockerImage = '' 
    }

		stages{

			stage('Clean and package'){
				steps{
					bat "mvn clean package"
					
				}				
			}

			stage('Building Image'){
				steps{
					script{
						dockerImage = docker.build registry + ":$BUILD_NUMBER"
					}
				}				
			}

			stage('Deploy Image'){
				steps{
					script{
						docker.withRegistry( '', registryCredential ) 
                        {dockerImage.push()}
					}
				}
			}

			/*stage('Sonar'){
				steps{
                   bat "mvn sonar:sonar"
                }
			}*/

	}
}