package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Init implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Util.setCategorization();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
