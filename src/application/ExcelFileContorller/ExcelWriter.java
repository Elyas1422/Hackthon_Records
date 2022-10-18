package application.ExcelFileContorller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.CompetitionsTypes.TeamBasedCompetition;
import application.Participants.Student;
import application.Participants.Team;

public class ExcelWriter {

	public static void write(IndividuialBasedCompetition competition) throws Exception {
		
		String filePath = "./data/Competitions Participations.xlsx";
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(file); //Assign the workbook to the existing excel file

		XSSFSheet sheet = workbook.createSheet(competition.getSponsor()); //implement sponsor name using competition class
		String name = competition.getCompetitionName();
		String link = competition.getCompetitionLink().toString();
		Date date = competition.getCompetitionDate();

		XSSFCellStyle style = workbook.createCellStyle(); //create style that give borders for the cell
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		CreationHelper creationHelper = workbook.getCreationHelper(); //create proper format for date
		XSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yyyy"));

		Object competitionData[][] = {
				{"Competition Name", name},
				{"Competition Link", link},
				{"Competition Date", date},
				{"",""},
				{"", "Student ID", "Student Name", "Major", "Rank"}
		}; 

		int rows = competitionData.length;

		for(int r=0; r<rows; r++) {
			int cols = competitionData[r].length;
			XSSFRow row = sheet.createRow(r);
			for(int c=0; c<cols; c++) {
				XSSFCell cell = row.createCell(c);
				Object value =competitionData[r][c];

				if(value instanceof String) {
					cell.setCellValue((String)value);
					if(c==1) {
						sheet.setColumnWidth(c, 15*256);
					}else {
						sheet.autoSizeColumn(r);
					}
					
					if(cols==5) {cell.setCellStyle(style);}
				}
				if(value instanceof Date) {
					cell.setCellValue((Date)value);
					cell.setCellStyle(dateStyle);	
				}


			}
		}

		HashMap<Student, Integer> data = new HashMap<Student, Integer>(competition.getPartcipant()); //HashMap for either individual or team list
		int rowNumForData = 5; //starting point to put the students

		int s=1;
		for (Map.Entry entry:data.entrySet())
		{
			XSSFRow row = sheet.createRow(rowNumForData++);

			Student student = (Student) entry.getKey();
			row.createCell(0).setCellValue((Integer)s);
			row.createCell(1).setCellValue((Integer)student.getId());
			row.createCell(2).setCellValue((String)student.getName());
			sheet.autoSizeColumn(2);
			row.createCell(3).setCellValue((String)student.getMajor());
			sheet.autoSizeColumn(3);
			if((Integer)entry.getValue()==-1) {
				row.createCell(4).setCellValue((String) "-");
			}else {
				row.createCell(4).setCellValue((Integer)entry.getValue());
			}
			s++;
			for(int i=0; i<5; i++) {
				row.getCell(i).setCellStyle(style);
			}
		}

				FileOutputStream fos = new FileOutputStream(filePath); //append the new competition to the excel sheet
				workbook.write(fos);
				fos.close();
				workbook.close();

		System.out.println("Success");

	}

	public static void write(TeamBasedCompetition competition) throws IOException {

		String filePath = "./data/Competitions Participations.xlsx";
		FileInputStream file = new FileInputStream(filePath); //Assign the workbook to an existing excel file
		XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(file);
		XSSFSheet sheet = workbook.createSheet(competition.getSponsor()); //implement sponsor name using competition class

		String name = competition.getCompetitionName();
		String link = competition.getCompetitionLink().toString();
		Date date = competition.getCompetitionDate();

		XSSFCellStyle style = workbook.createCellStyle(); //create style that give borders for the cell
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		CreationHelper creationHelper = workbook.getCreationHelper(); //create proper format for date
		XSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yyyy"));

		Object competitionData[][] = {
				{"Competition Name", name},
				{"Competition Link", link},
				{"Competition Date", date},
				{"",""},
				{"", "Student ID", "Student Name", "Major", "Team", "Team Name", "Rank"}
		}; 

		int rows = competitionData.length;
		int cols = competitionData[0].length;

		for(int r=0; r<rows; r++) {

			XSSFRow row = sheet.createRow(r);
			cols = competitionData[r].length;

			for(int c=0; c<cols; c++) {
				XSSFCell cell = row.createCell(c);
				Object value =competitionData[r][c];

				if(value instanceof String) {
					
					cell.setCellValue((String)value);
					if(c==1) {
						sheet.setColumnWidth(c, 15*256);
					}else {
						sheet.autoSizeColumn(r);
					}
					if(cols==7) {cell.setCellStyle(style);}
				}

				if(value instanceof Date) {
					cell.setCellValue((Date)value);
					cell.setCellStyle(dateStyle);
				}

			}
		}

		HashMap<Team, Integer> data = new HashMap<Team, Integer>(competition.getPartcipant()); //HashMap for either individual or team list
		int rowNumForData = 5; //starting point to put the students
		int r =1;
		int s = 1;
		for (Map.Entry entry:data.entrySet())
		{

			Team team = (Team)entry.getKey();
			ArrayList<Student> students = team.getTeamMembers();



			for(int i=0; i<students.size(); i++) {
				XSSFRow row = sheet.createRow(rowNumForData++);

				Student student = students.get(i);
				row.createCell(0).setCellValue((Integer)s);
				row.createCell(1).setCellValue((Integer)student.getId());
				row.createCell(2).setCellValue((String)student.getName());
				sheet.autoSizeColumn(2);
				row.createCell(3).setCellValue((String)student.getMajor());
				sheet.autoSizeColumn(3);
				row.createCell(4).setCellValue((Integer)r);
				row.createCell(5).setCellValue((String)team.getTeamName());
				sheet.autoSizeColumn(5);
				if((Integer)entry.getValue()==-1) {
					row.createCell(6).setCellValue((String) "-");
				}else {
					row.createCell(6).setCellValue((Integer)entry.getValue());
				}
				s++;
				for(int j=0; j<7; j++) {
					row.getCell(j).setCellStyle(style);
				}


			}
			r++;

		}

				FileOutputStream fos = new FileOutputStream(filePath); //append the new competition to the excel sheet
				workbook.write(fos);
				fos.close();
				workbook.close();
		System.out.println("Success"); 

	}

	public static void writeCompetitions(ArrayList competitionList) throws Exception {

		String filePath = "./data/Competitions Participations.xlsx";
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(file); //Assign the workbook to an existing excel file
		if(workbook.getNumberOfSheets()!=0) {removeAllSheets(workbook);} //the method will remove any existing sheets before writing

		for(int i=0; i<competitionList.size(); i++) { //write all the competitions

			if(competitionList.get(i) instanceof IndividuialBasedCompetition) { //select IndividualCompetition base and write it
				IndividuialBasedCompetition newCompetiion =(IndividuialBasedCompetition) competitionList.get(i);
				ExcelWriter.write(newCompetiion);
			} else { //select TeamCompetition and write it
				TeamBasedCompetition newCompetiion =(TeamBasedCompetition) competitionList.get(i);
				ExcelWriter.write(newCompetiion);
			}
		}
		

	}
	public static void removeAllSheets(XSSFWorkbook workbook) throws IOException { //this method removes all sheets before updating
		String filePath = "./data/Competitions Participations.xlsx";
		
		for(int i=workbook.getNumberOfSheets()-1;i>=0; i--){
			workbook.removeSheetAt(i);
		}
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		fos.close();
		workbook.close();
	}

}