package application.CompetitionsTypes;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import application.Participants.Student;

public abstract class Competition {
	private HashMap<Object,Integer> partcipantsList=new HashMap();
	private String sponsor ; 
	private URI competitionLink ;
	private String competitionName ;
	private Date competitionDate;
	public Competition(String sponsor,String competitionName, String competitionLink,Date competitionDate) throws URISyntaxException {
		this.sponsor=sponsor;
		this.competitionName=competitionName;
		this.competitionLink= new URI(competitionLink);
		this.competitionDate=competitionDate;
	}
	public String getSponsor() {
		return sponsor;
	}
	public URI getCompetitionLink() {
		return competitionLink;
	}
	public String getCompetitionName() {
		return competitionName;
	}
	public Date getCompetitionDate() {
		return competitionDate;
	}
	public  HashMap<?,?> getPartcipant() {
		return null;
	}
	public void addPartcipant(Object j,Integer rank) {
		partcipantsList.put(j, rank);
	}
	public String getStatus(Date date, HashMap<?,?> participants) {

		String status = "";
		Boolean update = false; //check if students rank needs update

		if(date.before(new Date())) {

			for (Map.Entry entry:participants.entrySet()) { //search if there is any non updated ranks

				if((Integer)entry.getValue()==-1) {update = true;}

			}
			if(update) {status = "Need Update";}
			else {status = "Finshed";}
		}
		else {status = "In progress";}

		return status;
	}


}
