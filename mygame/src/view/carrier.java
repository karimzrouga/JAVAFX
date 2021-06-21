package view;

import java.util.ArrayList;

import Controls.cmenu;
import Model.FileIO;
import Model.player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class carrier {
	private BorderPane root;
	private ObservableList<player> data = FXCollections.observableArrayList();

	public carrier(BorderPane root) {
		this.root = root;

	}

	@SuppressWarnings("unchecked")
	public void carrierview() {

		try {

			Button home = new Button();
			home.setOnAction(event -> {
				System.out.println("home");
				cmenu c = new cmenu();
				root.getChildren().clear();
				root.setStyle("-fx-background-image:none");
				c.Menu(root);
			});
			home.getStyleClass().add("mybutton");
			Image ihome = new Image("/img/buttonicons/prd.png");
			ImageView hv = new ImageView();
			hv.setImage(ihome);
			hv.setFitHeight(30);
			hv.setFitWidth(30);
			home.setGraphic(hv);

			HBox menurank = new HBox(home);
			// menurank.setSpacing(300);
			/******************************************************************************************************************************/
			TableView<player> table = new TableView<player>();
			table.setEditable(true);

			/******************************************************************************************************************************/
			TableColumn<player, Integer> score = new TableColumn<player, Integer>(" score");
			score.setMinWidth(200);
			score.setCellValueFactory(new PropertyValueFactory<>("score"));

			/******************************************************************************************************************************/
			TableColumn<player, String> Arena = new TableColumn<player, String>("Arena");
			Arena.setMinWidth(200);
			Arena.setCellValueFactory(new PropertyValueFactory<>("Arena"));
			/******************************************************************************************************************************/
			TableColumn<player, String> nbround = new TableColumn<player, String>("nbround");
			nbround.setMinWidth(200);
			nbround.setCellValueFactory(new PropertyValueFactory<>("nbround"));
			/******************************************************************************************************************************/
			table.getColumns().addAll(Arena, score, nbround);

			/*******************************************************/
			@SuppressWarnings("unused")
			ArrayList<player> players = new ArrayList<player>();
			FileIO objdata = new FileIO();
			objdata.importdataplayer(data);
			table.getItems().addAll(data);

			root.setTop(menurank);
			root.setCenter(table);

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
