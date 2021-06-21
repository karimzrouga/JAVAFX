package Model;

import java.util.ArrayList;
import java.util.Arrays;

import Controls.Viewcontrol;

import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class cursors {
	private ArrayList<String> cursors = new ArrayList<String>(Arrays.asList(
			"img/iconmousecursor/cursor1.png", "img/iconmousecursor/cursor2.png",
			"img/iconmousecursor/cursor3.png", "img/iconmousecursor/cursor4.png",
			"img/iconmousecursor/cursor5.png", "img/iconmousecursor/cursor6.png",
			"img/iconmousecursor/cursor7.png", "img/iconmousecursor/cursor8.jpg",
			"img/iconmousecursor/cursor9.png", "img/iconmousecursor/cursor10.png"));
	private ArrayList<Button> listbutton;

	public cursors() {
		listbutton = new ArrayList<Button>();
		listcursor();
	}

	public void listcursor() {
		for (int i = 0; i < cursors.size(); i++) {
			// System.out.println("test"+i);
			listbutton.add(new Button());
			listbutton.get(i).setGraphic((setimg(cursors.get(i))));
			cursoraction(i, cursors.get(i));
		}
	}

	private void cursoraction(int i, String image) {
		listbutton.get(i).setOnAction(event -> {

			Viewcontrol cl = new Viewcontrol();

			cl.setCursor(new ImageCursor(new Image(image)));
		});

	}

	private ImageView setimg(String image) {
		ImageView iv = new ImageView(image);

		iv.setFitHeight(50);
		iv.setFitWidth(50);
		return iv;

	}

	public ArrayList<String> getCursors() {
		return cursors;
	}

	public void setCursors(ArrayList<String> cursors) {
		this.cursors = cursors;
	}

	public ArrayList<Button> listbutton() {
		return listbutton;
	}

	public void listbutton(ArrayList<Button> listcursors) {
		this.listbutton = listcursors;
	}

}
