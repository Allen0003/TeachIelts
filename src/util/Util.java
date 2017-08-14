package util;

public class Util {

	public static String getCategorization(String action) {
		switch (action.toLowerCase()) {
		case "reading":
			return Const.reading;
		case "listening":
			return Const.listening;
		case "speaking":
			return Const.speaking;
		case "generalwriting1":
			return Const.generalWriting1;
		case "academicwriting1":
			return Const.academicWriting1;
		case "writing2":
			return Const.writing2;
		case "ieltstest":
			return Const.TheIELTSTest;
		case "thinkielts":
			return Const.AboutThinkIELTS;
		case "studying":
			return Const.StudyingEnglish;
		default:
			return "unknown";
		}
	}

}
