pipeline {
  agent any
  tools {
    maven 'Maven'
  }

  stages {

    stage('Build Maven') {
      steps {
        git branch: 'main', url: 'https://github.com/ashwinihemegowda/real-estate-crud-application'
        sh "mvn -Dmaven.test.failure.ignore=true clean package"
      }
    }
 }
}
