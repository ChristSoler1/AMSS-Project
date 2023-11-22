
@author: Christ Solèr
@edited: 15.11.2023

# Used technologies
- Java version 17
- MySQL
- Spring Framework

---

# Install IDE
install the IDE
- Install IntelJ (for free as student)
- Open the Project Folder, when opening, few depenecies will be installed (Maven, Spring, Java JDK)
- reference: https://www.youtube.com/watch?v=viNG3VVnzFE&ab_channel=GeekyScript
- reference: https://www.youtube.com/watch?v=kQ6Zkb6s6mM&ab_channel=CodeJava


---
# Setup the Database
install and create database
- Install MySQL Community Server Workbench: https://dev.mysql.com/downloads/mysql/
- Configure name = "root" and passwords = "password="
- Import the "kanban_dashboard.sql" file to the server (in Workbench)
- Create a database "CREATE DATABASE `kanban_dashboard`;" (either in Intelj or workbench)
- Execute it
- When created the database should include table with modules, performance_records, priorities, task_statuses and tasks
- reference https://www.youtube.com/watch?v=jW5lrS6EUPM&ab_channel=HostGator
- If needed you can change the name and password for the configured server: "/src/main/resources/application.properties"

spring.datasource.username=root / spring.datasource.password=password

---

pom.xml includes the dependencies. Should be installed automatically by the IDE
Alot of files where created by the Spring Initializer





