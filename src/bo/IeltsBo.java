package bo;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.UserDao;
import entity.User;
import util.Const;

public class IeltsBo {

	public Connection conn;

	UserDao userDao;

	public IeltsBo() throws Exception {
		Class.forName(Const.sqlDriver);
		conn = DriverManager.getConnection(Const.sqlUrl, Const.sqlUsername, Const.sqlPassword);
	}

	public boolean checkLogin(User user) throws Exception {
		if (userDao == null) {
			userDao = new UserDao(this.conn);
		}
		return userDao.checkLogin(user);
	}

	public void disconnect() throws Throwable {
		if (conn != null) {
			conn.close();
		}
	}

	public void finalize() throws Throwable {
		disconnect();
	}
}
