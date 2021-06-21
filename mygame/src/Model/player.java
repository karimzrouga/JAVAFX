package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class player extends person {
	
	private final StringProperty pays = new SimpleStringProperty();
	private final StringProperty Arena = new SimpleStringProperty();

	private final IntegerProperty nbround = new SimpleIntegerProperty();
	private final IntegerProperty Score = new SimpleIntegerProperty();

	public player(int id, String name, int score, int nbround, String arena, String pays) {
		super(id, name);

		this.nbround.set(nbround);
		this.Score.set(score);
		this.pays.set(pays);
		this.Arena.set(arena);

	}

	public player(int id, String name, String email, String password,  int score, int nbround, String arena,
			String pays) {
		super(id, name, email, password);

		this.nbround.set(nbround);
		this.Score.set(score);
		this.pays.set(pays);
		this.Arena.set(arena);
	
	}

	public player(int id, String name, String email, String password ) {
		super(id, name, email, password);
		
	}

	public player(String name, int score, String pays, String Arena, int nbround) {
		super(name);

		this.pays.set(pays);
		this.Arena.set(Arena);
		this.nbround.set(nbround);
		this.Score.set(score);
	}

	public String getName() {
		return super.getNom();
	}

	public void setName(String name) {
		super.setNom(name);
	}

	public String getPays() {
		return pays.get();
	}

	public void setPays(String pays) {
		this.pays.set(pays);
	}

	public int getScore() {
		return Score.get();
	}

	public void setScore(int score) {
		Score.set(score);
	}

	public String getArena() {
		return this.Arena.get();
	}

	public void setID(int iD) {
		super.setId(iD);
	}

	public void setArena(String arena) {
		this.Arena.set(arena);
	}

	public int getNbround() {
		return nbround.get();
	}

	public void setNbround(int nbround) {
		this.nbround.set(nbround);
	}

	public String getPassword() {
		return super.getPassword();
	}
	
	public String getEmail() {
		return super.getEmail();
	}

	public int getID() {
		return super.getId();
	}



	public StringProperty getpropertypays() {
		return pays;
	}

	public StringProperty getpropertyArena() {
		return Arena;
	}

	public IntegerProperty getpropertyscore() {
		return Score;
	}

	public IntegerProperty getpropertynbround() {
		return nbround;
	}

	@Override
	public String toString() {
		return "[" + super.toString() +  ",   pays=" + getPays() + ",   Arena="
				+ getArena() + ",   Score=" + getScore() + ",   nbround=" + getNbround() + "]";
	} 
	
	
	public String pertostring() {
		return "[ Arena="+ getArena() + ",   Score=" + getScore() + ",   nbround=" + getNbround() + "]";
	}
	

}
