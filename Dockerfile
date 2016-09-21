FROM	java8
ADD     target/webcrawler*.war /home/default/webcrawler.war

EXPOSE 8080
CMD ["java","-Xmx1G","-Djava.security.egd=file:/dev/./urandom","-jar","/home/default/webcrawler.war"]
