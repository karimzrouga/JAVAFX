package view.dualmod;

import Controls.Viewcontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.GameSound;

public class luncher_dual {
	static Stage Dualstage;
	private BorderPane bp;
	private GridPane grid = new GridPane();
	private VBox vs;
	private DualMod dualplayer;
	private GameSound start;

	public luncher_dual() {

	}

	@SuppressWarnings("static-access")
	public Stage initdual(int nbcard, int nbc, int nbl, Insets c, int size) {
		try {
		
			bp = new BorderPane();
			start=new GameSound ("");
			dualplayer = new DualMod(nbcard);
			Dualstage = new Stage();

			TextField player1name = new TextField("PLAYER1");
			TextField player2name = new TextField("PLAYER2");
			dualplayer.setP1(player1name.getText());
			dualplayer.setP2(player2name.getText());
			grid = dualplayer.initworld(nbc, nbl, size);
			dualplayer.initmenu(bp);
			Button start1 = new Button("start ");
			start1.setOnAction(event -> {
				///// flip back all cards
				for (int i1 = 0; i1 < dualplayer.getCards().size(); i1++) {
					dualplayer.getCards().get(i1).flip(dualplayer.getCards().get(i1).getFront());
				}
				start.changesound("src\\music\\start1.mp3");
				dualplayer.setTour1(true);

				dualplayer.flipall();
				vs.getChildren().clear();
				vs.setStyle("-fx-background-color: none");
				dualplayer.setP1(player1name.getText());
				dualplayer.setP2(player2name.getText());
			});

			grid.setPadding(c);

			grid.setVgap(10);
			grid.setHgap(5);
			bp.setCenter(grid);
			start1.setStyle("-fx-background-color: \r\n" + "      #FFFFFF,\r\n"

					+ "        linear-gradient( #a40606  50%, #6b0f1a 60%);\r\n"
					+ "    -fx-background-insets: 0,1,2;\r\n" + "    -fx-background-radius: 3,2,1;\r\n"
					+ "    -fx-padding: 3 30 3 30;\r\n" + "    -fx-text-fill: white;\r\n" + "    -fx-font-size: 14px;");
			Button start2 = new Button("start ");
			start2.setOnAction(event -> {
				dualplayer.setTour2(true);
				for (int i1 = 0; i1 < dualplayer.getCards().size(); i1++) {
					dualplayer.getCards().get(i1).flip(dualplayer.getCards().get(i1).getFront());
				}
				dualplayer.flipall();
				start.changesound("src\\music\\start1.mp3");
				vs.getChildren().clear();
				vs.setStyle("-fx-background-color: none");
				dualplayer.setP1(player1name.getText());
				dualplayer.setP2(player2name.getText());
			});
			start2.setStyle("-fx-background-color: \r\n" + "     #FFFFFF,\r\n"

					+ "        linear-gradient( #009ffd  50%, #2a2a72 60%);\r\n"
					+ "    -fx-background-insets: 0,1,2;\r\n" + "    -fx-background-radius: 3,2,1;\r\n"
					+ "    -fx-padding: 3 30 3 30;\r\n" + "    -fx-text-fill: white;\r\n" + "    -fx-font-size: 14px;");

			/**************************************************************************/

			vs.setMargin(player1name, new Insets(130, 0, 0, 0));
			vs.setMargin(player2name, new Insets(200, 0, 0, 0));
			vs = new VBox(player1name, start1, player2name, start2);
			vs.setStyle(style("img/backgoundlevels/mnd.png"));
			bp.setStyle(style("img/backgoundlevels/new.png"));

			vs.setSpacing(40);

			bp.setRight(vs);

			BorderPane.setAlignment(grid, Pos.CENTER_LEFT);
			Scene scene = new Scene(bp, 1000, 1000);
			Viewcontrol cl = new Viewcontrol();
			scene.cursorProperty().bind(cl.getCursor());
			Dualstage.setScene(scene);
			Dualstage.resizableProperty().setValue(Boolean.TRUE);
			Dualstage.setTitle("FX GAME ZONE");
			Dualstage.setAlwaysOnTop(true);
			Dualstage.getIcons().add(new Image("img/buttonicons/stageicon.jpg"));
			Dualstage.setScene(scene);
			Dualstage.setMaximized(true);

			Dualstage.setOnCloseRequest(event -> {
				Viewcontrol navc = new Viewcontrol();
				navc.setClic(0);
			});

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return Dualstage;

	}

	private String style(String background) {
		return ("-fx-background-image: url('" + background + "'); " + "-fx-background-position: center center; "
				+ "-fx-background-repeat: no-repeat;" + "-fx-background-size: cover");
	}

}
