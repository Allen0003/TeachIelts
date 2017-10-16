package bo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import dao.AWSSQL;
import dao.ContextDao;
import dao.HomePageDao;
import dao.ManuBarDao;
import dao.UserDao;
import entity.FileContext;
import entity.ManuBar;
import entity.User;
import util.Const;

public class IeltsBo {

	public Connection conn;

	UserDao userDao;
	ContextDao contextDao;
	ManuBarDao manuBarDao;
	HomePageDao homePageDao;
	AWSSQL awsSQL;

	public IeltsBo() throws Exception {
		Class.forName(Const.sqlDriver);
		conn = DriverManager.getConnection(Const.sqlUrl, Const.sqlUsername, Const.sqlPassword);
	}

	public String getHomeContext() throws Exception {
		if (homePageDao == null) {
			homePageDao = new HomePageDao(conn);
		}
		return homePageDao.getHomePage();
	}

	public void updHomeContext(String input) throws Exception {
		if (homePageDao == null) {
			homePageDao = new HomePageDao(conn);
		}
		try {
			conn.setAutoCommit(false);
			homePageDao.delHomePage();
			homePageDao.addHomePage(input);
			this.conn.commit();
		} catch (Exception e) {
			try {
				this.conn.rollback();
			} catch (Exception e1) {
				throw new Exception(e1);
			}
			throw new Exception(e);
		} finally {
			try {
				this.conn.setAutoCommit(true);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

	public boolean setContext(FileContext fileContext) throws Exception {
		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}
		return contextDao.setContext(fileContext);
	}

	public boolean delContext(int del_id) throws Exception {
		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}
		return contextDao.delContext(del_id);
	}

	public ArrayList<ManuBar> getManuBars() throws Exception {
		if (manuBarDao == null) {
			manuBarDao = new ManuBarDao(this.conn);
		}
		return manuBarDao.getManuBar();
	}

	public ArrayList<FileContext> getContext(String categorization) throws Exception {
		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}
		return contextDao.getContext(categorization);
	}

	public ArrayList<FileContext> getContext() throws Exception {
		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}
		return contextDao.getContext();
	}

	public boolean updContext(FileContext fileContext) throws Exception {
		if (contextDao == null) {
			contextDao = new ContextDao(this.conn);
		}
		return contextDao.updContext(fileContext);
	}

	public boolean checkLogin(User user) throws Exception {
		if (userDao == null) {
			userDao = new UserDao(this.conn);
		}
		return userDao.checkLogin(user);
	}

	public void createIeltsDB() throws Exception {
		if (awsSQL == null) {
			awsSQL = new AWSSQL(this.conn);
		}
		awsSQL.createDB();
	}

	public void createTable() throws Exception {
		if (awsSQL == null) {
			awsSQL = new AWSSQL(this.conn);
		}
		awsSQL.createTable();
	}

	public void seeAll() throws Exception {
		if (awsSQL == null) {
			awsSQL = new AWSSQL(this.conn);
		}
		awsSQL.seeAll();
	}

	public void insertOne() throws Exception {
		if (awsSQL == null) {
			awsSQL = new AWSSQL(this.conn);
		}
		awsSQL.insertOne();
	}

	public void updManuBar(ArrayList<ManuBar> inpus) throws Exception {
		if (manuBarDao == null) {
			manuBarDao = new ManuBarDao(conn);
		}
		try {
			conn.setAutoCommit(false);
			manuBarDao.delManuBar();
			manuBarDao.addManuBars(inpus);
			this.conn.commit();
		} catch (Exception e) {
			try {
				this.conn.rollback();
			} catch (Exception e1) {
				throw new Exception(e1);
			}
			throw new Exception(e);
		} finally {
			try {
				this.conn.setAutoCommit(true);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
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
