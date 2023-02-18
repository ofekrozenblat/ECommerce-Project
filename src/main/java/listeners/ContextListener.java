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
//		  user.setFirstName("Bobby3");
//		  try {
//			user.save();
//			System.out.println("ID: " + user.getId());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		  
		  UserDao userDao = new UserDao();
		  User user;
		try {
			user = userDao.get(18);
			System.out.println(user.getFirstName());
			user.setFirstName("Kool");
			user.save();
			System.out.println(user.getFirstName());
			//user.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
	  }
}
