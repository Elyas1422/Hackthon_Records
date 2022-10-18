package application.Participants;

public class Student {

	int id;
	String name; 
	String major; 
	public Student(int id , String name , String major) {
		this.id= id;
		this.name= name;
		this.major= major;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEmail() {
		return "s"+this.id+"@kfupm.edu.sa";
	}
	public String toString() {
		return this.name;
	}



}
