package Controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.dualmod.luncher_dual;

public class dualmenu {

	
	Insets ins = null;

	public dualmenu() {

	}

	public void confignemu(BorderPane p) {

		Button prd = new Button();
		prd.setOnAction(event -> {

			cmenu c = new cmenu();
			p.getChildren().clear();
			c.Menu(p);

		});
		prd.getStyleClass().add("mylbutton");
		Image iconprd = new Image("/img/buttonicons/prd.png");
		ImageView icon = new ImageView();
		icon.setImage(iconprd);
		icon.setFitHeight(40);
		icon.setFitWidth(40);
		prd.setGraphic(icon);

		Button lev1 = new Button() {
			{
				setOnAction(event -> {
					ins = new Insets(-30, 0, 0, 300);
					luncher_dual dualmod = new luncher_dual();

					dualmod.initdual(16, 4, 4, ins, 150).show();

				});
			}
		};

		lev1.setGraphic(setGraphic("img/buttonicons/16.png"));
		lev1.setStyle(btnlvlstyle());
		Button lev2 = new Button() {
			{
				setOnAction(event -> {

					ins = new Insets(-30, 0, 0, 280);
					luncher_dual dualmod = new luncher_dual();
					dualmod.initdual(20, 5, 4, ins, 150).show();
				});
			}
		};

		lev2.setStyle(btnlvlstyle());
		lev2.setGraphic(setGraphic("img/buttonicons/20.png"));
		Button lev3 = new Button() {
			{
				setOnAction(event -> {
					ins = new Insets(-40, 0, 0, 0);
					luncher_dual dualmod = new luncher_dual();
					dualmod.initdual(28, 7, 4, ins, 140).show();

				});
			}
		};

		lev3.setStyle(btnlvlstyle());
		lev3.setGraphic(setGraphic("img\\buttonicons\\28.png"));
		Button lev4 = new Button() {
			{
				setOnAction(event -> {

					ins = new Insets(-40, 0, 0, 0);
					luncher_dual dualmod = new luncher_dual();
					dualmod.initdual(50, 10, 5, ins, 101).show();

				});
			}
		};
		lev4.setGraphic(setGraphic("img/buttonicons/50.png"));
		lev4.setStyle(btnlvlstyle());

		HBox hm = new HBox(lev1, lev2);
		HBox hm1 = new HBox(lev3, lev4);
		VBox vb = new VBox(prd, hm, hm1);
		vb.setSpacing(20);
		vb.setPadding(new Insets(10, 10, 10, 150));
		hm.setPadding(new Insets(60, 0, 0, 50));
		hm1.setPadding(new Insets(30, 0, 0, 50));
		hm.setSpacing(40);
		hm1.setSpacing(40);
		p.setCenter(vb);
	}

	

	

	private ImageView setGraphic(String img) {

		Image icard = new Image(img);
		ImageView cbtn = new ImageView();
		cbtn.setImage(icard);
		cbtn.setFitHeight(80);
		cbtn.setFitWidth(80);
		return cbtn;

	}

	private String btnlvlstyle() {
		return " -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#020b02, #3a3a3a),\r\n"
				+ "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\r\n"
				+ "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),\r\n"
				+ "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5,6;\r\n" + "    -fx-background-radius: 9,8,5,4,3;\r\n"
				+ "    -fx-padding: 5 7 5 7;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);";
	}

	
}
