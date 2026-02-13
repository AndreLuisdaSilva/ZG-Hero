pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/AndreLuisdaSilva/ZG-Hero'
      }
    }

    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }

    stage('Test') {
      steps {
        sh './gradlew test'
      }
    }
  }
}
