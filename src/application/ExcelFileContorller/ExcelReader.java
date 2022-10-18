package application.ExcelFileContorller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;

import application.CompetitionsTypes.Competition;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.CompetitionsTypes.TeamBasedCompetition;
import application.Participants.Student;
import application.Participants.Team;

public class ExcelReader {

	public static ArrayList<Competition> read() throws IOException, URISyntaxException{

		ArrayList<Competition> competitionList = new ArrayList<Competition>();

		String path ="./data/Competitions Participations.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(path);
		String sponsor = null;
		String competitionName =null;
		String link = null;
		Date competitionDate = null;
		String status = null;



		for(int i=0; i<workbook.getNumberOfSheets(); i++) {
			HashMap<Student,Integer> studentsList= new HashMap<Student,Integer>() ; //save individual base students

			HashMap<Team, Integer> teamsList = new HashMap<Team,Integer>(); //save team base teams
			HashMap<String, Integer> teamRanks = new HashMap<String, Integer>(); //save team ranks
			XSSFSheet sheet = workbook.getSheetAt(i);
			sponsor = sheet.getSheetName();// sheet name (sponsor)

			int rows = sheet.getLastRowNum(); //determine number if rows in the sheet

			HashMap<String, ArrayList<Student>> allTeams = new HashMap<String, ArrayList<Student>>(); //contains teams and its members

			for(int r=0; r<rows+1; r++) { // loop over the rows

				if(r==3) {continue;} //skip the third row because it is blank

				XSSFRow row = sheet.getRow(r);
				int cols=sheet.getRow(r).getLastCellNum(); //number of columns per row

				String name = "";
				int ID = 0;
				String major= "";
				int rank = 0;
				String teamName = "";
				int teamNumber = 0;

				for(int c=0; c<cols; c++) { //loop over row's columns

					XSSFCell cell=row.getCell(c); //create cells

					if(r<=4) { //competition details Name-Link-Date

						if(c==1) { //get the value without label
							switch(r) {
							case 0: competitionName = cell.getStringCellValue(); break; //competition name
							case 1: link = cell.getStringCellValue(); break; // competition link
							case 2: competitionDate = cell.getDateCellValue();
							break; //competition date , get the status of competition according to its date
							}

						}

					} else {
						if(cols==5) { //information for individual based competition 


							switch(c) {
							case 1: ID = (int)cell.getNumericCellValue(); break; //Student's ID
							case 2: name = cell.getStringCellValue(); break; //Student's Name
							case 3: major = cell.getStringCellValue(); break; //Student's Major
							case 4: 
								if(cell.getCellType()==CellType.NUMERIC) {rank = (int)cell.getNumericCellValue(); break;} //student's rank
								if(cell.getCellType()==CellType.STRING) {rank = -1;} //student's rank in case there was no rank
							}
							if(rank!=0) {
								Student s = new Student(ID, name, major); //create object for each student
								studentsList.put(s, rank);
							}

						}
						else if(cols==7) { //information for team based competition



							switch(c) {
							case 1: ID = (int)cell.getNumericCellValue(); break; //Student's ID
							case 2: name = cell.getStringCellValue(); break; //Student's Name
							case 3: major = cell.getStringCellValue(); break; //Student's Major
							case 4: teamNumber = (int)cell.getNumericCellValue(); break; //team Number
							case 5: teamName = cell.getStringCellValue(); break; //team name
							case 6:
								if(cell.getCellType()==CellType.NUMERIC) {rank = (int)cell.getNumericCellValue(); break;} //student's rank
								if(cell.getCellType()==CellType.STRING) {rank = -1;} //student's rank in case there was no rank
							}
							if(rank!=0) {

								if (!containsTeam(allTeams, teamName)) {allTeams.put(teamName, new ArrayList<Student>());}  // checks of the team exist on the hashmap of not add new team
								Student s = new Student(ID, name, major); //creates student
								allTeams.get(teamName).add(s); //add student to its allocated team
								teamRanks.put(teamName, rank); //put the team rank
							}

						}
					}

				}
			}
			if(sheet.getRow(4).getLastCellNum()==5) {
				IndividuialBasedCompetition individualCompetition = new IndividuialBasedCompetition(sponsor, competitionName, link, competitionDate, studentsList);
				competitionList.add(individualCompetition);
			}else {

				for (Map.Entry entry:allTeams.entrySet()) { //Create Team object for each team

					String teamName = (String)entry.getKey(); //get the team name
					ArrayList<Student> listOfTeamMembers =allTeams.get(teamName); //get list of student in the same team
					Team team = new Team(listOfTeamMembers, teamName);
					int rank = teamRanks.get(teamName); //get team rank using team name

					teamsList.put(team, rank);

				}

				TeamBasedCompetition teamCompetition = new TeamBasedCompetition(sponsor, competitionName, link, competitionDate, teamsList);
				competitionList.add(teamCompetition);
			}
		}
		workbook.close();
		return competitionList;

	}
	public static boolean containsTeam(HashMap<String, ArrayList<Student>> list, String teamName) {
		boolean condition = false;

		for (Map.Entry entry:list.entrySet()) {
			if((String)entry.getKey()==teamName) {condition = true;}
		}

		return condition;
	}

}