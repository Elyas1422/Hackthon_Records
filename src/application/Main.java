package application;

import java.util.ArrayList;
import application.ExcelFileContorller.ExcelWriter;
import application.CompetitionsTypes.Competition;
import application.ExcelFileContorller.ExcelReader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

public class Main extends Application {
	public static ArrayList<Competition> competitionsList;
	public void start(Stage primaryStage) {

		try {
			competitionsList= ExcelReader.read();
			Scene scene = new allCompViewController().getStartingScene();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Hackathons Record");
			primaryStage.getIcons().add(new Image("file:competition.png")); //set icon for the program
			int nonUpdatedCompetition=0;
			for(int i = 0 ; i< Main.competitionsList.size();i++) {
				Competition comp= Main.competitionsList.get(i);
				if(comp.getStatus(comp.getCompetitionDate(), comp.getPartcipant()).equals("Need Update")){
					nonUpdatedCompetition++;
				}

			}
			if (nonUpdatedCompetition>0) {
				Alert a= new Alert(Alert.AlertType.WARNING);
				a.setTitle("Please Update");
				a.setContentText("There is "+nonUpdatedCompetition+" Competitions that need to be updated.");
				a.setHeaderText(null);
				a.show();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		
		launch(args);
		ExcelWriter.writeCompetitions(competitionsList);
	}
}