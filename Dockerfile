FROM maven:3.6.3-openjdk-8
COPY . /home/mydockertest 
#RUN mvn -f /home/mydockertest/pom.xml clean test