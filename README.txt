RestWebServiceExample www.gerhardkarl.com

GIT
	Sample from gitub https://github.com/mjtoolbox/RestWebServiceTest

	adpted to -> https://github.com/gerhardkarl/GKRestWebService1
	https://github.com/gerhardkarl/GKRestWebService1/tree/master/target/GKRestWebService.war
	
	MAVEN Build: mvn clean compile package -P development wildfly:deploy-only

Wildfly local installation test
	copy https://github.com/gerhardkarl/GKRestWebService1/tree/master/target/GKRestWebService.war 
		to c:\eprog\wildfly\wildfly-8.1.0.Final\standalone\deployments\

	start server: c:\eprog\wildfly\wildfly-8.1.0.Final\bin\standalone.bat

	URLs RestWebServiceExample
	
		http://localhost:8080/GKRestWebService/rs/json/Persons
		http://localhost:8080/GKRestWebService/rs/json/overAge/8
		http://localhost:8080/GKRestWebService/rs/json/PersonsByName?names=Gerhard,Hanne
		http://localhost:8080/GKRestWebService/rs/json/PersonsByNameJson?[Hanne] 

Docker (use windows powershell als administrator!)
	
	docker pull jboss/wildfly

	Dockerfile. c:\gerhardkarlcom\ws-gerhardkarl-services\RestWebServiceTest-master\target\Dockerfile

		FROM jboss/wildfly
		RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
		EXPOSE 8080 8443 9990
		# copy the application
		ADD GKRestWebService.war  /opt/jboss/wildfly/standalone/deployments/

	docker build --tag=wildfly-gkrest-app .

	docker run  -p 9990:9990 -p 8080:8080 -it wildfly-gkrest-app

	##NW# docker run -it wildfly-gkrest-app /opt/jboss/wildfly/bin/domain.sh -b 0.0.0.0 -Djboss.management.http.port=8888 
	##NW# docker run -it -p 8080:8080 -p 9990:9990 wildfly-gkrest-app funktioniert nicht
	##NW# docker run -it -p 8080:8080 wildfly-gkrest-app
	##NW# docker run --expose 9990 -p 9990:9990 -p 8080:8080 -it wildfly-gkrest-app  /opt/jboss/wildfly/bin/domain.sh -b 0.0.0.0 -Djboss.management.http.port=8888 

	docker-machine ip
	docker ps
	docker stop <CONTAINER ID> 
	docker inspect <CONTAINER ID> 

Testurls:
	http://localhost:8080/GKRestWebService/rs/json/Persons
	http://localhost:8080/GKRestWebService/rs/json/overAge/8
	http://localhost:8080/GKRestWebService/rs/json/PersonsByName?names=Gerhard,Hanne
	http://localhost:8080/GKRestWebService/rs/json/PersonsByNameJson?[Hanne] 
	http://localhost:8080/GKRestWebService/clientTest.jsp
	http://localhost:8080/GKRestWebService/index.jsp
