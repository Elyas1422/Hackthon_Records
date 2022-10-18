package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.CompetitionsTypes.Competition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class webViewController implements Initializable  {
	@FXML
	private Button backbutton;
	@FXML
	private TextField linkBox;
	@FXML
	private WebView webview;
	private WebEngine engine;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		engine =webview.getEngine();

	}
	public void loadPage(Competition comp) {
		String link= comp.getCompetitionLink().toString();
		linkBox.setText(link);
		engine.load(link);
		backbutton.setOnMouseClicked(e->{
			CompetitionViewController x = new CompetitionViewController();
			try {
				x.getScene((Stage)backbutton.getScene().getWindow(),comp);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
	}
	public void  getScene(Stage stage ,Competition comp) throws IOException {

		FXMLLoader fxmlloader =new FXMLLoader();
		fxmlloader.setLocation(getClass().getResource("webView.fxml"));
		Parent root = fxmlloader.load();
		webViewController containerCon= fxmlloader.getController();
		containerCon.loadPage(comp);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}