package application.CompetitionsTypes;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import application.Participants.Student;
import application.Participants.Team;

public class TeamBasedCompetition extends Competition{

	HashMap<Team,Integer> partcipantsList;
	public TeamBasedCompetition(String sponsor, String competitionName, String competitionLink,
			Date competitionDate, HashMap<Team,Integer> teamsList) throws URISyntaxException {
		super(sponsor, competitionName, competitionLink, competitionDate);
		this.partcipantsList=sortByValue(teamsList);
	}
	
	
	public HashMap<Team, Integer> getPartcipant() {
		return sortByValue(partcipantsList);
	}
	public void addPartcipant(Team t,Integer rank) {
		partcipantsList.put(t, rank);
	}
	public void fixPartcipant(Team t,Integer rank) {
		partcipantsList.replace(t, rank);
	}
	
	public static HashMap<Team, Integer> sortByValue(HashMap<Team, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Team, Integer> > list =
               new LinkedList<Map.Entry<Team, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Team, Integer> >() {
            public int compare(Map.Entry<Team, Integer> o1,
                               Map.Entry<Team, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Team, Integer> temp = new LinkedHashMap<Team, Integer>();
        for (Map.Entry<Team, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
	
	
}
