package Model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {

	private String front;
	private String back;
	private int id;

	private Button button;
	private int cardsize = 0;
	private boolean cliced =false ;

	public Card(int id, String front, String back, int cardsize) {
		button = new Button();
		this.front = front;
		this.back = back;
		this.id = id;

		this.cardsize = cardsize;

	}

	public void setGraphic() {
		
		Image icard = new Image(back);
		ImageView vcard = new ImageView();
		vcard.setImage(icard);
		vcard.setFitHeight(this.cardsize);
		vcard.setFitWidth(this.cardsize);
		button.setGraphic(vcard);

	}

	public void flip(String img) {
		
		Image icard = new Image(img);
		ImageView vcard = new ImageView();
		vcard.setImage(icard);
		vcard.setFitHeight(this.cardsize);
		vcard.setFitWidth(this.cardsize);
		button.setGraphic(vcard);

	}

	@SuppressWarnings("unused")
	public void buttoncolor( String style)
	{
		 button.setStyle("-fx-background-color:'" +style + "' \r\n");
	}


	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getcardsize() {
		return cardsize;
	}

	public void setcardsize(int cardsize) {
		this.cardsize = cardsize;
	}


	
	public Button getbutton() {
		return button;
	}

	public void setbutton(Button b) {
		this.button = b;
	}

	public boolean isCliced() {
		return cliced;
	}

	public void setCliced(boolean cliced) {
		this.cliced = cliced;
	}

}
