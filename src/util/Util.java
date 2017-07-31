package util;

public class Util {

	public static String getAction(String action) throws Exception {
		switch (action.toLowerCase()) {
		case "reading":
			return Const.reading;
		case "listening":
			return Const.listening;
		case "speaking":
			return Const.speaking;
		case "writing1":
			return Const.writing1;
		case "writing2":
			return Const.writing2;
		default:
			throw new Exception("unknow action");
		}
	}

}
