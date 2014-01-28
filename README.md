spring-ejb3-webapp
=============

This application demonstrates the interoperability between spring mvc and ejb3 SLSB layer.

The application is a sample blog webapp styled with twitter bootstrap v2.0.

To build and run the application follow this install instructions:

1. Download and unzip jboss-as-7.1.1.zip.
2. Clone this repository
3. You need java 6 and maven 3 to build the application
4. Start the jboss application server with standalone.sh or standalone.bat
5. To build and deploy call mvn clean package jboss-as:deploy (make sure, that jboss is running)

To redeploy call mvn clean package jboss-as:redpeloy
To undeploy the application call jboss-as:undeploy

To generate java classes from wsdl run the following command 
1. mvn clean generate-sources
2. In the ide ( eclipse or jboss dev studio ) right click on the generated sources folder 
and select the option "Use as source folder" . This helps the ide to discover the generated
classes.

To install google closurel linter ( javascript coding style validator ) follow the instructions 
as described in the link below
https://developers.google.com/closure/utilities/docs/linter_howto
On doing a mvn clean package the linter gets invoked as a part of the build process and the js files 
gets validated.


To configure an external datasource like mysql in jboss application server. Follow the instructions
as described in the below link.
https://zorq.net/b/2011/07/12/adding-a-mysql-datasource-to-jboss-as-7/

