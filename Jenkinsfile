pipeline {
	agent any 
	
	environment { 
        registry = "shadyettaieb/devopstimesheet" 
        registryCredential = 'dockerHub'
        dockerImage = '' 
    }


	stages{

			stage('Clean and package'){
				steps{
					bat "mvn clean package"
					
				}				
			}

			stage('Sonar'){
				steps{
                   bat "mvn sonar:sonar"
                }
			} 

			stage('Deploy'){
				steps{
					bat "mvn deploy"
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

			

		

			
	}
}