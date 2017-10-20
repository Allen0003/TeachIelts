package util;

import java.util.HashMap;
import java.util.logging.Logger;

public class Const {

	public final static int sqlOK = 1;

	public final static String systemName = "TeachIelts";

	public final static String sqlDriver = "com.mysql.jdbc.Driver";

	public final static String sqlUrl = "jdbc:mysql://localhost:3306";

	// public final static String sqlUrl =
	// "jdbc:mysql://ielts.cl1vrrp07ocv.us-east-2.rds.amazonaws.com:3306";

	public final static String sqlUsername = "root";
	public final static String sqlPassword = "apss1943";
	public static final Logger LOGGER = Logger.getLogger(systemName);

	public final static String home = "home/home.html";
	public final static String upload = "manager/upload.html";

	public final static String isShow = "Y";
	public final static String isMain = "Y";
	public final static String isDrop = "Y";

	public final static String isLogin = "true";

	public static HashMap<String, String> manuMap = new HashMap<String, String>();

	public final static String email = "thinkielts.manuk@gmail.com";
}
