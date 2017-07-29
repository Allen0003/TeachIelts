package bo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import dao.ContextDao;
import dao.UserDao;
import entity.FileContext;
import entity.User;
import util.Const;

public class IeltsBo {

	public Connection conn;

	UserDao userDao;
	ContextDao contextDao;

	public IeltsBo() throws Exception {
		Class.forName(Const.sqlDriver);
		conn = DriverManager.getConnection(Const.sqlUrl, Const.sqlUsername, Const.sqlPassword);
	}

	
	public boolean setContext(FileContext fileContext) throws Exception{
		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}
		return contextDao.setContext(fileContext);
	}
	
	public ArrayList<FileContext> getContext(String categorization) throws Exception {

		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}

		return contextDao.getContext(categorization);
	}

	public boolean checkLogin(User user) throws Exception {
		if (userDao == null) {
			userDao = new UserDao(this.conn);
		}
		return userDao.checkLogin(user);
	}

	public void disconnect() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

	public void finalize() throws Exception {
		disconnect();
	}
}
