package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class person {

	private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
	public person( int id,String nom,String email ,String password) {
		
		this.nom.set(nom); 
		this.id.set(id);
		this.password.set(password);
		this.email.set(email);
	}
public person( int id,String nom) {
		
		this.nom.set(nom); 
		this.id.set(id);
		
	}
public person( String nom) {
		
		this.nom.set(nom);
		
	}

public person( String nom ,String email, String password) {
	
	this.nom.set(nom);
	this.email.set(email);
	this.password.set(password);
}


	@Override
	public String toString() {
		return "id=" + getId() + ", nom=" + getNom()  + ", email=" + getEmail() + ", password=" + getPassword();
	}

	public String getNom() {
		return nom.get();
	}

	
	
	public void setpass(String pass) {
		this.password.set(pass);
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);

}
	public String getPassword() {
		return password.get();
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String mail) {
		email.set(mail);
	}
	public StringProperty getpropertyNom() {
		return nom;
	}
	public StringProperty getpropertyemail() {
		return email;
	}
	public StringProperty getpropertypassword() {
		return password;
	}
	public IntegerProperty getpropertyid() {
		return id;
	}
}
