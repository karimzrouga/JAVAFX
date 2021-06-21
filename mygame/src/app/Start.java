package app;

import Controls.Viewcontrol;
import Controls.cmenu;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Start extends Application {
	private BorderPane root;
	private double xOffset, yOffset;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);

		try {
			root = new BorderPane();
			cmenu menu = new cmenu();

			root.setOnMousePressed(e -> {

				xOffset = e.getSceneX();
				yOffset = e.getSceneY();

			});
			root.setOnMouseDragged(e -> {

				primaryStage.setX(e.getScreenX() - xOffset);
				primaryStage.setY(e.getScreenY() - yOffset);

			});
			Viewcontrol cl = new Viewcontrol();

			menu.Menu(root);

			Scene scene = new Scene(root, 700, 500);

			scene.cursorProperty().bind(cl.getCursor());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("img/buttonicons/stageicon.jpg"));
			primaryStage.setTitle("FX GAME ZONE");
			primaryStage.setScene(scene);

			primaryStage.show();
			if (cl.getClic() != 0) {
				primaryStage.close();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		primaryStage.setOnCloseRequest(event -> {
			Viewcontrol navc = new Viewcontrol();
			navc.setClic(0);
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
