package view;

import Controls.Datacontrol;
import Controls.Viewcontrol;
import Controls.cmenu;

import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

public class level {
	private Viewcontrol navc;
	private BorderPane root;
	private SingleMod gameworld = null;
	private int sceneWidth = 0, sceneHeigth = 0;

	/************************************************************************/
	public level(int sceneWidth, int sceneHeigth) {
		this.sceneWidth = sceneWidth;
		this.sceneHeigth = sceneHeigth;

	}

	/************************************************************************/
	private ImageView setimg(Image i) {
		ImageView iv = new ImageView();
		iv.setImage(i);
		iv.setFitHeight(60);
		iv.setFitWidth(60);
		return iv;

	}

	/************************************************************************/

	@SuppressWarnings("static-access")
	public BorderPane levelview() {
		Admin adm = new Admin();

		try {
			navc = new Viewcontrol();
			root = new BorderPane();

			resize(this.sceneWidth, this.sceneHeigth);
			root.setStyle("-fx-background-image: url(\"img/backgoundlevels/back2.png\");"
					+ "-fx-background-position:  center;" + " -fx-background-repeat: no-repeat;"
					+ "-fx-background-size: cover, auto;"

			);
			//////////////////////////////////////////////////////////////////////////////

			Button prd = new Button();
			prd.setOnAction(event -> {
				if (navc.getClic() == 0) {
					root.setStyle("-fx-background-image: none");
					cmenu c = new cmenu();
					root.getChildren().clear();
					c.Menu(root);
				}

			});
			prd.getStyleClass().add("mybutton");
			Image iconprd = new Image("/img/buttonicons/prd.png");
			ImageView icon = new ImageView();
			icon.setImage(iconprd);
			icon.setFitHeight(40);
			icon.setFitWidth(40);
			prd.setGraphic(icon);
			/************************************* -_- ************************************/
			Button edit = new Button();
			edit.getStyleClass().add("mybutton");
			edit.setOnAction(event -> {
				if (navc.getClic() == 0) {

					navc.setClic(1);
					adm.adminspace().show();
				}
			});
			Image iconedit = new Image("/img/buttonicons/setting.png");
			ImageView editicon = new ImageView();
			editicon.setImage(iconedit);
			editicon.setFitHeight(40);
			editicon.setFitWidth(40);
			edit.setGraphic(editicon);
			//////////////////////////////////////////////////////////////////////////////

			VBox v1 = new VBox();
			VBox v2 = new VBox();

			v1.setSpacing(10);
			v2.setSpacing(10);
			HBox menu = new HBox(v1, v2);
			menu.setSpacing(80);
			menu.setPadding(new Insets(10, 10, 10, 220));
			HBox mn = new HBox();
			mn.setSpacing(180);
			mn.setPadding(new Insets(70, 10, 0, 70));

			Button LEVEL1 = new Button();
			LEVEL1.setOnAction(event -> {

				if (navc.getClic() == 0) {

					gameworld = new SingleMod("L1", 8, 3, 650, 620);

					gameworld.CreateWorld("/img/backgoundlevels/b.jpg", 20, 100, 12, 3).show();

				}

				// stop();
				// autoply(url);
			});

			Button LEVEL2 = new Button();
			LEVEL2.setOnAction(event -> {

				if (navc.getClic() == 0) {
					gameworld = new SingleMod("L2", 12, 4, 750, 620);
					gameworld.CreateWorld("/img/backgoundlevels/pu.jpg", 20, 100, 18, 5).show();
				}
			});

			Button LEVEL3 = new Button();
			LEVEL3.setOnAction(event -> {
				if (navc.getClic() == 0) {

					// bord("L3", nbcard, nbcolone, screenwith, screenheigth)
					gameworld = new SingleMod("L3", 18, 5, 800, 650);
					// pl1.creatbord("space.png",width,heigth,vmenu padding,spacing,time);
					gameworld.CreateWorld("/img/backgoundlevels/space.png", 30, 120, 28, 8).show();
				}
			});

			Button LEVEL4 = new Button();
			LEVEL4.setOnAction(event -> {
				if (navc.getClic() == 0) {
					navc.setClic(1);
					gameworld = new SingleMod("L4", 24, 6, 901, 650);
					gameworld.CreateWorld("/img/backgoundlevels/gray.jpg", 50, 140, 50, 8).show();
				}

			});

			Button LEVEL5 = new Button();
			LEVEL5.setOnAction(event -> {
				if (navc.getClic() == 0) {

					gameworld = new SingleMod("L5", 50, 10, 1000, 1000);
					gameworld.CreateWorld("/img/backgoundlevels/pink.jpg", 70, 280, 80, 15).show();
				}

			});
			Button LEVEL6 = new Button();

			LEVEL6.setOnAction(event -> {
				if (navc.getClic() == 0) {

					gameworld = new SingleMod("L6", 72, 12, 1100, 1001);
					gameworld.CreateWorld("/img/backgoundlevels/yellow.jpg", 120, 250, 120, 15).show();
				}
			});
			LEVEL1.setStyle(btnlvlstyle());
			LEVEL2.setStyle(btnlvlstyle());
			LEVEL3.setStyle(btnlvlstyle());
			LEVEL4.setStyle(btnlvlstyle());
			LEVEL5.setStyle(btnlvlstyle());
			LEVEL6.setStyle(btnlvlstyle());
			Image lev1 = new Image("img/buttonicons/lvl1.png");
			Image lev2 = new Image("img/buttonicons/lvl2.png");
			Image lev3 = new Image("img/buttonicons/l1.png");
			Image lev4 = new Image("img/buttonicons/l2.png");
			Image lev5 = new Image("img/buttonicons/l3.png");
			Image lev6 = new Image("img/buttonicons/l4.png");
			LEVEL1.setGraphic(setimg(lev1));
			LEVEL2.setGraphic(setimg(lev2));
			LEVEL3.setGraphic(setimg(lev3));
			LEVEL4.setGraphic(setimg(lev4));
			LEVEL5.setGraphic(setimg(lev5));
			LEVEL6.setGraphic(setimg(lev6));
			Button logout = new Button();
			logout.getStyleClass().add("mylogbutton");
			Image iconlogout = new Image("/img/buttonicons/out.png");
			ImageView ic = new ImageView();
			ic.setImage(iconlogout);
			ic.setFitHeight(60);
			ic.setFitWidth(60);
			logout.setGraphic(ic);
			logout.setOnAction(event -> {
				playerview pv = new playerview();
				{
					pv.setPlayer(null);
					root.setStyle("-fx-background-image: none");
					root.getChildren().clear();
					login l = new login(root);
					l.loginview();

				}

			});
			mn.getChildren().addAll(prd, logout, edit);

			/***********************************************/
			playerview vp = new playerview();
			Label playername = new Label(vp.getPlayer().getName().toUpperCase());
			playername.setStyle(" -fx-font-size: 16pt;\r\n");

			Datacontrol dc = new Datacontrol();
			if (dc.Adminloging()) {
				edit.setVisible(true);
			} else {
				edit.setVisible(false);

				mn.setMargin(edit, new Insets(0, 0, 0, -270));
				mn.getChildren().add(playername);

			}
			/***********************************************/

			v1.getChildren().addAll(LEVEL1, LEVEL2, LEVEL3);
			v2.getChildren().addAll(LEVEL4, LEVEL5, LEVEL6);
			root.setCenter(menu);
			root.setTop(mn);

		}

		catch (

		Exception e) {

			System.out.println(e.getMessage());
		}

		return root;

	}

	private String btnlvlstyle() {
		return " -fx-background-color: \r\n"
				+ "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n"
				+ "        linear-gradient(#D3D3D3  , #D3D3D3  ),\r\n"
				+ "        linear-gradient(#807b7a  0%, #adabaa 20%,#D3D3D3  80%, #adabaa 100%),\r\n"

				+ "        linear-gradient(#D3D3D3  0%, #D3D3D3 50%, #D3D3D3  51%, #D3D3D3  100%);\r\n"
				+ "    -fx-background-insets: 0,1,4,5,6;\r\n" + "    -fx-background-radius: 9,8,5,4,3;\r\n"
				+ "    -fx-padding: 5 8 5 8;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);";
	}

	private void resize(double width, double heigth) {
		root.setMaxWidth(width);
		root.setMinWidth(width);
		root.setMinHeight(heigth);
		root.setMaxHeight(heigth);
	}

	public int getSceneWidth() {
		return sceneWidth;
	}

	public void setSceneWidth(int sceneWidth) {
		this.sceneWidth = sceneWidth;
	}

	public int getSceneHeigth() {
		return sceneHeigth;
	}

	public void setSceneHeigth(int sceneHeigth) {
		this.sceneHeigth = sceneHeigth;
	}

}
