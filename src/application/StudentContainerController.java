package application;

import application.CompetitionsTypes.Competition;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.Participants.Student;
import application.messageController.Message;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Application;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StudentContainerController {
	@FXML
	private GridPane GridCon;
	@FXML
	private Button ContactButton;

	public void setData(Competition competition,Student student,Integer rank) {

		String RankStr="#"+rank.toString();
		if (rank==-1)
			RankStr="";
		Label StudentName= new Label(RankStr+" -"+student.getName());
		StudentName.setFont(Font.font("Calibri",FontWeight.BOLD,35));;
		StudentName.setTextFill(Color.WHITE);
		GridCon.add(StudentName, 0, 1);
		GridPane.setMargin(StudentName,new Insets(0,0,0,20));
		Label IdAndMajor= new Label("Id:"+student.getId()+"         Major:"+student.getMajor());
		IdAndMajor.setFont(Font.font("Calibri",FontWeight.BOLD,20));
		IdAndMajor.setTextFill(Color.WHITE);
		GridCon.add(IdAndMajor, 0, 2);
		GridPane.setMargin(IdAndMajor,new Insets(20,0,20,20));
		String body =Message.getMessage(competition.getCompetitionName(), student.getName());
		String subject=Message.getSubject(rank, competition.getCompetitionName());
		ContactButton.setOnAction(e->{

			ArrayList<String> emailList=new ArrayList<String>();
			emailList.add(student.getEmail());
			try {
				Message.mailto(emailList,subject , body);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		if (rank==-1) {
			ContactButton.setText("give a rank");
			ContactButton.setStyle("-fx-background-color:linear-gradient(to right, #BE9400 ,#111111)");
			ContactButton.setOnAction(e->{
				Stage stage =(Stage)ContactButton.getScene().getWindow();
				modifyRankViewController x= new modifyRankViewController();
				try {
					x.getScene(stage, competition, student);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
		GridCon.setOnMouseEntered(e ->{GridCon.setScaleX(1.01); GridCon.setScaleY(1.01);});
		GridCon.setOnMouseExited(e ->{GridCon.setScaleX(1); GridCon.setScaleY(1); });
	}
}
