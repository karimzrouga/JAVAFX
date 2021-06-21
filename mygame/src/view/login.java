package view;



import javax.mail.MessagingException;

import Controls.Datacontrol;

import Controls.cmenu;
import Model.Sendalert;
import Model.player;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class login {
	private player p;
	private BorderPane Pane = new BorderPane();
	private level l;

	public login(BorderPane Pane) {
		l = new level(700, 500);

		this.Pane = Pane;
	}

	public void loginview() {

		try {
			Datacontrol mycontrol = new Datacontrol();

			Text alert = new Text();
			HBox loginmenu = new HBox();
			loginmenu.setPadding(new Insets(50, 0, 10, 70));
			VBox loginaction = new VBox();
			loginaction.setSpacing(20);
			loginaction.setPadding(new Insets(25, 0, 10, 280));
			///////////// homebutton///////////

			Button prd = new Button();
			prd.setOnAction(event -> {
				Pane.setStyle("-fx-background-image: none");
				cmenu c = new cmenu();
				Pane.getChildren().clear();
				c.Menu(Pane);

			});
			prd.getStyleClass().add("mybutton");
			Image iconprd = new Image("/img/buttonicons/prd.png");
			ImageView icon = new ImageView();
			icon.setImage(iconprd);
			icon.setFitHeight(40);
			icon.setFitWidth(40);
			prd.setGraphic(icon);
			////////////////////////

			//////////////////////// start //////////////////////////////////

			TextField playername = new TextField();
			Label Username = new Label("Your Username");

			Label Userpassword = new Label("Your Password");

			PasswordField password = new PasswordField();

			Button start = new Button("Start") {
				{
					setOnAction(event -> {

						if (!(playername.getText().isEmpty() && password.getText().isEmpty())) {
							p = new player(-2, playername.getText(), null, password.getText());

							if (mycontrol.verifuser(p)) {

								Pane.getChildren().clear();
								Pane.getChildren().add(l.levelview());

							} else {

								alert.setText("You have entered an invalid username or password");
								alert.setFill(Color.FIREBRICK);

							}
						} else {
							alert.setText(
									"required information is missing or incomplete. Please correct your entries and try again");
							alert.setFill(Color.FIREBRICK);

						}

					});
					
				}
			};
			start.setId("startbutton");
			Button forget = new Button("forget password ?");
			forget.setId("forgetbutton");

			Button login = new Button() {
				{
					setOnAction(event -> {

						loginaction.getChildren().clear();
						loginaction.getChildren().addAll(Username, playername, Userpassword, password, forget, start);
						loginaction.setPadding(new Insets(30, 10, 10, 200));
					});
				}
			};

			login.setStyle(mybuttonstyle("img/buttonicons/login.png"));

			//////////////////////////////////////////////////////////
////////////////////////create //////////////////////////////////
			Text title = new Text("create account");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			title.setFill(Color.WHITE);

			Label Useremail = new Label("Your Email");
			TextField Email = new TextField();
			Label Userpays = new Label("your country");
			TextField pays = new TextField();

			Button add = new Button("create") {
				{
					setOnAction(event -> {

						if (!(playername.getText().isEmpty() && password.getText().isEmpty()
								&& pays.getText().isEmpty())) {

							p = new player(-1, playername.getText(), Email.getText(), password.getText(), -1, -1, "l1",
									pays.getText());
							if (mycontrol.createaccount(p)) {
								@SuppressWarnings("unused")
								playerview vp = new playerview(p);
								Pane.getChildren().clear();
								Pane.getChildren().add(l.levelview());
								Sendalert welcome = new Sendalert(Email.getText(), "ADMIN@gmail.com",
										"PASSWORDADMIN");
								try {
									welcome.sendMail("wlc");
								} catch (MessagingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								alert.setText(
										"required information is missing or incomplete. Please correct your entries and try again");
								alert.setFill(Color.FIREBRICK);

							}

						} else {
							alert.setText(
									"required information is missing or incomplete. Please correct your entries and try again");
							alert.setFill(Color.FIREBRICK);
						}

					});
				}
			};

			Button sendalert = new Button("reset");
			add.setId("startbutton");
			//////////////////// forget bloc ///////////////////////
			forget.setOnAction((event -> {
				alert.setText("");
				loginaction.getChildren().clear();
				loginaction.getChildren().addAll(Username, playername, Useremail, Email, sendalert);
				loginaction.setSpacing(10);
				loginaction.setPadding(new Insets(20, 10, 10, 200));

			}));
			Button reset = new Button("reset");
			reset.setId("startbutton");
			sendalert.setOnAction((event -> {
				if (mycontrol.resetpassword(playername.getText(), Email.getText())) {
					Label codeval = new Label("Code validation");
					TextField code = new TextField();
					alert.setText("Send the code  to :  " + Email.getText());
					alert.setFill(Color.WHITE);
					loginaction.getChildren().clear();
					loginaction.getChildren().addAll(codeval, code, reset);
					loginaction.setSpacing(10);
					loginaction.setPadding(new Insets(20, 10, 10, 200));

					Sendalert se = new Sendalert(Email.getText(), "zrougakarim88@gmail.com", "karim21126340");
					try {
						se.sendMail("reset");
						reset.setOnAction((ev -> {

							if (code.getText().compareTo(se.getCode()) == 0) {
								loginaction.getChildren().clear();

								password.backward();
								password.setText(mycontrol.getPv().getPassword());
								loginaction.getChildren().addAll(Username, playername, Userpassword,
										new TextField(mycontrol.getPv().getPassword()), start);
								loginaction.setPadding(new Insets(30, 10, 10, 200));
								alert.setText("Your acoount has been confirmed");
								alert.setFill(Color.WHITE);
							} else {
								alert.setText("Code de validation INCORRECT");
								alert.setFill(Color.FIREBRICK);
							}

						}));

					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					alert.setText(
							"required information is missing or incomplete. Please correct your entries and try again");
					alert.setFill(Color.FIREBRICK);
				}

			}));
			//////////////////// forget///////////////////////
			Button create = new Button() {
				{
					setOnAction(event -> {

						loginaction.getChildren().clear();
						loginaction.getChildren().addAll(Username, playername, Useremail, Email, Userpassword, password,
								Userpays, pays, add);
						loginaction.setSpacing(5);
						loginaction.setPadding(new Insets(1, 10, 10, 200));
					});
				}
			};

			create.setStyle(mybuttonstyle("img/buttonicons/createaccount.png"));
////////////////////////create //////////////////////////////////
			Pane.setStyle("-fx-background-image: url(\"img/backgoundlevels/back2.png\");"
					+ "-fx-background-position:  center;" + " -fx-background-repeat: no-repeat;"
					+ "-fx-background-size: cover, auto;"

			);

			Button guestplayer = new Button() {
				{
					setOnAction(event -> {
						@SuppressWarnings("unused")
						playerview vp = new playerview(
								new player(-1, "guest", "guest", "guest", -1, -1, "guest", "guest"));
						Pane.getChildren().clear();
						Pane.getChildren().add(l.levelview());

					});
				}
			};

			guestplayer.setStyle(mybuttonstyle("img/buttonicons/guest.png"));
			loginmenu.getChildren().add(prd);
			loginaction.getChildren().addAll(login, create, guestplayer);

			Pane.setTop(loginmenu);
			Pane.setCenter(loginaction);
			Insets insets = new Insets(0, 20, 70, 140);
			Pane.setBottom(alert);
			BorderPane.setMargin(alert, insets);
		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	private String mybuttonstyle(String background) {
		return ("-fx-background-image: url('" + background + "'); " + "-fx-background-position:  center;"
				+ " -fx-background-repeat: no-repeat;" + "-fx-background-size: cover, auto;"
				+ "    -fx-background-radius: 50;\r\n" + "    -fx-padding: 25 25 25 145;\r\n"
				+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
	}

}
