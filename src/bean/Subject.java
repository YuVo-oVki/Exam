package bean;

import java.io.Serializable;
import bean.School;

public class Subject implements Serializable {
	private String cd;
	private String name;
	private School school;

	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public School school
}
