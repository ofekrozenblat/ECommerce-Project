package listeners;

import java.sql.SQLException;

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
//		  user.setFirstName("Yummy2");
//		  try {
//			user.save();
//			System.out.println("ID: " + user.getId());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		  
		  UserDao userDao = new UserDao();
		  User user = userDao.get(15);
		  user.delete();
		  
		  
	  }
}
