package tk.ontes.past.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import tk.ontes.past.PastGame;

public class MenuScreen implements Screen {

    private static final int VIEW_HEIGHT = 10*16;

    private PastGame game;
    private OrthographicCamera camera;

    public MenuScreen(PastGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
    }

    @Override
    public void render(float delta) {
        // Update camera
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Clear screen
        Gdx.gl.glClearColor(204/255f, 250/255f, 240/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render
        game.batch.begin();
        //TODO
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = VIEW_HEIGHT;
        camera.viewportWidth = VIEW_HEIGHT* width / height;
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
