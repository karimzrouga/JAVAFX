package view.dualmod;

import java.util.ArrayList;
import java.util.Random;
import Model.Card;

import Model.player_moddual;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import view.GameSound;
import view.world;

public class DualMod extends world {
	private player_moddual p1, p2;
	private int scorep1 = 0, scorep2 = 0, finval = 0, clic = 0;
	private StringProperty p1turn = new SimpleStringProperty("Start");
	private StringProperty p2turn = new SimpleStringProperty("Start");
	private Insets vbin;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private GridPane grid;
	private VBox vs;
	private GameSound gamesound;

	public DualMod(int nbcard) {
		super(nbcard);
		cards = super.getCards();
		grid = new GridPane();
		p1 = new player_moddual("Name:", "Score:0");
		p2 = new player_moddual("Name:", "Score:0");
		gamesound = new GameSound("src/music/correct.mp3");
	}

	public void initmenu(BorderPane p) {

		HBox menu = new HBox();
		menu.setSpacing(200);
		menu.setStyle("-fx-background-color:#000000");

		///////////////////////////////////////////////////////////////////
		Label lp1 = new Label(this.p1.getpropertyNom().get());
		lp1.textProperty().bind(this.p1.getpropertyNom());
		lp1.setStyle(textstyle());
		Label lsp1 = new Label(this.p1.getScore());
		lsp1.setStyle(textstyle());
		lsp1.textProperty().bind(this.p1.getPropertyScore());
		////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
		Label ltour1 = new Label();
		ltour1.setStyle(
				" -fx-font-size: 15px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: blue ;\r\n");
		ltour1.textProperty().bind(p1turn);
		Label lp2 = new Label(this.p2.getpropertyNom().get());
		lp2.textProperty().bind(this.p2.getpropertyNom());
		lp2.setStyle(textstyle());
		Label lsp2 = new Label(this.p2.getScore());
		lsp2.setStyle(textstyle());
		lsp2.textProperty().bind(this.p2.getPropertyScore());
		Label ltour2 = new Label("0 clic");
		ltour2.setStyle(
				" -fx-font-size: 15px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: red ;\r\n");
		ltour2.textProperty().bind(p2turn);
		//////////////////////////////////////////////////////////////
		menu.getChildren().addAll(lp1, lsp1, ltour1, lp2, lsp2, ltour2);
		p.setTop(menu);

	}

	public GridPane initworld(int nbc, int nbl, int size) {
		try {

			super.findimg();
			super.listcards(size, "img\\buttonicons\\rb.jpg");

			init(grid, nbc, nbl);

		}

		catch (Exception e) {

		}
		return grid;
	}

	private void init(GridPane g1, int nbc, int nbl) {
		int nbcard = super.getNbcard();
		int exit = 0, i = 0, j = 5;
		Integer[] index = new Random().ints(0, nbcard).distinct().limit(nbcard).boxed().toArray(Integer[]::new);
		while (exit < nbl * nbc) {
			cards.get(exit).setGraphic();

			g1.add((Button) cards.get(index[exit]).getbutton(), i, j, 1, 1);
			i++;
			if (i == nbc) {
				j++;
				i = 0;
			}

			exit++;

		}

	}

	public HBox menuendgame() {
		Button next = new Button();
		next.setStyle("-fx-background-color: none");
		next.setGraphic(btnstyle("img/buttonicons/next.png"));
		next.setOnAction(event -> {

			selectnextlevel();
			if (cards.size() == 50) {
				next.setVisible(false);
			}
		});
		Button EXIT = new Button();
		EXIT.setOnAction(e -> Platform.exit());
		EXIT.setStyle("-fx-background-color: none");
		EXIT.setGraphic(btnstyle("img/buttonicons/exx.png"));
		Button menu = new Button();
		menu.setStyle("-fx-background-color: none");
		menu.setGraphic(btnstyle("img/buttonicons/mnu.png"));
		menu.setOnAction(event -> {
			luncher_dual.Dualstage.close();
		});
		HBox mn = new HBox(menu, next, EXIT);
		mn.setSpacing(30);
		mn.setPadding(new Insets(0, 10, 10, 170));
		return mn;
	}

	private void selectinset() {
		if (cards.size() == 16) {

			vbin = new Insets(10, 10, 10, 80);
		}
		if (cards.size() == 20) {

			vbin = new Insets(10, 10, 10, 100);
		}
		if (cards.size() == 28 || cards.size() == 50) {
			vbin = new Insets(10, 10, 10, 370);
		}

	}

