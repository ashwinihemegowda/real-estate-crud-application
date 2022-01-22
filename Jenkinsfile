pipeline {
agent any
tools {
maven 'Maven'
}

stages {

  stage('Build Maven') {
    steps {
      git branch: 'ashwini',url: 'https://github.com/ashwinihemegowda/real-estate-crud-application'
      sh "mvn -Dmaven.test.failure.ignore=true clean package"
    }
  }
  stage("Publish to Nexus Repository Manager") {

    steps {

      script {

        pom = readMavenPom file: "pom.xml";

        filesByGlob = findFiles(glob: "target/*.${pom.packaging}");

        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"

        artifactPath = filesByGlob[0].path;

        artifactExists = fileExists artifactPath;

        if (artifactExists) {

          echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

          nexusArtifactUploader(
            nexusVersion: 'nexus3',

            protocol: 'http',

            nexusUrl: '192.168.0.180:8081',

            groupId: 'pom.com.thbs.realestate',

            version: 'pom.0.0.1-SNAPSHOT',

            repository: 'maven-central-repository',

            credentialsId: 'NEXUS_CREDENTIAL',

            artifacts: [

              [artifactId: 'pom.realestatepro',

                classifier: '',

                file: artifactPath,

                type: pom.packaging
              ],

              [artifactId: 'pom.realestatepro',

                classifier: '',

                file: "pom.xml",

                type: "pom"
              ]

            ]

          );

        } else {

          error "*** File: ${artifactPath}, could not be found";

        }

      }

    }

  }
}

}
