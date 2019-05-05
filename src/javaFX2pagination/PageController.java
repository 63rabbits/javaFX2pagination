package javaFX2pagination;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PageController {

	@FXML
	private VBox vbx;
	@FXML
	private Button btn;

	enum PAGE_STYLE {
		NORMAL, BULLET;
	}

	private PAGE_STYLE pgnStyle = PAGE_STYLE.NORMAL;

	final static int NUMBER_OF_PAGE = 15;
	final static int BEGINING_PAGE = 5;
	final static int PADING_OF_PAGE = 20;

	final Pagination pgn = new Pagination();
	//	final Pagination pgn = new Pagination(NUMBER_OF_PAGE, BEGINING_PAGE - 1);

	@FXML
	void initialize() {
		assert vbx != null : "fx:id=\"vbx\" was not injected: check your FXML file 'Page.fxml'.";
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'Page.fxml'.";

		this.vbx.getChildren().add(pgn);

		this.pgn.setPageCount(NUMBER_OF_PAGE);
		this.pgn.setCurrentPageIndex(BEGINING_PAGE - 1);
		setPageStyle(PAGE_STYLE.NORMAL);

		this.pgn.setLayoutX(PADING_OF_PAGE);
		this.pgn.setLayoutY(PADING_OF_PAGE);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pgn.setPrefWidth(vbx.getScene().getWidth() - PADING_OF_PAGE * 2);
				pgn.setPrefHeight(vbx.getScene().getHeight() - PADING_OF_PAGE * 2);
			}
		});

		this.pgn.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				Label lbl = new Label("This page is index-" + pageIndex + " .");
				VBox vbx = new VBox();
				vbx.setAlignment(Pos.CENTER);
				vbx.getChildren().add(lbl);
				return vbx;
			}
		});
	}

	@FXML
	void btnOnAction(ActionEvent e) {
		switch (pgnStyle) {
		case NORMAL:
			setPageStyle(PAGE_STYLE.BULLET);
			break;
		case BULLET:
			setPageStyle(PAGE_STYLE.NORMAL);
			break;
		default:
			break;
		}
	}

	void setPageStyle(PAGE_STYLE s) {
		switch (s) {
		case NORMAL:
			this.pgnStyle = PAGE_STYLE.NORMAL;
			this.pgn.setStyle("-fx-border-color: black;");
			break;
		case BULLET:
			this.pgnStyle = PAGE_STYLE.BULLET;
			this.pgn.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
			this.pgn.getStylesheets().add(this.getClass().getResource("res/Page.css").toExternalForm());
			this.btn.setDisable(true);
			break;
		default:
			break;
		}
	}
}
