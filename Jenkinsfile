pipeline {
   agent any 
	
	environment { 
        registry = "khaoulaarfaoui/devopstimesheet" 
        registryCredential = 'dockerHub'
        dockerImage = '' 
    }

		stages{

			stage('Clean Package'){
					steps{
						sh "mvn clean package"
					}				
				}
				
			stage('Sonar Analyse'){
				steps{
                    sh "mvn sonar:sonar"
                  }
            }

            stage('Nexus Deploy'){
				steps{
					sh "mvn deploy"
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


} }
