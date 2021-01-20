# Assessment-test (Dockerized Selenium-java-Cucumber-BDD Tests for ease of distribution)
Assessment test framework to scan a web page to validate on the below
- There are no console errors on page loads
- The response code from the page (200, 302, 404, etc.)
- All links on the page go to another live (non 4xx) page

# Getting Started
Pre-requisite:
- Install Docker
- Clone the project to local 
- Open command prompt/terminal/powershell from the root of the project where docker-compose.yml is located
- Execute the below docker commands to start the test

Use docker-compose command to run the setup and start the test execution.

    docker-compose up

The above command will pull the images:selenium/standalone-chrome & selenium/standalone-firefox, build app image, create and run Docker containers as specified in the docker-compose.yml file.

To clean the environment

    docker-compose down

Network

The networks configured in the docker-compose.yml file will establish the connectivity between the docker containers to communicate each other.

Volume

    volumes:

    - ./target/cucumberHtmlReports:/home/mydockertest/target/cucumberHtmlReports
./target/cucumberHtmlReports is in the current directory where you run docker-compose.

When the tests are executed with docker-compose, the report will be saved inside a container where they run. A volume(already configured in the docker-compose.yml) is needed to copy the reports outside the container(to the local machine).

# Reports

When the test execution completed the scan report will be available under./target/cucumberHtmlReports.
  - Open the html report file with the name-webPageScanReport.html





