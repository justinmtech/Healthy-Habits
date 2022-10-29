# Healthy-Habits
A web-app for self-improvement and habit-tracking that was built with Java, Spring, Hibernate, HTML, CSS and JavaScript.

# Setup
- Download the latest JAR file and place it into a folder of its own preferably. 
- Run the jar file in a terminal with java -jar <jarFileName>.jar.
- If you get an error, be sure you have the correct settings in application.properties.

# Configuration
Be sure to include an "application.properties" file in the same directory as the JAR. It will not run properly if the SQL properties are not correct. If it fails to create the tables on the first run, try setting hibernate-ddl-auto to 'create-drop', then you can change it back afterwards.

- server.port = 2053
- server.address = localhost
- spring.datasource.url = jdbc:mysql://localhost:3306/server
- spring.datasource.username = root
- spring.datasource.password = password
- spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
- spring.jpa.hibernate.ddl-auto = update
- spring.jpa.properties.hibernate.format_sql=true
