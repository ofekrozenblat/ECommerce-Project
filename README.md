# Project Team
- Ofek Rozenblat
- Shachak Rozenblat
- Syyed Fatmi

## How to run the application locally

### Step 1: Set up the Tomcat 9 server in Eclipse
1. Install Apache Tomcat (v9.0), https://tomcat.apache.org/download-90.cgi.
2. In Java EE perspective, select the Servers tab.
3. Create new server, select Apache Tomcat 9.
4. Point to the directory where you unzipped Tomcat.

### Step 2: Cloning the repository into Eclipse
1. Clone this GitHub repository into a directory.
2. In Eclipse, go to File -> Import.
3. Select Maven -> Existing Maven Projects.
4. For Root Directory, browse to where you cloned this GitHub repository and select the directory of the repository.
5. Click Finish, the project should now be imported.

### Step 3: Setting up the database
You will need to have set up the MySQL Workbench application as that is what we used to set up our local database. If you do not have it installed,
you can install it from here: https://dev.mysql.com/downloads/workbench/. When installing **make sure to install the MySQL server**.
Once you have a local instance of MySQL running, you can setup the database as such:
1. Create a schema called `reagail`.
2. Open the SQL file located in the project directory at `/src/main/sql/regail_schema.sql`.
3. Run this SQL file in MySQL workbench.
4. In the same folder you will find another file called `dummy_glasses_data.sql`, run this file in MySQL workbench as well. This will populate the database with dummy data.

### Step 4: Setting up the database connection
1. In Eclipse, go to `context.xml` file located at `\src\main\webapp\META-INF\context.xml`.
2. Open the context.xml file.
3. Under the Resource named `jdbc/reagailDB`, you should see the attributes `username` and `password`, these correspond to your local MySQL workbench account that you created. Edit these fields and input your MySQL workbench credentials for your local instance. Once done, save the file.

### Step 5: Running the application
1. Right click the project in Eclipse explorer.
2. Select: Run As -> Run on server.
3. Select the Tomcat 9 server.
4. The application should now load in the browser.

### Notes
In case the project may not run due to dependency issues, try the following:
1. In the Eclipse at the top, there is a tab called `Project`. Select `Project`->`Clean`, and select this project to be cleaned.
2. After cleaning, right click the project in Eclipse explorer.
3. Select: Run As -> Maven install.
4. Wait for the Maven installation to finish.
5. Re-attempt Step 5.

### How to make a user an Admin
If you would like to test our Admin features, follow these steps:
1. Run the project.
2. Click `Sign Up`.
3. Enter the fields as neccessary and click `Sign Up`.
4. You should now be logged in as a registered user.
5. Go to Eclipse and stop the running project.
6. Go to your MySQL workbench application, open the `reagail` schema.
7. Go to the table called `users`. You should see a row with the credentials of your newly created account.
8. There is a column name `is_admin`, this controls whether the account is an admin (is_admin=1) or not an admin (is_admin=0). Create the SQL statement
`UPDATE reagail.users SET is_admin=1 WHERE id=[YOUR ACCOUNT ID HERE]`, where [YOUR ACCOUNT ID HERE] is the `id` column of your account row in the users table.
9. Execute the SQL statement, your account should now have `is_admin` set to 1.
10. Re-run the project.
11. Login with the same account, once looged in your account should now be an Admin.
12. In the browser, on the top right next to the cart you should see your account name. Clicking it will provide a drop down menu which should show an `Admin` option. Clicking it will navigate you to our admin page where you can test our admin features.
