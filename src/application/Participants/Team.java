package application.Participants;

import java.util.ArrayList;

public class Team {
	private String teamName;
	private ArrayList <Student>teamMembers;
	public Team(ArrayList<Student> Students,String teamName) {
		this.teamName = teamName;
		this.teamMembers = Students;
	}
	public String getTeamName() {
		return teamName;
	}
	public ArrayList<Student> getTeamMembers() {
		return teamMembers;
	}
	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Student student: teamMembers) {
			names.add(student.getName());
		}
		return names;
	}
	public ArrayList<String> getEmails() {
		ArrayList<String> Emails = new ArrayList<String>();
		for (Student student: teamMembers) {
			Emails.add(student.getEmail());
		}
		return Emails;
	}
	public ArrayList<Integer> getIds() {
		ArrayList<Integer> IDs = new ArrayList<Integer>();
		for (Student student: teamMembers) {
			IDs.add(student.getId());
		}
		return IDs;
	}
	public String toString() {
		return teamName+": "+ teamMembers;
	}
	
	
}
