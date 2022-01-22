pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'ashwini',url: 'https://github.com/ashwinihemegowda/real-estate-crud-application'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('publish to nexus'){
            steps{
                nexusArtifactUploader artifacts: [
                    [artifactId: 'realestatepro', classifier: '',
                    file: 'target/realestatepro-0.0.1-SNAPSHOT.jar',
                    type: 'jar']],
                    credentialsId: 'NEXUS_CRED',
                    groupId: 'com.thbs.realestate',
                    nexusUrl: '192.168.0.180:8081',
                    nexusVersion: 'nexus3', protocol: 'http',
                    [artifactId: 'realestatepro', classifier: '', 
                    file: 'target/realestatepro-0.0.1-SNAPSHOT.jar', 
                    type: 'jar']], 
                    credentialsId: 'NEXUS_CRED',
                    groupId: 'com.thbs.realestate', 
                    nexusUrl: '192.168.0.180:8081', 
                    nexusVersion: 'nexus3', protocol: 'http',
                    repository: 'maven-central-repository', version: '0.0.1-SNAPSHOT'
            }
        }
    }
}

