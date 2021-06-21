package view;

import java.util.ArrayList;

import Controls.cmenu;
import Model.player;
import Model.playerdata;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ranking {
	BorderPane root;

	public ranking(BorderPane root) {
		this.root = root;
		root.setStyle("-fx-background-image:none");
	}

	@SuppressWarnings("unchecked")
	public void rankingview() {

		try {

			Button home = new Button();
			home.setOnAction(event -> {
				System.out.println("home");
				cmenu c = new cmenu();
				root.getChildren().clear();

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
			TableColumn<player, String> Name = new TableColumn<player, String>(" Name");
			Name.setMinWidth(180);
			Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
			/******************************************************************************************************************************/
			TableColumn<player, Integer> score = new TableColumn<player, Integer>(" score");
			score.setMinWidth(170);
			score.setCellValueFactory(new PropertyValueFactory<>("score"));

			/******************************************************************************************************************************/
			TableColumn<player, String> Arena = new TableColumn<player, String>("Arena");
			Arena.setMinWidth(170);
			Arena.setCellValueFactory(new PropertyValueFactory<>("Arena"));
			/******************************************************************************************************************************/
			TableColumn<player, String> pays = new TableColumn<player, String>("pays");
			pays.setMinWidth(180);
			pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
			/******************************************************************************************************************************/
			table.getColumns().addAll(Name, score, Arena, pays);

			/*******************************************************/

			playerdata playerdatav = new playerdata();
			ArrayList<player> s = playerdatav.findall();
			// System.out.println(" nn r "+s.size());
			for (int i = 0; i < s.size(); i++) {
				table.getItems().add(s.get(i));

			}

			root.setTop(menurank);
			root.setCenter(table);

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
