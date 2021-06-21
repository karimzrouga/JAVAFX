package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class player_moddual extends person {
	private final StringProperty score = new SimpleStringProperty();
	private boolean tour = false;

	public player_moddual(String name, String score) {
		super(name);

		this.score.set(score);
		

	}

	public boolean isTour() {
		return tour;
	}

	public void setTour(boolean tour) {
		this.tour = tour;
	}
	

	public StringProperty getPropertyScore() {
		return score;
	}
	public String getScore() {
		return score.get();
	}
}