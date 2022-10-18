package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.CompetitionsTypes.Competition;
import application.Participants.Student;
import application.Participants.Team;
import application.messageController.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TeamContainerController  {
	@FXML
	private GridPane GridCon;
    @FXML
    private Button ContactButton;



	public void setData(Competition competition,Team team,Integer rank) {
		String RankStr="#"+rank.toString();
		if (rank==-1) 
			RankStr="";
		Label TeamName= new Label(RankStr+" -"+team.getTeamName());
		TeamName.setFont(Font.font("Calibri",FontWeight.BOLD,35));;
		TeamName.setTextFill(Color.WHITE);
		GridCon.add(TeamName, 0, 0);
		GridPane.setMargin(TeamName,new Insets(50,0,0,50));
		int row=1;
		for (Student s: team.getTeamMembers()) {
			Label StudentName= new Label(row+"-"+s.getName()+"  Id:"+s.getId()+"     Major:"+s.getMajor());
			StudentName.setFont(Font.font("Calibri",FontWeight.BOLD,20));
			StudentName.setTextFill(Color.WHITE);
			GridCon.add(StudentName, 0, row++);
			if (row ==2) {
				GridPane.setMargin(StudentName,new Insets(50,0,0,20));
			}
			else {
				GridPane.setMargin(StudentName,new Insets(0,0,0,20));
			}
		}
		
		String body =Message.getMessage(competition.getCompetitionName(), team.getTeamName());
		String subject=Message.getSubject(rank, competition.getCompetitionName());
		ContactButton.setOnAction(e->{
			try {
				Message.mailto(team.getEmails(),subject , body);
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
					x.getScene(stage, competition, team);
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