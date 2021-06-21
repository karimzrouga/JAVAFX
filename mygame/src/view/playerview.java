package view;

import Model.player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class playerview {

	private static ObjectProperty<player> playerProperty = new SimpleObjectProperty<player>();

	public playerview(player p) {
		playerProperty.set(p);

	}

	public playerview() {

	}

	public ObjectProperty<player> getPlayerProperty() {
		return playerProperty;
	}

	public void setPlayerProperty(ObjectProperty<player> playerProperty) {
		playerview.playerProperty = playerProperty;
	}

	public player getPlayer() {
		return playerProperty.get();
	}

	public void setPlayer(player pp) {
		playerProperty.set(pp);
	}

}
