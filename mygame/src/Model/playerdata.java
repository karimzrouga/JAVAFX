package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class playerdata extends DAO<player> {
	static ArrayList<player> player = new ArrayList<player>();

	public playerdata() {
		super();

	}

	@Override
	public void create(player p) {
		final String req = "INSERT INTO  player (name,email,password,score,arena,nbround,pays) values(?,?,?,?,?,?,?) ";

		try {
			PreparedStatement stat = super.getCon().prepareStatement(req);
			stat.setString(1, p.getName());
			stat.setString(2, p.getEmail());
			stat.setString(3, p.getPassword());
			stat.setInt(4, p.getScore());
			stat.setString(5, p.getArena());
			stat.setInt(6, p.getNbround());
			stat.setString(7, p.getPays());

			int nrow = stat.executeUpdate();
			System.out.println(nrow);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(player p) {

		try {
			final String req = "update player set  name=?, pays=?,arena=?,nbround=?, score=? where id=?";

			PreparedStatement stat = super.getCon().prepareStatement(req);
			stat.setString(1, p.getName());
			stat.setString(2, p.getPays());
			stat.setString(3, p.getArena());
			stat.setInt(4, p.getNbround());
			stat.setInt(5, p.getScore());
			stat.setInt(6, p.getID());
			int row = stat.executeUpdate();
			System.out.println(row);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	/******************************************************************/

	public String addReq(String req, String addon) {
		String where = "WHERE";
		// System.out.println("AddReq" + req);
		int test = req.toLowerCase().indexOf(where.toLowerCase());
		if (test == -1) {
			req += " WHERE " + addon;
		} else {
			req += " AND " + addon;
		}
		return req;
	}

	@Override
	public player find(player p) {
    
		player np = null;
		String req = "select * from player";
		Statement stat = null;
		/*-*/
		req = (p.getEmail() != null) ? addReq(req, ("email=" + "'" + p.getEmail() + "'")) : req;
		req = (p.getPassword() != null) ? addReq(req, ("password=" + "'" + p.getPassword() + "'")) : req;
		req = (p.getArena() != null) ? addReq(req, ("arena=" + "'" + p.getArena() + "'")) : req;
		req = (p.getScore() > -1) ? addReq(req, ("score=" + p.getScore())) : req;
		req = (p.getNbround() > -1) ? addReq(req, ("nbRound=" + p.getNbround())) : req;
		req = (p.getName() != null) ? addReq(req, ("name=" + "'" + p.getName() + "'")) : req;
		req = (p.getPays() != null) ? addReq(req, "pays=" + "'" + p.getPays() + "'") : req;
		req = (p.getID() > -1) ? addReq(req, "id=" + "'" + p.getID() + "'") : req;

		// System.out.println(req);
		/*-*/

		/*
		 * if (p.getArena()!=null) { req+= "arena="+"'"+p.getArena()+"'" ;
		 * System.out.println(req ); } if (p.getScore() >-1) { req+=
		 * "score="+p.getScore() ; System.out.println(req ); } if (p.getNbround() >-2) {
		 * req+= "nbround ="+p.getNbround() ; System.out.println(req ); } if
		 * (p.getName() !=null) { req+= "name="+"'"+p.getName()+"'" ;
		 * System.out.println(req ); } if (p.getPays() !=null) { req+=
		 * "pays="+"'"+p.getPays()+"'" ; System.out.println(req ); }
		 * 
		 * if (p.getID() !=null) { req+= "id="+"'"+p.getID()+"'" ;
		 * System.out.println(req ); }
		 */
		try {
			// System.out.println(req );
			// if (req.length()>27)
			// {
			stat = super.getCon().createStatement();

			ResultSet res = stat.executeQuery(req);
			if (res.isBeforeFirst()) {
				while (res.next()) {
					int id = res.getInt("id");
					String email = res.getString("email");
					String password = res.getString("password");
					String name = res.getString("name");
					int score = res.getInt("score");
					int nbround = res.getInt("nbround");
					String pays = res.getString("pays");
					String arena = res.getString("arena");

					np = new player(id, name, email, password, score, nbround, arena, pays);

				}
			} else {
				System.out.println("No Player Found");
				return null;
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("done ");
		return np;
	}

	/******************************************************************/
	@Override
	public void delete(player p) {
		try {
			PreparedStatement st = super.getCon().prepareStatement("DELETE FROM player WHERE id = ?");
			st.setInt(1, p.getID());
			int row = st.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<player> findall() {
		final String req = "select * from player";
		try {
			Statement stat = super.getCon().createStatement();
			ResultSet res = stat.executeQuery(req);
			while (res.next()) {
				int id = res.getInt("id");

				String name = res.getString("name");
				String email = res.getString("email");
				int score = res.getInt("score");

				int nbround = res.getInt("nbround");
				String pays = res.getString("pays");
				String arena = res.getString("arena");

				player.add(new player(id, name,email, "empty", score, nbround, arena, pays));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	public static ArrayList<player> getPlayer() {
		return player;
	}

	public static void setPlayer(ArrayList<player> player) {
		playerdata.player = player;
	}

}
