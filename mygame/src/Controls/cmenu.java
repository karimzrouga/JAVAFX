package Controls;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.GameSound;
import view.carrier;
import view.level;
import view.login;
import view.option;
import view.playerview;
import view.ranking;

public class cmenu {
	playerview pv;

	public cmenu() {

	}

	public void Menu(BorderPane root) {

		try {
			pv = new playerview();
			GameSound s = new GameSound("src/music/music.mp3");
			s.Play();

			root.setId("pane");

			VBox menu = new VBox();
			Image img = new Image("img/backgoundlevels/img.png");
			ImageView iv = new ImageView();
			iv.setImage(img);
			iv.setFitHeight(500);
			iv.setFitWidth(700);
			root.getChildren().add(iv);
			Text text = new Text("MEMORYFLIP");
			// text.getStyleClass().add("text");
			text.setId("fancytext");

			root.setTop(text);
			// menu.getStyleClass().add("menu");
			menu.setSpacing(15);
			menu.setPadding(new Insets(30, 80, 10, 300));

			Button PLAY = new Button("PLAY") {
				{
					setOnAction(event -> {
						if (pv.getPlayer() == null) {
							login l = new login(root);

							root.getChildren().clear();
							l.loginview();

						} else {
							root.getChildren().clear();
							level l = new level(700, 500);
							root.getChildren().add(l.levelview());
						}

						// s.stop();

					});
				}
			};

			Button _carrier = new Button("carrier") {
				{
					setOnAction(event -> {
					
						if (pv.getPlayer() != null && pv.getPlayer().getId() != -1&& pv.getPlayer().getPassword()!=null ) {
							System.out.println(pv.getPlayer().toString());
							carrier Carrier = new carrier(root);
							root.getChildren().clear();
							Carrier.carrierview();

						}

					});
				}
			};
			Tooltip tt = new Tooltip();
			tt.setText("login before");
			tt.setStyle("-fx-font: normal bold 10 Langdon; " + "-fx-base: #AE3522; " + "-fx-text-fill: white;");

			_carrier.setTooltip(tt);
			Button RANKING = new Button("RANKING") {
				{
					setOnAction(event -> {

						ranking Ranking = new ranking(root);

						root.getChildren().clear();
						Ranking.rankingview();
					});
				}
			};

			Button OPTIONS = new Button("OPTIONS") {
				{
					setOnAction(event -> {
						option option = new option(root);
						root.getChildren().clear();
						option.Optionsview();

					});
				}
			};

			Button DUAL = new Button("DUAL") {
				{
					setOnAction(event -> {
						dualmenu dl = new dualmenu();
						dl.confignemu(root);

					});
				}
			};

			Button EXIT = new Button("Exit");
			EXIT.setOnAction(e -> Platform.exit());
			PLAY.getStyleClass().add("menubutton");
			_carrier.getStyleClass().add("menubutton");
			OPTIONS.getStyleClass().add("menubutton");
			RANKING.getStyleClass().add("menubutton");
			DUAL.getStyleClass().add("menubutton");
			EXIT.getStyleClass().add("menubutton");
			menu.getChildren().addAll(PLAY, DUAL, _carrier, OPTIONS, RANKING, EXIT);
			root.setCenter(menu);

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
