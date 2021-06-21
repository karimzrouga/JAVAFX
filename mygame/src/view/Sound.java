package view;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class Sound {
	private static MediaPlayer mediaplayer;
	private static Media media;
	private static String url;

	public Sound(String url) {
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();

		if (media == null && mediaplayer == null) {
			Sound.url = url;
			createsound();

		}
		Sound.url = url;

	}

	public void createsound() {
		System.out.println("donemusic1");

		File fc = new File(url);
		if (fc.exists()) {
			System.out.println("done");
			try {

				media = new Media(fc.toURI().toString());

				mediaplayer = new MediaPlayer(media);
				mediaplayer.setVolume(1);

			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			System.out.println("errroe");

		}

	}

	public abstract void changesound(String url);

	public abstract void stop();

	public abstract boolean isAutoPlay();

	public abstract void AutoPlay();

	public abstract void Play();

	public abstract boolean isMute();

	public abstract void setMute(boolean muted);

	public abstract void volume(float v);

	public static MediaPlayer getMediaplayer() {
		return mediaplayer;
	}

	public static void setMediaplayer(MediaPlayer mediaplayer) {
		Sound.mediaplayer = mediaplayer;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		Sound.media = media;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		Sound.url = url;
	}

}
