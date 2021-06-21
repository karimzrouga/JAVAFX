package Controls;

import Model.player;
import Model.playerdata;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import view.playerview;

public class Datacontrol {

	playerdata DB = new playerdata();
	player pv = null;

	public Datacontrol() {

	}

	public boolean createaccount(player p) {

		if (p.getName() == null || p.getPassword() == null || p.getEmail() == null || p.getPays() == null) {

			return false;
		}

		if (exsiste(p)) {

			return false;
		}

		boolean test = true;
		if (p.getEmail().indexOf('@') == -1
				&& ((p.getEmail().indexOf(".com") == -1 || p.getEmail().indexOf(".fr") == -1))) {
			test = false;

			return false;

		}

		if (p.getPassword().length() < 8) {
			test = false;
			// System.out.println("test2" + test);
			return false;

		}
		if (test) {
			// System.out.println("test3"+test);
			pv = new player(965, p.getName(), p.getEmail(), p.getPassword(), -2, -2, "l1", p.getPays());

			@SuppressWarnings("unused")
			playerview mp = new playerview(pv);
			DB.create(pv);
		}

		return test;

	}

	public boolean verifuser(player p) {
		if (p.getName() == null || p.getPassword() == null)
			return false;
		else {
			boolean test = false;
			pv = new player(-1, p.getName(), null, p.getPassword(), -999, -999, null, null);

			if (pv.getName().length() > 3)

			{
				if (exsiste(pv)) {

					test = true;
					@SuppressWarnings("unused")
					playerview mp = new playerview(DB.find(pv));

				}
			}

			return test;

		}
	}

	private boolean exsiste(player p) {

		if (DB.find(p) == null)
			return false;
		else
			return true;

	}

	public player find(player p) {
		if (DB.find(p) != null)
			return DB.find(p);
		else
			return null;

	}

	public boolean resetpassword(String username, String email) {
		if (username.length() > 3 && email.length() > 0) {
			player rp = new player(-1, username, email, null, -1, -1, null, null);
			// System.out.println(rp.toString());
			if (find(rp) != null) {
				pv = new player(-1, username, email, find(rp).getPassword());
				return true;
			}
		}
		return false;

	}

	public player find(ObservableList<player> data, int id) {
		int i = 0;
		player fp = null;
		System.out.println("id=" + id);
		while (i < data.size()) {
			if (data.get(i).getID() == id) {
				fp = data.get(i);
				// break;
			}
			i++;
		}
		return fp;
	}

	public void delete(ObservableList<player> data, int id) {
		System.out.println(find(data, id).toString());
		if (find(data, id) != null) {
			System.out.println("done");
			this.DB.delete(find(data, id));
			data.remove(find(data, id));
		}

	}

	public boolean Adminloging() {
		ObjectProperty<player> playerProperty = new SimpleObjectProperty<player>();
		playerview vp = new playerview();
		playerProperty.bind(vp.getPlayerProperty());

		if (playerProperty.get().getId() == 0 && playerProperty.get().getName().toLowerCase().compareTo("admin123") == 0
				&& playerProperty.get().getPassword().toLowerCase().compareTo("admin") == 0) {

			return true;
		}

		return false;

	}

	public void updateplayer(ObservableList<player> data, player p) {

		if (find(data, p.getID()) != null) {

			int i = data.indexOf(find(data, p.getID()));
			data.get(i).setNom(p.getNom());
			data.get(i).setEmail(p.getEmail());
			data.get(i).setNbround(p.getNbround());
			data.get(i).setScore(p.getScore());
			this.DB.update(data.get(i));
		}
	}

	public player getPv() {
		return pv;
	}

}
