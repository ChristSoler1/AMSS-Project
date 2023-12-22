# Einstieg

### Referenzdokumentation

Für weitere Informationen ziehen Sie bitte die folgenden Abschnitte in Betracht:

* [Offizielle Apache Maven Dokumentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Referenzanleitung](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/)
* [Erstellen eines OCI-Images](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web)

### Anleitungen

Die folgenden Anleitungen veranschaulichen, wie man einige Funktionen konkret nutzt:

* [Zugriff auf Daten mit MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Zugriff auf Daten mit JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Erstellung eines RESTful Web Services](https://spring.io/guides/gs/rest-service/)
* [Bereitstellung von Webinhalten](https://spring.io/guides/tutorials/rest/)
* 
### Folgendes zu beachten:

* Datenbank User ist konfiguriert für User = root / Passwort = password / port: 3306 (Kann im application.properties angepasst werden)
* Unter "src/main/resources/application.properties" kann man den Port ändern
* Frontend wird über Node.js gestartet mit dem Befehl "npm start" im Frontend Ordner (Terminal oder Powershell)
* Installation vom Server wird je nach benötigt mit Befehl "npm install" im Frontend Ordner (Terminal oder Powershell)