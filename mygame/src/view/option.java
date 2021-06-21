package view;

import Controls.cmenu;
import Model.cursors;

import javafx.geometry.Insets;

import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class option {
	private cursors cursor;

	private BorderPane root;
	private GridPane pane;

	public option(BorderPane root) {
		cursor = new cursors();
		this.root = root;
	}

	public void Optionsview() {

		try {
			GameSound sound = new GameSound("");
			HBox menu = new HBox();

			menu.setSpacing(10);

			root.setStyle("-fx-background-image: url(\"img/backgoundlevels/back2.png\");"
					+ "-fx-background-position:  center;" + " -fx-background-repeat: no-repeat;"
					+ "-fx-background-size: cover, auto;"

			);
			Button home = new Button();
			home.setOnAction(event -> {
				System.out.println("home");
				cmenu c = new cmenu();
				root.getChildren().clear();
				root.setStyle("-fx-background-image:none");
				c.Menu(root);
			});
			home.getStyleClass().add("mybutton");

			Button mute = new Button();

			mute.setOnAction(event -> {

				sound.setMute(true);

			});
			Button play = new Button();
			play.setOnAction(event -> {

				sound.setMute(false);

			});

			Button edit = new Button();

			menu.setPadding(new Insets(80, 10, 10, 150));
			edit.setOnAction(event -> {
				editplayer rd = new editplayer();
				rd.edit(root, pane);

			});
			pane = new GridPane();
			pane.setPadding(new Insets(80, 10, 10, 150));
			pane.setHgap(10);
			pane.setVgap(10);
			int exit = 0, i = 0, j = 5;

			while (exit < cursor.getCursors().size() && cursor.getCursors() != null) {

				pane.add(cursor.listbutton().get(exit), i, j, 1, 1);

				i++;
				if (i == 5) {
					j++;
					i = 0;
				}

				exit++;
			}

			home.setGraphic(setimg("/img/buttonicons/prd.png"));
			mute.setGraphic(setimg("/img/buttonicons/of.png"));
			play.setGraphic(setimg("/img/buttonicons/on.png"));
			edit.setGraphic(setimg("/img/buttonicons/editt.png"));

			menu.getChildren().addAll(home, mute, play);
			playerview pv = new playerview();
			if (pv.getPlayer() != null && pv.getPlayer().getId() != -1&& pv.getPlayer().getPassword()!=null ) {
				menu.getChildren().add(edit);

			}

			root.setTop(menu);
			root.setCenter(pane);

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	private ImageView setimg(String image) {
		ImageView iv = new ImageView(image);

		iv.setFitHeight(50);
		iv.setFitWidth(50);
		return iv;

	}

}
