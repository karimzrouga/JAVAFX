package view;

import Model.player;
import Model.playerdata;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class editplayer {

	public void edit(BorderPane root, GridPane pane) {

		VBox vb = new VBox();
		Label Lnom = new Label("NAME");
		TextField tfnom = new TextField();
		Label Email = new Label("Email");
		TextField tfEmail = new TextField();
		Label LPASSWORD = new Label("PASSWORD");
		TextField tfPASSWORD = new TextField();
		Label LPAYS = new Label("PAYS");
		TextField tfPAYS = new TextField();
		Button MODIFIER = new Button("MODIFIER");
		MODIFIER.setOnAction(ev -> {
			player p = inputfiltre(tfnom, tfEmail, tfPASSWORD, tfPAYS);
			playerview pv = new playerview();

			playerdata dp = new playerdata();
			if (verif(p, pv) != 0) {
				dp.update(pv.getPlayer());
			}

			root.getChildren().remove(1);
			root.setCenter(pane);
		});
		vb.getChildren().addAll(Lnom, tfnom, Email, tfEmail, LPASSWORD, tfPASSWORD, LPAYS, tfPAYS, MODIFIER);
		vb.setPadding(new Insets(50, 10, 10, 200));

		root.setCenter(vb);

	}

	private int verif(player p, playerview pv) {
		int x = 0;
		if (!(p.getName().isEmpty())) {
			pv.getPlayer().setName(p.getName());
			x += 1;
		}
		if (!(p.getEmail().isEmpty())) {
			pv.getPlayer().setEmail(p.getEmail());
			x += 1;
		}
		if (!(p.getPays().isEmpty())) {
			pv.getPlayer().setPays(p.getPays());
			x += 1;
		}
		if (!(p.getPassword().isEmpty())) {
			pv.getPlayer().setpass(p.getPassword());
			x += 1;
		}
		return x;
	}

	public player inputfiltre(TextField tfnom, TextField tfEmail, TextField tfPASSWORD, TextField tfPAYS) {

		return new player(0, tfnom.getText(), tfEmail.getText(), tfPASSWORD.getText(), 0, 0, null, tfPAYS.getText());

	}

}
