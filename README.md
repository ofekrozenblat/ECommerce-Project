# EECS4413-project
Project for eCommerce.

## Instructions for TA: How to run the application locally

### Step 1: Set up the Tomcat 9 server in Eclipse
The Tomcat 9 server we are using for this project is the same one that are used for the labs.
These instructions are copied from the labs in case you do not have Tomcat 9 installed
1. Install Apache Tomcat (v9.0), https://tomcat.apache.org/download-90.cgi
2. In Java EE perspective, select the Servers tab
3. Create new server, select Apache Tomcat 9
4. Point to the directory where you unzipped Tomcat

### Step 2: Cloning the repository into eclipse
1. Clone this GitHub repository into a directory
2. In Eclipse, go to File -> Import
3. Select Maven -> Existing Maven Projects
4. For Root Directory, browse to where you cloned this GitHub repository and select the directory of the repository
5. Click Finish, the project should now be imported

### Step 3: Setting up the database
You will need to have set up the MySQL Workbench application as that is what we used to set up our local database. If you do not have it installed,
you can install it from here: https://dev.mysql.com/downloads/workbench/. When installing **make sure to install the MySQL server**.
Once you have a local instance of MySQL running, you can setup the database as such:
1. Create a schema called `reagail`
2. Open the SQL file located in the project directory at `EECS4413-project/src/main/sql/regail_schema.sql`
3. Run this SQL file in MySQL workbench
4. In the same folder you will find another file called `dummy_combined_data.sql`, run this file in MySQL workbench as well. This will populate the database with dummy data

### Step 4: Setting up the database connection
1. In Eclipse, go to `context.xml` file located at `EECS4413-project\src\main\webapp\META-INF\context.xml`
2. Open the context.xml file
3. Under the Resource named `jdbc/reagailDB`, you should see the attributes `username` and `password`, these correspond to your local MySQL workbench account that you created. Edit these fields and input your MySQL workbench credentials for your local instance. Once done, save the file

### Step 5: Running the application
1. Right click the project in Eclipse explorer
2. Select: Run As -> Run on server
3. Select the Tomcat 9 server
4. The application should now load in the browser

### Notes
In case the project may not run due to dependency issues, try the following:
1. Right click the project in Eclipse explorer
2. Select: Run As -> Maven install
3. Wait for the Maven installation to finish
4. Re-attempt Step 5
