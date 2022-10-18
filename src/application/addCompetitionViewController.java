package application;

import java.io.IOException;
import application.ExcelFileContorller.ExcelWriter;
import java.net.URISyntaxException;
import java.time.LocalDate;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import application.CompetitionsTypes.Competition;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.CompetitionsTypes.TeamBasedCompetition;
import application.Participants.Student;
import application.Participants.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addCompetitionViewController {
	@FXML
	private Label ErrorMessage;
	@FXML
	private Button backbutton;
	@FXML
	private TextField competitionField;
	@FXML
	private DatePicker dateField;
	@FXML
	private Button finishButton;
	@FXML
	private TextField linkField;
	@FXML
	private TextField sponserField;
	@FXML
	private CheckBox typeChecker;

	public void setData() throws Exception{
		finishButton.setOnAction(e->{
			boolean error =false ;
			String errormessage ="";

			if (competitionField.getText().isBlank() ) {
				errormessage += "-Fill the Competition Name ";
				error =true ;
			}
			if (sponserField.getText().isBlank() ) {
				errormessage += "-Fill the sponser ";
				error =true ;
			}
			if (linkField.getText().isBlank() ) {
				errormessage += "-Fill the link ";
				error =true ;
			}
			if (dateField.getValue()==null ) {
				errormessage += "-Choose a Competition Date ";
				error =true ;
			}
			if (error == true ) {
				ErrorMessage.setText(errormessage);
			}
			else {
				ZoneId defaultZoneId = ZoneId.systemDefault();
				LocalDate localDate = dateField.getValue();
				Date date =Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
				String competitionName= competitionField.getText();
				String link=linkField.getText();
				String sponser=sponserField.getText();
				if (typeChecker.isSelected()) {
					try {
						TeamBasedCompetition t= new TeamBasedCompetition(sponser,competitionName , link, date, new HashMap<Team,Integer>());
						Main.competitionsList.add(t);
						ExcelWriter.write(t);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						IndividuialBasedCompetition s= new IndividuialBasedCompetition(sponser,competitionName , link, date, new HashMap<Student,Integer>());
						Main.competitionsList.add(s);
						ExcelWriter.write(s);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				Stage stage =(Stage)backbutton.getScene().getWindow();
				allCompViewController x = new allCompViewController();
				try {
					x.getScene(stage);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		backbutton.setOnAction(e->{
			Stage stage =(Stage)backbutton.getScene().getWindow();
			allCompViewController x = new allCompViewController();
			try {
				x.getScene(stage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}



	public void getScene(Stage stage) throws Exception {
		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("addCompetitionView.fxml"));
		Parent root = fxmlloader.load();
		addCompetitionViewController containerCon= fxmlloader.getController();
		containerCon.setData();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
