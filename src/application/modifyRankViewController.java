package application;

import java.io.IOException;
import java.util.ArrayList;

import application.CompetitionsTypes.Competition;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.CompetitionsTypes.TeamBasedCompetition;
import application.ExcelFileContorller.ExcelWriter;
import application.Participants.Student;
import application.Participants.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class modifyRankViewController {
	@FXML
	private Label ModifyTitle;
	@FXML
	private Button backbutton;
	@FXML
	private Button finishButton;
	@FXML
	private ComboBox rankComboBox;

	public void setData(Competition competition,Student student) {
		ModifyTitle.setText("Modify Rank for "+student.getName());
		ArrayList<String> nums=new ArrayList<String>();
		nums.add("-");
		for (int i =1; i<100;i++) {
			nums.add((i)+"");
		}
		rankComboBox.getItems().addAll(nums);
		IndividuialBasedCompetition x= (IndividuialBasedCompetition) competition;
		finishButton.setOnAction(e->{
			Integer rank=-1;
			if((String)rankComboBox.getValue()=="-") {
				rank=-1;
			}
			else {
				rank= Integer.parseInt((String) rankComboBox.getValue());
			}
			x.fixPartcipant(student, rank);
			CompetitionViewController y=new CompetitionViewController();
			Stage stage =(Stage)finishButton.getScene().getWindow();
			try {
				y.getScene(stage, competition);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		backbutton.setOnAction(e->{
			Stage stage =(Stage)backbutton.getScene().getWindow();
			CompetitionViewController q = new CompetitionViewController();
			try {
				q.getScene(stage,competition);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	public void setData(Competition competition,Team team) {
		ModifyTitle.setText("Modify Rank for "+team.getTeamName());
		ArrayList<String> nums=new ArrayList<String>();
		nums.add("-");
		for (int i =1; i<100;i++) {
			nums.add((i)+"");
		}
		rankComboBox.getItems().addAll(nums);
		TeamBasedCompetition x= (TeamBasedCompetition) competition;
		finishButton.setOnAction(e->{
			Integer rank=-1;
			if((String)rankComboBox.getValue()=="-") {
				rank=-1;
			}
			else {
				rank= Integer.parseInt((String) rankComboBox.getValue());
			}
			x.fixPartcipant(team, rank);
			CompetitionViewController y=new CompetitionViewController();
			Stage stage =(Stage)finishButton.getScene().getWindow();
			try {
				y.getScene(stage, competition);
				ExcelWriter.writeCompetitions(Main.competitionsList);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		backbutton.setOnAction(e->{
			Stage stage =(Stage)backbutton.getScene().getWindow();
			CompetitionViewController q = new CompetitionViewController();
			try {
				q.getScene(stage,competition);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	public void getScene(Stage stage,Competition comp,Student stu ) throws Exception {
		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("modifyRankView.fxml"));
		Parent root = fxmlloader.load();
		modifyRankViewController containerCon= fxmlloader.getController();
		containerCon.setData(comp,stu);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void getScene(Stage stage,Competition comp,Team t ) throws Exception {
		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("modifyRankView.fxml"));
		Parent root = fxmlloader.load();
		modifyRankViewController containerCon= fxmlloader.getController();
		containerCon.setData(comp,t);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
