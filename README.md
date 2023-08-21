# financial-system-apis
This is a demo for financial system APIs.
Using Java8, spring boot

* Prerequisite Software installed
  * Docker platform(run using docker platform)
  * Postgres(run manually) 
* Get started (Docker)
  * Go to project folder path.
  * Run docker command > docker-compose up -d  --build --force-recreate
  * While two containers(db, application-server) are up and running.
  * You can call swagger UI to test APIs > http://localhost:8084/api/v1/swagger-ui/index.html#/
* Get started (CMD)
    * Go to Postgres client software or using psql and create database with name "bank-system".
    * run mvn clean install.
    * Go to the created target folder.
    * Run the jar file using command > java -jar bank-system.jar.
    * You can call swagger UI to test APIs > http://localhost:8083/api/v1/swagger-ui/index.html#/
