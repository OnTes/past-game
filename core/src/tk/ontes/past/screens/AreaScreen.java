package tk.ontes.past.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import tk.ontes.past.PastGame;
import tk.ontes.past.area.Area;
import tk.ontes.past.area.FlatGrassArea;

public class AreaScreen implements Screen {

    private static final int VIEWPORT_HEIGHT = 10*16;

    private PastGame game;
    private OrthographicCamera cam;
    private Area area;

    public AreaScreen(PastGame game) {
        this.game = game;
        cam = new OrthographicCamera(640, 320);
        area = new FlatGrassArea(16);
    }

    @Override
    public void render(float delta) {
        // Update
        area.update(delta);

        // Update camera
        cam.position.set(area.player.x, area.player.y, 0);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        // Clear screen
        Gdx.gl.glClearColor(153/255f, 186/255f, 255/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render
        game.batch.begin();
        area.draw(game);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = VIEWPORT_HEIGHT;
        cam.viewportWidth = VIEWPORT_HEIGHT* width / height;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
