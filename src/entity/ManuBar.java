package entity;

import java.util.ArrayList;

public class ManuBar {

	String name;

	String value;

	String subClass;

	boolean isMain;

	String order;

	boolean isDrop;

	ArrayList<ManuBar> mySubs;

	public ArrayList<ManuBar> getMySubs() {
		return mySubs;
	}

	public void setMySubs(ArrayList<ManuBar> mySubs) {
		this.mySubs = mySubs;
	}

	public boolean isDrop() {
		return isDrop;
	}

	public void setDrop(boolean isDrop) {
		this.isDrop = isDrop;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

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

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}
}
