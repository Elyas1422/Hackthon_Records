package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import application.CompetitionsTypes.Competition;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.ExcelFileContorller.ExcelReader;
import application.Participants.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class allCompViewController implements Initializable {
	@FXML
	private GridPane gridCon;
	@FXML
	private ScrollPane scrollContainer;
	@FXML
	private Button addCompetitionButton;

	private List<Competition> competitionsList= new ArrayList<Competition>();

	private List<Competition> getData() throws URISyntaxException, IOException{
		List<Competition> cc1= Main.competitionsList;
		return cc1;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addCompetitionButton.setOnAction(e->{
			addCompetitionViewController x =new addCompetitionViewController();
			Stage stage =(Stage)addCompetitionButton.getScene().getWindow();
			try {
				x.getScene(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		int column =1 ; 
		int row = 1;
		try {
			competitionsList.addAll(getData());
			for (int i =0 ; i< competitionsList.size();i++) {
				FXMLLoader fxmlloader =new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("CompetitionContianer.fxml"));
				AnchorPane anchorPane = fxmlloader.load();
				containerController containerCon= fxmlloader.getController();
				containerCon.setData(competitionsList.get(i));
				if (column==5) {
					column=1;
					row++;
				}
				gridCon.add(anchorPane, column++, row);
				//gridCon.add(new Insets(10), column++, row);
				GridPane.setMargin(anchorPane,new Insets(20));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getScene(Stage stage) throws IOException {
		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("AllCompetitionsView.fxml"));
		Parent root = fxmlloader.load();
		allCompViewController containerCon= fxmlloader.getController();
		Scene scene = new Scene(root);
		stage.setTitle("Hackathons Record");
		stage.setScene(scene);
		stage.show();
	}
	public Scene getStartingScene( ) throws IOException{

		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("AllCompetitionsView.fxml"));
		Parent root = fxmlloader.load();
		allCompViewController containerCon= fxmlloader.getController();
		Scene scene = new Scene(root);
		
		return scene;
	}
}
