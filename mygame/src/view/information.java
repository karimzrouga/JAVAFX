package view;

import Model.player;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class information {

	@SuppressWarnings("unchecked")
	public static BorderPane levelStage() {
		BorderPane Pane = new BorderPane();
		TableView<player> table = new TableView<player>();

		TableColumn<player, String> t1 = new TableColumn<player, String>(" Name");
		TableColumn<player, String> t2 = new TableColumn<player, String>(" score");

		table.getColumns().addAll(t1, t2);
		Pane.setCenter(table);

		return Pane;

	}
}