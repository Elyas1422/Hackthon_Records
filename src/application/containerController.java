package application;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;

import application.CompetitionsTypes.Competition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import application.CompetitionViewController;

public class containerController {
	private Stage stage;



	@FXML
	private Label competitionName;

	@FXML
	private Label dateOfCompetiton;

	@FXML
	private Label nameOfsub;
	@FXML
	private AnchorPane ContainerCompetition;

	public void setData(Competition comp) {
		competitionName.setText(comp.getCompetitionName());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		dateOfCompetiton.setText(formatter.format(comp.getCompetitionDate()));
		nameOfsub.setText(comp.getSponsor());
		//ContainerCompetition.setStyle("-fx-background-color:#111111");
		if (comp.getStatus(comp.getCompetitionDate(), comp.getPartcipant())=="Finshed") 
		{
			modifyButton(ContainerCompetition,"linear-gradient(to top, #72B4A4, #111111)","#888888");
		}
		else if (comp.getStatus(comp.getCompetitionDate(), comp.getPartcipant())=="In progress") {
			modifyButton(ContainerCompetition,"linear-gradient(to top, #A2B472, #111111)","#888888");
		}
		else {
			modifyButton(ContainerCompetition,"linear-gradient(to top, #FF2F2F, #111111)","#888888");
		}

		ContainerCompetition.setOnMouseClicked(e ->{
			stage =(Stage)ContainerCompetition.getScene().getWindow();
			CompetitionViewController x = new CompetitionViewController();
			try {
				x.getScene(stage,comp);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});


	}
	public static void modifyButton (AnchorPane button,String color ,String colorPressed) {
		button.setPrefSize(button.getWidth(),button.getHeight()); 
		button.setStyle("-fx-background-color:"+color); //setting a color for the button
		button.setOnMousePressed(e->{button.setStyle("-fx-background-color:"+colorPressed);}); //setting a color for when the user press the button
		button.setOnMouseReleased(e->{button.setStyle("-fx-background-color:"+color);}); //after releasing the mouse, make the color as it was. 
		button.setOnMouseEntered(e ->{button.setScaleX(1.1); button.setScaleY(1.1);});
		button.setOnMouseExited(e ->{button.setScaleX(1); button.setScaleY(1);});


	}


}
