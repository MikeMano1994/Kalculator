FROM anapsix/alpine-java
LABEL maintainer="manoj@cogzidel.com"
COPY /target/spring-petclinic-1.5.1.jar /home/test/Downloads/spring-petclinic-1.5.1.jar
CMD ["java","-jar","/home/test/Downloads/spring-petclinic-1.5.1.jar"]
