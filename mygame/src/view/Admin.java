package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import Controls.Datacontrol;
import Controls.Viewcontrol;
import Model.FileIO;
import Model.player;
import Model.playerdata;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Admin {
	// list player contient resulta findall
	private ObservableList<player> data = FXCollections.observableArrayList();
	private playerdata playerdatav = new playerdata();

	public Admin() {

	}

	@SuppressWarnings("unchecked")
	public Stage adminspace() {
		Stage secondStage = new Stage();
		Stage fileStage = new Stage();

		try {
			FileIO ioplayer = new FileIO();

			TableView<player> table = new TableView<player>();
			VBox br = new VBox();
			br.setPadding(new Insets(10, 75, 10, 75));
			Button btnimg = new Button("Add card image ");

			btnimg.setGraphic(btngrafic("img/buttonicons/addimg.png"));

			Button btnexportfile = new Button("Export dataplayers");
			btnexportfile.setGraphic(btngrafic("img/buttonicons/export-data-icon-9.jpg"));
			Button btnimportfile = new Button("Import dataplayers");
			btnimportfile.setGraphic(btngrafic("img/buttonicons/move-waiting-up.png"));
			btnimportfile.setOnAction(e -> {

				ioplayer.inputfile(fileStage, data);
				findall(table);
			});
			btnimg.setOnAction(e -> {
				inputimgfile(fileStage);
			});
			btnexportfile.setOnAction(e -> {

				ioplayer.Outputfile(fileStage, data);
			});
			Label Lid = new Label("ID");
			TextField tfid = new TextField();
			Label LEmail = new Label("Email");
			TextField tfEmail = new TextField();
			Label Lnom = new Label("NAME");
			TextField tfnom = new TextField();
			Label LSCORE = new Label("SCORE");
			TextField tfScore = new TextField();
			Label LNBROUND = new Label("NBROUND");
			TextField tfNBROUND = new TextField();
			Label LARENA = new Label("ARENA");
			TextField tfArena = new TextField();
			Label LPAYS = new Label("PAYS");
			TextField tfPAYS = new TextField();
			////////////// clear input
			Button cleartextfiled = new Button("RESET");
			cleartextfiled.setOnAction(event -> {
				tfnom.clear();
				tfEmail.clear();
				tfid.clear();
				tfScore.clear();
				tfNBROUND.clear();
				tfArena.clear();
				tfPAYS.clear();
			});
			VBox inputtxt = new VBox();
			inputtxt.getChildren().addAll(Lid, tfid, Lnom, tfnom, LEmail, tfEmail, LSCORE, tfScore, LNBROUND, tfNBROUND,
					LARENA, tfArena, LPAYS, tfPAYS, cleartextfiled, br, btnimg, btnimportfile, btnexportfile);

			inputtxt.setSpacing(5);
			secondStage.setTitle("ADMIN:EDIT");
			/* add button */
			Button add = btnstyle("img/buttonicons/add.png");
			add.setOnAction(event -> {
				player p = inputfiltre(tfid, tfnom, tfEmail, null, tfScore, tfNBROUND, tfArena, tfPAYS);
				data.add(p);
				table.getItems().add(p);
				findall(table);
			});
			titleindication(add  ,"add player");
			/* delete button */
			Datacontrol dc = new Datacontrol();
			Button delete = btnstyle("img/buttonicons/delete.png");
			delete.setOnAction(event -> {
				if (!(tfid.getText().isEmpty())) {
					int id = Integer.parseInt(tfid.getText());
					dc.delete(data, id);
					findall(table);
				}

			});
			titleindication(delete  ,"delete player");
			/* update button */

			Button update = btnstyle("img/buttonicons/active-directory-icon-png-5501.png");
			update.setOnAction(event -> {

				player p = inputfiltre(tfid, tfnom, tfEmail, null, tfScore, tfNBROUND, tfArena, tfPAYS);
				// System.out.println(p.toString());
				dc.updateplayer(data, p);
				findall(table);
			});
			titleindication(update   ,"update player");
			Button find = btnstyle("img/buttonicons/find.png");
			find.setOnAction(event -> {

				player p =new player(Integer.parseInt(tfid.getText()), tfnom.getText(), -1, -1, null, null);
			
				table.getItems().clear();
				table.getItems().addAll(this.playerdatav.find(p));
				//table.refresh();
			});
			titleindication(find  ,"find player");
			/* find All button */

			Button findAll = btnstyle("img/buttonicons/findall.png");
			findAll.setOnAction(event -> {

				table.getItems().clear();
				findall(table);
			});
			titleindication(findAll  ,"find  All player");
			/* exit */
			Button exit = btnstyle("img/buttonicons/exite.png");
			exit.setOnAction(e -> Platform.exit());
			titleindication(exit  ,"exit");
			Button Home = new Button();
			Home.setOnAction(event -> {
				Viewcontrol navc = new Viewcontrol();
				navc.setClic(0);
				secondStage.close();
			});
			titleindication(Home  ,"Home");
			Image home = new Image("img/buttonicons/home.png");
			ImageView hv = new ImageView();
			hv.setImage(home);
			hv.setFitHeight(30);
			hv.setFitWidth(30);
			Home.setGraphic(hv);

			HBox editmenu = new HBox(Home, add, delete, update, find, findAll, exit);
			editmenu.setSpacing(40);

			table.setEditable(true);
			TableColumn<player, String> ID = new TableColumn<player, String>("ID");
			ID.setMinWidth(100);
			ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
			TableColumn<player, String> Name = new TableColumn<player, String>(" Name");
			Name.setMinWidth(100);
			Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
			TableColumn<player, String> email = new TableColumn<player, String>("email");
			email.setCellValueFactory(new PropertyValueFactory<>("email"));
			email.setMinWidth(200);
			TableColumn<player, Integer> TopScore = new TableColumn<player, Integer>("TopScore");
			TopScore.setMinWidth(100);
			TopScore.setCellValueFactory(new PropertyValueFactory<>("score"));

			TableColumn<player, String> Arena = new TableColumn<player, String>("Arena");
			Arena.setMinWidth(100);
			Arena.setCellValueFactory(new PropertyValueFactory<>("Arena"));

			TableColumn<player, String> pays = new TableColumn<player, String>("Pays");
			pays.setMinWidth(100);
			pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
			table.getColumns().addAll(ID, Name, email, TopScore, Arena, pays);

			data.addAll(playerdatav.findall());
			// System.out.println("here"+data.toString());
			table.getItems().addAll(data);
///////////////////////////////

			//////////////////////////////////////////////////////////////
			BorderPane root = new BorderPane();
			root.setTop(editmenu);
			root.setCenter(table);
			root.setRight(inputtxt);
			Scene scene = new Scene(root, 950, 610);
			secondStage.setTitle("FX GAME ZONE");
			secondStage.setAlwaysOnTop(true);
			secondStage.getIcons().add(new Image("img/buttonicons/stageicon.jpg"));
			secondStage.setScene(scene);
			secondStage.setOnCloseRequest(event -> {
				Viewcontrol navc = new Viewcontrol();
				navc.setClic(0);
			});

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return secondStage;

	}

	

	private void findall(TableView<player> table) {
		table.getItems().clear();
		table.getItems().addAll(data);
		table.refresh();
	}

	private ImageView btngrafic(String img) {
		Image img2 = new Image(img);
		ImageView iv2 = new ImageView();
		iv2.setImage(img2);
		iv2.setFitHeight(40);
		iv2.setFitWidth(40);
		return iv2;
	}

	private void inputimgfile(Stage fileStage) {
		FileChooser file = new FileChooser();
		file.setTitle("chose Image");
		File file1 = file.showOpenDialog(fileStage);
		if (file1 != null) {

			addsrc(file1);
			System.out.println("myfile" + file1);

		} else {
			System.out.println("chossefile ");
		}
	}

	private Button btnstyle(String ch) {
		Button add = new Button();

		Image img = new Image(ch);
		ImageView iv = new ImageView();
		iv.setImage(img);
		iv.setFitHeight(30);
		iv.setFitWidth(30);
		add.setGraphic(iv);
		return add;
	}

	private void addsrc(File selectedFileimg) {
		Path to;
		Path from;

		try {

			if (selectedFileimg != null) {
				from = Paths.get(selectedFileimg.toURI());
				to = Paths.get("F:\\MDWs2\\javafx\\project\\mygame\\src\\cards\\" + selectedFileimg.getName());
				Files.copy(from, to);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private  void titleindication( Button btn  ,String msg)
	{
		Tooltip tt = new Tooltip();
		tt.setText(msg);
		tt.setStyle("-fx-font: normal bold 10 Langdon; "
		    + "-fx-base: #AE3522; "
		    + "-fx-text-fill: white;");
		btn .setTooltip(tt);
	}

	@SuppressWarnings("unused")
	private player inputfiltre(TextField tfid, TextField tfnom, TextField tfEmail, TextField tfpassword,
			TextField tfScore, TextField tfNBROUND, TextField tfArena, TextField tfPAYS) {

		int id = -1, score = -1, nbround = -1;
		if (tfid != null && tfid.getText().length() != 0) {
			id = Integer.parseInt(tfid.getText());
		}
		if (tfScore != null && tfScore.getText().length() != 0) {
			score = Integer.parseInt(tfScore.getText());
		}
		if (tfNBROUND != null && tfNBROUND.getText().length() != 0) {
			nbround = Integer.parseInt(tfNBROUND.getText());
		}
		System.out.println(new player(id, tfnom.getText(), tfEmail.getText(), "*****", score, nbround, tfArena.getText(),
				tfPAYS.getText()).toString());
		return new player(id, tfnom.getText(), tfEmail.getText(), "*****", score, nbround, tfArena.getText(),
				tfPAYS.getText());
	}
}