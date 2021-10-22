pipeline {	

	    environment { 

        registry = "ghassenbogh/devopstimesheet" 
        registryCredential = 'dockerHub'
        dockerImage = '' 
    }

    agent any 

	stages{

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
			
			stage('Cleaning Up'){
				steps{
						sh "docker rmi $registry:$BUILD_NUMBER" 
					}
				}				
			/*
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
			*/
		}
	} 

