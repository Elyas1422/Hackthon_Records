package application;

import java.io.IOException;
import javafx.scene.control.ScrollPane;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.ResourceBundle;

import application.CompetitionsTypes.Competition;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.CompetitionsTypes.TeamBasedCompetition;
import application.Participants.Student;
import application.Participants.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class CompetitionViewController   {
	@FXML
	private Label CompetitionName= new Label(" ");
	@FXML
	private Label SponserAndDate= new Label(" ");
	@FXML
	private ImageView webIcon=new ImageView();
	@FXML
	private Label Status= new Label(" ");
	@FXML
	private Button backbutton= new Button();
    @FXML
    private Button addPartcipant= new Button();
	@FXML
	private ScrollPane ScrollPane;
	@FXML
	private GridPane gridConS;






	public void setData(Competition comp) throws Exception {
		
		CompetitionName.setText(comp.getCompetitionName());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		String date=formatter.format(comp.getCompetitionDate());
		SponserAndDate.setText("sponsered by:"+comp.getSponsor()+"          "+"Date:"+date);
		Status.setText(comp.getStatus(comp.getCompetitionDate(), comp.getPartcipant()));
		modifypic(webIcon);
		webIcon.setOnMouseClicked(e->{
			Stage stage =(Stage)webIcon.getScene().getWindow();
			webViewController x = new webViewController();
			try {
				x.getScene(stage, comp);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		

		// Organizing the students in the scroll pane.
		int row = 1;
		//int column =1 ; 
		
		if (comp instanceof IndividuialBasedCompetition) {
			
			IndividuialBasedCompetition x= (IndividuialBasedCompetition) comp;
			for (Map.Entry<Student, Integer>student :x.getPartcipant().entrySet()) {
				FXMLLoader fxmlloader =new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("StudentContainer.fxml"));
				GridPane anchorPane = fxmlloader.load();
				StudentContainerController containerCon1= fxmlloader.getController();
				containerCon1.setData(comp,student.getKey(),student.getValue());
				gridConS.add(anchorPane, 0, row++);
				GridPane.setMargin(anchorPane,new Insets(30));
				
			}
			addPartcipant.setOnAction(e->{
				
				Stage stage =(Stage)addPartcipant.getScene().getWindow();
				addStudentViewController s = new addStudentViewController();
				try {
					s.getScene(stage,comp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});

		}
		else {
			TeamBasedCompetition x = (TeamBasedCompetition) comp;
			for (Map.Entry<Team, Integer>team :x.getPartcipant().entrySet()) {
				FXMLLoader fxmlloader =new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("TeamContainer.fxml"));
				GridPane anchorPane = fxmlloader.load();
				TeamContainerController containerCon1= fxmlloader.getController();
				containerCon1.setData(comp,team.getKey(),team.getValue());
				gridConS.add(anchorPane, 0, row++);
				GridPane.setMargin(anchorPane,new Insets(22));
			}
			addPartcipant.setOnAction(e->{
				
				Stage stage =(Stage)addPartcipant.getScene().getWindow();
				addTeamViewController t = new addTeamViewController();
				try {
					t.getScene(stage,comp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});
			
		}
	}
	public void getScene(Stage stage,Competition comp ) throws Exception {
		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("CompetitionView.fxml"));
		Parent root = fxmlloader.load();
		CompetitionViewController containerCon= fxmlloader.getController();
		containerCon.setData(comp);
		Scene scene = new Scene(root);
		stage.setTitle(comp.getCompetitionName());
		stage.setScene(scene);
		stage.show();
	}
	public static void modifypic (ImageView pic) {
		pic.setOnMouseEntered(e ->{pic.setScaleX(1.1); pic.setScaleY(1.1);});
		pic.setOnMouseExited(e ->{pic.setScaleX(1); pic.setScaleY(1);});	
	}
}
