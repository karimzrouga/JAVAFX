package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Model.Card;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;

public abstract class world {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Button> listbutton = new ArrayList<Button>();
	private ArrayList<String> fliesname = new ArrayList<String>();
	protected ArrayList<Integer> position = new ArrayList<Integer>();
	private int nbcard;

	public world(int nbcard) {
		this.nbcard = nbcard;
	}

	// recuperarion des image aprtir folder cards
	/// creation list back images*_*
	public void findimg() {
		String dossier = "F:\\MDWs2\\javafx\\project\\mygame\\src\\cards";

		File repertoire = new File(dossier);
		// repertoire.mkdir()
		if (!repertoire.exists() || !repertoire.isDirectory())
			throw new IllegalArgumentException("messa");
		else {
			File[] chemins = repertoire.listFiles();
			for (int i = 0; i < chemins.length; i++) {
				if (chemins[i].isFile()) {
					// System.out.println("fichier : "
					// +chemins[i].toString().substring(chemins[i].toString().indexOf("src")+4));
					fliesname.add(chemins[i].toString().substring(chemins[i].toString().indexOf("src") + 4));
				}
				// else
				// System.out.println("dossier : " + chemins[i]);
			}
			// System.out.println(repertoire.getName());
		}
	}

	/// creation list button*_*11
	public void listcards(int Size, String back) {

		Integer[] index2 = new Random().ints(0, fliesname.size()).distinct().limit(fliesname.size())
				.limit(fliesname.size() - 1).boxed().toArray(Integer[]::new);
		// Integer[] index1 = new Random().ints(0, fliesname.size()-1).distinct()

		for (int i = 0; i < (int) nbcard / 2; i++)

		{
			// System.out.println("**" + index2[i] + "**");
			listbutton.add(new Button());

			// index = (int) (Math.random() * (size - i + 1) + i);

			cards.add(new Card(i, fliesname.get(index2[i]), back, Size));
			cards.add(new Card(i, fliesname.get(index2[i]), back, Size));
			// System.out.println("*card" + i + "id=" + cards.get(i).getId() + "***" +
			// cards.get(i).getFront());

		}
		for (int i = 0; i < (int) nbcard; i++)

		{
			cards.get(i).buttoncolor("#EDECEA");
			cardaction(cards.get(i));
		}
	}

	public abstract void cardaction(Card card);
	
	public abstract void endgame();

	public abstract boolean check();

	public ImageView btnstyle(String image) {
		ImageView iv = new ImageView(image);

		iv.setFitHeight(60);
		iv.setFitWidth(60);
		return iv;

	}

	public String textstyle() {

		return " -fx-font-size: 15px;\r\n" + "    -fx-font-weight: bold;\r\n" + "    -fx-text-fill: white ;\r\n";
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Button> getListbutton() {
		return listbutton;
	}

	public void setListbutton(ArrayList<Button> listbutton) {
		this.listbutton = listbutton;
	}

	public ArrayList<String> getFliesname() {
		return fliesname;
	}

	public void setFliesname(ArrayList<String> fliesname) {
		this.fliesname = fliesname;
	}

	public int getNbcard() {
		return nbcard;
	}

	public void setNbcard(int nbcard) {
		this.nbcard = nbcard;
	}


}
