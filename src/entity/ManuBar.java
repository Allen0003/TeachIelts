package entity;

import java.util.ArrayList;

public class ManuBar {

	String name;

	String value;

	String subClass;

	String isMain;

	String order;

	String isDrop;

	ArrayList<ManuBar> mySubs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIsDrop() {
		return isDrop;
	}

	public void setIsDrop(String isDrop) {
		this.isDrop = isDrop;
	}

	public ArrayList<ManuBar> getMySubs() {
		return mySubs;
	}

	public void setMySubs(ArrayList<ManuBar> mySubs) {
		this.mySubs = mySubs;
	}

}
