module SWEproject {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires org.apache.poi.ooxml;
	requires javafx.web;
	requires java.desktop;
	opens application to javafx.graphics, javafx.fxml;
}
