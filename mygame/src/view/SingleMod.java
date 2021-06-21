package view;

import java.util.ArrayList;

import java.util.Random;

import Controls.Viewcontrol;
import Model.Card;
import Model.FileIO;
import Model.player;
import Model.playerdata;
import javafx.application.Platform;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SingleMod extends world {
	@SuppressWarnings("unused")
	private BorderPane bp;//
	private GridPane Pane;//
	private Stage WorldStage;//
	private int nbcard = 0, nbc = 0;//
	private int scenewidth;//
	private int sceneheigth;//
	private String level; //
	private int nbflip;//
	private int clic = 0;//
	private int score = 0;//
	private StringProperty pscore = new SimpleStringProperty();//
	private Viewcontrol navc = new Viewcontrol();//
	private Time gametime ;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private static int nbround = 0;
	playerview vp = new playerview();

	/*****************************************/

	/*************************************************************/

	public SingleMod(String level, int nbcard, int nbc, int scenewidth, int sceneheigth) {

		super(nbcard);
		cards = super.getCards();
		nbround++;
		navc.setClic(1);
		this.nbcard = nbcard;
		this.nbc = nbc;
		this.bp = new BorderPane();
		this.WorldStage = new Stage();
		this.scenewidth = scenewidth;
		this.sceneheigth = sceneheigth;
		this.level = level;
		if (sceneheigth >= 1000 || scenewidth >= 1000) {
			WorldStage.setMaximized(true);

		}

	}

	/*************************************************************/
	public Stage CreateWorld(String background, int vpadding, int spacing, int limittime, int wait) {
		try

		{
			System.out.println(cards.size());
			SimpleObjectProperty<player> playerProperty = new SimpleObjectProperty<player>();

			playerProperty.bind(vp.getPlayerProperty());
			
			 gametime = new Time(limittime, wait);

			super.findimg();
			BorderPane bp = new BorderPane();

			bp.setStyle("-fx-background-image: url('" + background + "'); " + "-fx-background-position: center center; "
					+ "-fx-background-repeat: no-repeat;" + "-fx-background-size: cover");

			HBox vmenu = new HBox();
			vmenu.setSpacing(spacing);
			vmenu.setPadding(new Insets(10, 0, 0, vpadding));
			Label playername = new Label("Name:" + playerProperty.get().getName());
			playername.setStyle(textstyle());

			Label playerscore = new Label();
			pscore.set("Score:0");
			playerscore.textProperty().bind(pscore);
			playerscore.setStyle(textstyle());
			Label level = new Label("level:" + this.level);
			level.setStyle(textstyle());
			//////////////////////////////////////////////////////////////////////

			/////////////////////////////////////////////////////////////////////
			Label time = gametime.lancerTime();

			time.setMinHeight(2);
			time.setAlignment(Pos.TOP_CENTER);
			vmenu.getChildren().addAll(playername, time, playerscore, level);

			Pane = initgrid();
			// Pane = new GridPane();

			Pane.setPadding(new Insets(-5, 30, 30, 30));

			Pane.setHgap(10);
			Pane.setVgap(10);
			bp.setCenter(Pane);
			bp.setTop(vmenu);

			Scene scene = new Scene(bp, this.scenewidth, this.sceneheigth);
			Viewcontrol cl = new Viewcontrol();
			scene.cursorProperty().bind(cl.getCursor());
			WorldStage.setScene(scene);
			WorldStage.resizableProperty().setValue(Boolean.FALSE);
			WorldStage.setTitle("FX GAME ZONE");
			WorldStage.setAlwaysOnTop(true);
			WorldStage.getIcons().add(new Image("img/buttonicons/stageicon.jpg"));
			WorldStage.setScene(scene);

			WorldStage.setOnCloseRequest(event -> {
				Viewcontrol navc = new Viewcontrol();
				navc.setClic(0);
			});

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return WorldStage;
	}

	/*************************************************************/
	// add card on the grid
	public GridPane initgrid() {
		gametime.setlistcard(cards);

		if (scenewidth <= 650) {
			// level1
			listcards(150, "img/buttonicons/lvl1.png");
		}
		if (scenewidth <= 750 && scenewidth > 650) {
			// level2
			listcards(150, "img/buttonicons/lvl2.png");
		}

		if (scenewidth <= 800 && scenewidth > 750) {
			// level3
			listcards(120, "img/buttonicons/l1.png");
		} else if (scenewidth <= 950 && scenewidth > 800) {
			// level4
			listcards(110, "img/buttonicons/l2.png");

		} else if (scenewidth <= 1000 && scenewidth >= 950) {
			// level5
			listcards(100, "img/buttonicons/l3.png");

		}

		else {
			// l6
			listcards(85, "img/buttonicons/l4.png");
		}

		GridPane p = new GridPane();
		int exit = 0, i = 0, j = 5;
		Integer[] index = new Random().ints(0, nbcard).distinct().limit(nbcard).boxed().toArray(Integer[]::new);
		while (exit < nbcard) {

			cards.get(exit).setGraphic();
			cardaction(cards.get(exit));
			p.add((Button) cards.get(index[exit]).getbutton(), i, j, 1, 1);

			i++;
			if (i == nbc) {
				j++;
				i = 0;
			}

			exit++;
		}
		///// flip back all cards

		for (int i1 = 0; i1 < cards.size(); i1++) {
			cards.get(i1).flip(cards.get(i1).getFront());
			cards.get(i1).setCliced(true);
		}
		return p;
	}

	/*************************************************************/

	@SuppressWarnings("incomplete-switch")
	@Override
	public void cardaction(Card card) {
		card.getbutton().setOnAction(event -> {
			if (cards.size() != 0 && clic < 2 && card.isCliced() == false) {

				card.flip(card.getFront());

				position.add(cards.indexOf(card));

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
					flipwait.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue,
							Worker.State oldValue, Worker.State newValue) -> {
						switch (newValue) {
						case FAILED:
							break;
						case CANCELLED:
							break;
						case SUCCEEDED:
							clic = 0;
							check();
							position.clear();
							break;
						}
					});
					flipwait.start();
				}

			}

		});
	}

	/*************************************************************/
	@Override
	public boolean check() {

		GameSound swin = new GameSound("src/music/correct.mp3");
		if (position.size() == 2) {
			Card card1 = cards.get(position.get(0));
			Card card2 = cards.get(position.get(1));
			if (card1.getId() == card2.getId()) {
				// System.out.println("here2");
				card1.getbutton().setDisable(true);
				card2.getbutton().setDisable(true);
				score += 2;
				pscore.set("Score:" + score);

				nbflip += 2;
				swin.changesound("src/music/correct.mp3");
				endgame();
				return true;
			} else {

				// System.out.println("here3");
				/*
				 * new Timeline(new KeyFrame( Duration.millis(2500), ae -> return true ))
				 * .play();
				 */
				card1.setCliced(false);
				card2.setCliced(false);
				card1.flip(cards.get(position.get(0)).getBack());
				card2.flip(cards.get(position.get(1)).getBack());
				swin.changesound("src/music/fail.mp3");

				return false;

			}
		}

		clic = 0;

		return false;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getNbc() {
		return nbc;
	}

	public void setNbc(int nbc) {
		this.nbc = nbc;
	}

	public HBox menuendgame(String img, String nextimg, String menuimg, String Exitimg, Insets ins) {
		Button next = new Button();
		next.setStyle("-fx-background-color: none");
		next.setGraphic(btnstyle("img/buttonicons/" + nextimg));
		next.setOnAction(event -> {
			this.WorldStage.close();
			switch (this.level) {

			case "L1":

				SingleMod gameworld = new SingleMod("L2", 12, 4, 750, 620);
				gameworld.CreateWorld("/img/backgoundlevels/pu.jpg", 20, 100, 18, 3).show();

				break;
			case "L2":

				gameworld = new SingleMod("L3", 18, 5, 800, 650);
				gameworld.CreateWorld("/img/backgoundlevels/space.png", 30, 120, 28, 6).show();
				break;
			case "L3":
				gameworld = new SingleMod("L4", 24, 6, 901, 650);
				gameworld.CreateWorld("/img/backgoundlevels/gray.jpg", 50, 140, 50, 9).show();
				break;
			case "L4":
				gameworld = new SingleMod("L5", 50, 10, 1000, 1000);
				gameworld.CreateWorld("/img/backgoundlevels/pink.jpg", 70, 280, 80, 9).show();
				break;
			case "L5":
				next.setVisible(false);
				gameworld = new SingleMod("L6", 72, 12, 1100, 1001);
				gameworld.CreateWorld("/img/backgoundlevels/yellow.jpg", 120, 250, 12, 15).show();
				break;

			}
		});
		Button EXIT = new Button();
		EXIT.setOnAction(e -> Platform.exit());
		EXIT.setStyle("-fx-background-color: none");
		EXIT.setGraphic(btnstyle("img/buttonicons/exx.png"));
		Button EXIT1 = new Button();

		EXIT1.setOnAction(event -> {
			Viewcontrol navc = new Viewcontrol();
			navc.setClic(0);
			this.WorldStage.close();
		});
		EXIT1.setStyle("-fx-background-color: none");
		EXIT1.setGraphic(btnstyle("img/buttonicons/" + Exitimg));
		Button menu = new Button();
		menu.setStyle("-fx-background-color: none");
		menu.setGraphic(btnstyle("img/buttonicons/" + menuimg));
		menu.setOnAction(event -> {
			this.WorldStage.close();

		});
		HBox mn = new HBox(next, EXIT1, EXIT);
		mn.setSpacing(30);
		mn.setPadding(ins);
		return mn;
	}

	@Override
	public void endgame() {
 
		if (nbflip * 2 == cards.size()) {
			cards.clear();
			Pane.getChildren().clear();
			Pane.getChildren()
					.add(menuendgame("next.png", "next.png", "exx.png", "mnu.png", new Insets(200, 10, 10, 170)));
			this.vp.setPlayer(new player(clic, vp.getPlayer().getName(), score, nbround, level, ""));
			// System.out.println (this.vp.getPlayer().toString());
			playerdata bd = new playerdata();
			bd.update(this.vp.getPlayer());
			FileIO dataobj = new FileIO();
			dataobj.savedata(this.vp.getPlayer());
		}
	}

}