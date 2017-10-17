package tk.ontes.past;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tk.ontes.past.screens.AreaScreen;

public class PastGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public Assets assets;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
        assets = new Assets();

		setScreen(new AreaScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		assets.dispose();
	}
}
