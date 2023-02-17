package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.UserDao;
import factories.ModelFactory;
import model.User;

public class ContextListener implements ServletContextListener{
	  @Override
	  public void contextDestroyed(ServletContextEvent arg0) {
	    // Do stuff on server shutdown
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent arg0) {
		  // Do stuff on server start up
		  
		  // TESTING
//		  User user = ModelFactory.createUser();
//		  user.setFirstName("Goerge");
//		  user.save();
		  
//		  UserDao userDao = new UserDao();
//		  User user = userDao.get(1);
//		  System.out.println("RETRIEVED: " + user.getFirstName());
		  
	  }
}
