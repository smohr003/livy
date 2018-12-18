# livy

This repository shows an example of writing Spark applications in scala, 
which can be called using Apache Livy interface. 
The goal is have a reference style for this common functionality which is not well documented anywhere else. 
The Livy Test project contain sample scala classes which can be called by the 
Livy Caller Project. 


To run these, first compile the Livy test project and store the jar file in a location
that would be accessible for Livy Caller project. You have to send the location of jar for uploading
to the cluster. In a real scenario, you will have the jar uploaded as part of deployment in the cluster. 

