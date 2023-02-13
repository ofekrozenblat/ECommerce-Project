package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.User;

public class ContextListener implements ServletContextListener{
	  @Override
	  public void contextDestroyed(ServletContextEvent arg0) {
	    //do stuff
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent arg0) {
		 System.out.println("START UP LISTENER");
		 User user = new User("John");
		 user.save();
	  }
}