	@Override
	public void endgame() {

		int test = 2;
		int nbcard = super.getNbcard();
		if (finval == nbcard) {
			gamesound.changesound("src\\music\\fin.mp3");
			grid.getChildren().clear();
			Label l = new Label();

			if (scorep1 > scorep2) {
				test = 1;

			} else if (scorep1 < scorep2) {
				test = 2;

			} else {

				test = 0;
			}

			l.setFont(new Font("Tahoma", 60));

			if (test == 1) {

				l.setText(this.p1.getpropertyNom().get());
				l.setStyle(" -fx-text-fill: blue ;");
			} else if (test == 2) {
				System.out.println("p2");
				l.setStyle(" -fx-text-fill: red ;");
				l.setText(this.p2.getpropertyNom().get());
			} else {
				l.setStyle(" -fx-text-fill: green ;");
				l.setText("TWO WINNERS");
			}

			ImageView iv = new ImageView(new Image("img/backgoundlevels/796853.png"));
			l.setPadding(new Insets(10, 10, 10, 240));
			VBox vb = new VBox(iv, l, menuendgame());
			vb.setSpacing(50);
			selectinset();
			vb.setPadding(vbin);
			grid.getChildren().add(vb);

			p1turn.set("GAME");
			p2turn.set("OVER");
		}

	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void cardaction(Card card) {

		card.getbutton().setOnAction(event -> {
			if (p1.isTour() || p2.isTour()) {

				if (clic < 2) {
					if (super.getCards().size() != 0) {
						if (card.isCliced() == false) {
							card.setCliced(true);
							card.flip(card.getFront());

							position.add(super.getCards().indexOf(card));

							clic++;

							System.out.println("clic=" + clic);
							if (clic == 2) {
								final Service<Void> flipwait = new Service<Void>() {

									@Override
									protected Task<Void> createTask() {
										return new Task<Void>() {

											@Override
											protected Void call() throws Exception {
												final int maxIterations = 60000;
												for (int iterations = 0; iterations < maxIterations; iterations++) {
													System.out.println(iterations);

												}
												return null;
											}
										};

									}
								};
								flipwait.stateProperty()
										.addListener((ObservableValue<? extends Worker.State> observableValue,
												Worker.State oldValue, Worker.State newValue) -> {
											switch (newValue) {
											case FAILED:

											case CANCELLED:

											case SUCCEEDED:
												clic = 0;
												check();
												position.clear();

											}
										});
								flipwait.start();

							}
						}
					}

				}
			}
		});

	}

///////////////////////////////flipall////////////////////////////////////////
	@SuppressWarnings("incomplete-switch")
	public void flipall() {
		clic = 4;
		final Service<Void> flip = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						final int maxIterations = 550000;
						for (int iterations = 0; iterations < maxIterations; iterations++) {
							System.out.println(iterations);

						}
						return null;
					}
				};

			}
		};
		flip.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue,
				Worker.State oldValue, Worker.State newValue) -> {
			switch (newValue) {
			case FAILED:
				break;
			case CANCELLED:
				break;
			case SUCCEEDED:
				for (int i = 0; i < this.cards.size(); i++) {
					cards.get(i).flip(cards.get(i).getBack());
				}
				clic = 0;
				break;

			}
		});
		flip.start();

	}

///////////////////////////////endflipall////////////////////////////////////////
	@Override
	public boolean check() {

		if (position.size() == 2) {
			// System.out.println("here1");
			Card card1 = cards.get(position.get(0));
			Card card2 = cards.get(position.get(1));
			if (card1.getId() == card2.getId()) {
				finval += 2;

				card1.getbutton().setDisable(true);
				card2.getbutton().setDisable(true);
				if (p1.isTour()) {
					p1turn.set("Your Turn");
					p2turn.set("");
					scorep1 += 2;
					this.p1.getPropertyScore().set("Score:" + scorep1);
				} else {

					p2turn.set("Your Turn");
					p1turn.set("");
					scorep2 += 2;
					this.p2.getPropertyScore().set("Score:" + scorep2);
				}

				gamesound.changesound("src/music/correct.mp3");
				endgame();
				return true;
			} else {
				if (p1.isTour()) {
					p1turn.set("");
					p2turn.set("Your Turn");
					p1.setTour(false);
					p2.setTour(true);
				}

				else {

					p2turn.set("");
					p1turn.set("Your Turn");
					p2.setTour(false);
					p1.setTour(true);
				}

				card1.setCliced(false);
				card2.setCliced(false);
				card1.flip(cards.get(position.get(0)).getBack());
				card2.flip(cards.get(position.get(1)).getBack());
				gamesound.changesound("src/music/fail.mp3");
				endgame();
			}
		}

		clic = 0;

		return false;
	}

	private void selectnextlevel() {
		if (cards.size() == 16) {
			luncher_dual.Dualstage.close();
			Insets ins = new Insets(-30, 0, 0, 280);
			luncher_dual dualmod = new luncher_dual();

			dualmod.initdual(20, 5, 4, ins, 150).show();
		}
		if (cards.size() == 20) {
			luncher_dual.Dualstage.close();
			Insets ins = new Insets(-40, 0, 0, 0);
			luncher_dual dualmod = new luncher_dual();
			vbin = new Insets(10, 10, 10, 30);
			dualmod.initdual(28, 7, 4, ins, 140).show();
		}
		if (cards.size() == 28) {
			luncher_dual.Dualstage.close();

			luncher_dual dualmod = new luncher_dual();
			Insets ins = new Insets(-40, 0, 0, 0);
			dualmod.initdual(50, 10, 5, ins, 101).show();
		}
	}

	public void setScorep1(int scorep1) {
		this.scorep1 = scorep1;
	}

	public void setScorep2(int scorep2) {
		this.scorep2 = scorep2;
	}

	public void setTour1(boolean tour1) {
		this.p1.setTour(tour1);
	}

	public void setTour2(boolean tour2) {
		this.p2.setTour(tour2);
	}

	public void setP1(String p1) {
		this.p1.getpropertyNom().set(p1);
	}

	public void setP2(String p2) {
		this.p2.getpropertyNom().set(p2);
	}

}
