# webcrawler

This project crawls any given website using the url and counts the frequency of a word reperating

#Building Instructions

mvn clean install

this should build a WAR file.

#Running the project

-- java -jar target/webcrawler-0.0.1-SNAPSHOT.war

starts up the project

-- localhost:8080 

should launch the UI thats should take in the URL and the category.

# Pre-Requisites

1. Java 8 SDK installed.
2. Maven 3.X installed. 

#DB Access

Currently uses HSQLDB, app can easily be configured to run with postgres, Mysql or other databases, by just modifyig the application.properties file.

#DEMO Link

http://sendvid.com/wvmzvanr
