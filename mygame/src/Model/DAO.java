package Model;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.ArrayList;

public abstract class DAO<T> {
	Connection con;

	public DAO() {

		connect();

	}

	String url;

	public abstract void create(T req);

	public abstract void update(T elmt);

	public abstract T find(T p);

	public abstract ArrayList<T> findall();

	public abstract void delete(T emlt);

	public void connect() {

		url = "jdbc:sqlite:src/BDD/userdata.db";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(url);

		}

		catch (SQLException e) {
			System.out.println("Error connextion");
			e.printStackTrace();

		}

		catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void close() {

		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
