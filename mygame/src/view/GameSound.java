package view;

public class GameSound extends Sound {

	public GameSound(String url) {
		super(url);

	}

	@Override
	public void stop() {
		super.getMediaplayer().stop();

	}

	@Override
	public boolean isAutoPlay() {
		return super.getMediaplayer().isAutoPlay();
	}

	@Override
	public boolean isMute() {
		return super.getMediaplayer().isMute();
	}

	@Override
	public void volume(float f) {
		super.getMediaplayer().setVolume(f);
	}

	@Override
	public void AutoPlay() {
		super.getMediaplayer().autoPlayProperty();

	}

	@Override
	public void Play() {
		super.getMediaplayer().play();

	}

	@Override
	public void changesound(String url) {
		super.getMediaplayer().stop();
		super.setUrl(url);
		createsound();
		Play();

	}

	@Override
	public void setMute(boolean muted) {
	if (muted)
	{
	super.getMediaplayer().setVolume(0);
	}
	else {
		super.getMediaplayer().setVolume(100);
	}
		
	}

}