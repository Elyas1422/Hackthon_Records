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
import java.util.TreeMap;

import application.Participants.Student;
import application.Participants.Team;

public class IndividuialBasedCompetition extends Competition{
	HashMap<Student,Integer> partcipantsList;
	public IndividuialBasedCompetition(String sponsor, String competitionName, String competitionLink,
			Date competitionDate,HashMap<Student,Integer> studentsList) throws URISyntaxException {
		super(sponsor, competitionName, competitionLink, competitionDate);
		this.partcipantsList=sortByValue(studentsList);
	}
	public HashMap<Student, Integer> getPartcipant() {
		return sortByValue(partcipantsList);
	}
	public void addPartcipant(Student s,Integer rank) {
		partcipantsList.put(s, rank);
	}
	public void fixPartcipant(Student s,Integer rank) {
		partcipantsList.replace(s, rank);
	}
	public static HashMap<Student, Integer> sortByValue(HashMap<Student, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Student, Integer> > list =
               new LinkedList<Map.Entry<Student, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Student, Integer> >() {
            public int compare(Map.Entry<Student, Integer> o1,
                               Map.Entry<Student, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Student, Integer> temp = new LinkedHashMap<Student, Integer>();
        for (Map.Entry<Student, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
	
}
