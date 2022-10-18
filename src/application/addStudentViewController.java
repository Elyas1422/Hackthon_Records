package application;

import java.io.IOException;
import java.util.ArrayList;

import application.CompetitionsTypes.Competition;
import application.Participants.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.CompetitionsTypes.IndividuialBasedCompetition;
import application.ExcelFileContorller.ExcelWriter;

public class addStudentViewController {
	@FXML
	private Label AddingTitle;
	@FXML
	private Button backbutton;
	@FXML
	private Button finishButton;
	@FXML
	private TextField IdField;
	@FXML
	private TextField MajorField;
	@FXML
	private TextField StudentField;
	@FXML
	private ComboBox rankComboBox;
	@FXML
	private Label ErrorMessage;

	public void setData(Competition comp) throws Exception{
		AddingTitle.setText("Adding a Student to "+comp.getCompetitionName());
		ArrayList<String> nums=new ArrayList<String>();
		nums.add("-");
		for (int i =1; i<100;i++) {
			nums.add((i)+"");
		}
		rankComboBox.getItems().addAll(nums);
		rankComboBox.getSelectionModel().selectFirst();
		backbutton.setOnAction(e->{
			Stage stage =(Stage)backbutton.getScene().getWindow();
			CompetitionViewController x = new CompetitionViewController();
			try {
				x.getScene(stage,comp);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		finishButton.setOnAction(e->{
			boolean error =false ;
			String errormessage ="";
			if (IdField.getText().length()!=9 || ! isNumeric(IdField.getText())) {
				errormessage += "-Id should be 9 digits ";
				error =true ;
			}
			if (StudentField.getText().isBlank() ) {
				errormessage += "-Fill the Student Name ";
				error =true ;
			}
			if (MajorField.getText().isBlank() ) {
				errormessage += "-Fill the Major ";
				error =true ;
			}
			if (error == true ) {
				ErrorMessage.setText(errormessage);
			}
			else {
				int id= Integer.parseInt(IdField.getText());
				Student newStudent= new Student(id,StudentField.getText(),MajorField.getText());
				Integer rank=-1;
				if((String)rankComboBox.getValue()=="-") {
					rank=-1;
				}
				else {
					rank= Integer.parseInt((String) rankComboBox.getValue());
				}
				IndividuialBasedCompetition s=(IndividuialBasedCompetition)comp;
				s.addPartcipant(newStudent, rank);
				CompetitionViewController x = new CompetitionViewController();
				try {
					x.getScene((Stage)backbutton.getScene().getWindow(),comp);
					ExcelWriter.writeCompetitions(Main.competitionsList);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}


	public void getScene(Stage stage,Competition comp ) throws Exception {
		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("addStudentView.fxml"));
		Parent root = fxmlloader.load();
		addStudentViewController containerCon= fxmlloader.getController();
		containerCon.setData(comp);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public static boolean isNumeric(String string) {
	    int intValue;
			
	   
			
	    if(string == null || string.equals("")) {
	     
	        return false;
	    }
	    
	    try {
	        intValue = Integer.parseInt(string);
	        return true;
	    } catch (NumberFormatException e) {
	      
	    }
	    return false;
	}
}
