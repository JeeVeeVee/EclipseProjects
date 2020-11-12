module OOPIII_NET_UDP_FX {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens gui to javafx.graphics, javafx.fxml;
	opens main to javafx.graphics, javafx.fxml;
}