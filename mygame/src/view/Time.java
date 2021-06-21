package view;

import java.util.ArrayList;

import Model.Card;
import javafx.animation.AnimationTimer;

import javafx.scene.control.Label;

public class Time {

	private int limit = 60, wait = 0;

	private AnimationTimer timer;

	private ArrayList<Card> cards;
	boolean test = true;

	public Time(int limit, int wait) {
		this.limit = limit+wait;
		this.wait = wait;
	}

	public Label lancerTime() {

		Label label = new Label("START");
		label.setStyle(" -fx-font-size: 25px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: white ;\r\n"
				+ "    -fx-background-color: linear-gradient(to right, #4A84AC 70%, #4A84AC 75%);"
				+ "-fx-border-radius: 15 15 10 10;" + "-fx-background-radius: 15 15 10 10;");

		timer = new AnimationTimer() {
			int i = 0;

			@Override
			public void handle(long now) {
				i++;
				int sec = i / 60;
				if (i % 60 == 0 && sec > wait) {
					sec -= wait;
					label.setText("time :" + sec);
				}

				if (limit - sec == 7) {

					label.setStyle(" -fx-font-size: 25px;\r\n" + "    -fx-font-weight: bold;\r\n"
							+ "    -fx-text-fill: white ;\r\n"
							+ "    -fx-background-color: linear-gradient(to right, #C43D3D 75%, #C43D3D 75%);"
							+ "-fx-border-radius: 15 15 10 10;" + "-fx-background-radius: 15 15 10 10;");
				}

				if (sec == wait && test) {
					test = false;
					for (int i1 = 0; i1 < cards.size(); i1++) {
						cards.get(i1).flip(cards.get(i1).getBack());
						cards.get(i1).setCliced(false);
						
					}
					
				}
				if (sec > limit - 1) {

					label.setStyle(" -fx-font-size: 25px;\r\n" + "    -fx-font-weight: bold;\r\n"
							+ "    -fx-text-fill: white ;\r\n"
							+ "    -fx-background-color: linear-gradient(to right, #52C458 75%, #52C458 75%);"
							+ "-fx-border-radius: 15 15 10 10;" + "-fx-background-radius: 15 15 10 10;");

				}
				if (cards.size() == 0) {
					label.setText("Winner");
					label.setStyle(" -fx-font-size: 25px;\r\n" + "    -fx-font-weight: bold;\r\n"
							+ "    -fx-text-fill: white ;\r\n"
							+ "    -fx-background-color: linear-gradient(to right, #52C458 75%, #52C458 75%);"
							+ "-fx-border-radius: 15 15 10 10;" + "-fx-background-radius: 15 15 10 10;");

					this.stop();

				}
				if (sec == limit + 1) {
                  
					this.stop();

					label.setText("TIME UP");
					for (int i = 0; i < cards.size(); i++) {
						cards.get(i).flip(cards.get(i).getFront());
					 
					}
					cards.clear();
					
				
				}

			}
		};
		timer.start();
		return label;

	}


	public void setlistcard(ArrayList<Card> cardss) {
		this.cards = cardss;

	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	
}