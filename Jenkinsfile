pipeline {	

	agent any 
	
	environment { 
        registry = "ghassenbogh/devopstimesheet" 
        registryCredential = 'dockerHub'
        dockerImage = '' 
    }

	stages{
			
			stage('Clean Package Test'){
					steps{
						bat "mvn clean package"
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

