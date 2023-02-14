package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
	  }
}
