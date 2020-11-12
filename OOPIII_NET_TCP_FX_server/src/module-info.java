module OOPIII_NET_TCP_FX_server {
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	opens main to javafx.graphics, javafx.fxml;
}