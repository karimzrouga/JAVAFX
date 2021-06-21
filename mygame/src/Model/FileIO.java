package Model;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.playerview;

public class FileIO {
	private File myfile;
	private ArrayList<String> listdataplayer = new ArrayList<String>();
	private ArrayList<player> listplayer = new ArrayList<player>();

////////////////////////////////constructeur
	public FileIO() {

		myfile = new File("dataplayer");

	}

///////////////////////////////////export file
	public void Outputfile(Stage fileStage, ObservableList<player> data) {
		FileChooser file = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

		file.getExtensionFilters().add(extFilter);
		file.setTitle("save file");
		myfile = file.showSaveDialog(fileStage);
		if (myfile != null) {

			System.out.println("myfile" + myfile.getAbsolutePath());
			SaveFile(data);

		} else {
			System.out.println("chossefile ");
		}
	}

//////////////////////////////////importfile
	public void inputfile(Stage fileStage, ObservableList<player> data) {
		FileChooser file = new FileChooser();
		file.setTitle("chose file");
		File file1 = file.showOpenDialog(fileStage);
		if (file1 != null) {

			readFile(data, file1);
		} else {
			System.out.println("null file ");
		}

	}

///////////////////////writefile exported
	public void SaveFile(ObservableList<player> data) {
		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter(myfile);
			for (int i = 0; i < data.size(); i++) {
				fileWriter.write(data.get(i).toString() + "\n");
			}

			fileWriter.close();
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

	public void savedata(player p) {

		playerview vp = new playerview();

		File file = new File("src\\datafile\\" + vp.getPlayer().getName() + ".txt");

		try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(vp.getPlayer().pertostring());
			bw.newLine();
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

	public void importdataplayer(ObservableList<player> data) {
		playerview vp = new playerview();
		File file = new File("src\\datafile\\" + vp.getPlayer().getName() + ".txt");
		readFiledata(data, file);

	}

///////////////////////readfile imported

	public void readFile(ObservableList<player> data, File ofile) {
		try {

			FileReader fr = new FileReader(ofile);
			int ptr1;
			String chaine = "";
			char ch;

			boolean test = false;
			while ((ptr1 = fr.read()) != -1) {
				ch = (char) ptr1;

				if (ch != '[' && ch != ']') {

					if (ch == '=') {
						test = true;
						ptr1 = fr.read();
					}
					if (ch == ',' || ch == '\n') {
						test = false;
						addplayer(chaine);

						chaine = "";
					}

					if (test) {

						ch = (char) ptr1;
						chaine += ch;

					}

				}

			}

			fr.close();
			data.addAll(listplayer);
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

////////////////////////////////////////////////////////////////////////////////////////
	public void readFiledata(ObservableList<player> data, File ofile) {
		try {

			FileReader fr = new FileReader(ofile);
			int ptr1;
			String chaine = "";
			char ch;

			boolean test = false;
			while ((ptr1 = fr.read()) != -1) {
				ch = (char) ptr1;

				if (ch != '[' && ch != ']') {

					if (ch == '=') {
						test = true;
						ptr1 = fr.read();
					}
					if (ch == ',' || ch == '\n') {
						test = false;

						adddata(chaine);
						chaine = "";
					}

					if (test) {

						ch = (char) ptr1;
						chaine += ch;

					}

				}

			}

			fr.close();
			data.addAll(listplayer);
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

	//////////////////////////////////////////
	private void adddata(String chaine) {
		// System.out.println("here");

		if (listdataplayer.size() < 3) {
			listdataplayer.add(chaine);
		//	System.out.println("here1");
		}

		if (listdataplayer.size() == 3) {
			// System.out.println("here2");

			int score = Integer.parseInt(listdataplayer.get(1));
			String strNew = listdataplayer.get(2).substring(0, 1);
		//	System.out.println(strNew);
			int nbr = Integer.parseInt(strNew);
			player p = new player(-1, "", score, nbr, listdataplayer.get(0), listdataplayer.get(0));

			listplayer.add(p);
			listdataplayer.clear();
		}
///////////////////////////////////////////////////////////////////////////////////////
	}

	private void addplayer(String chaine) {
		if (listdataplayer.size() < 8) {
			listdataplayer.add(chaine);
		}
		if (listdataplayer.size() == 8) {
			int id = Integer.parseInt(listdataplayer.get(0)), score = Integer.parseInt(listdataplayer.get(6)),
					nbround = Integer.parseInt(listdataplayer.get(7));
			/*
			 * System.out.println(l.get(0)+"**"+i); System.out.println(l.get(7)+"**"+j);
			 * System.out.println(l.get(8)+"**"+k);
			 */
			player p = new player(id, listdataplayer.get(1), listdataplayer.get(2), listdataplayer.get(3), score,
					nbround, listdataplayer.get(6), listdataplayer.get(5));

			listplayer.add(p);
			listdataplayer.clear();
		}

	}

	public ArrayList<player> getListplayer() {
		return listplayer;
	}

	public void setListplayer(ArrayList<player> listplayer) {
		this.listplayer = listplayer;
	}

	public ArrayList<String> getListdataplayer() {
		return listdataplayer;
	}

	public void setListdataplayer(ArrayList<String> listdataplayer) {
		this.listdataplayer = listdataplayer;
	}

}
