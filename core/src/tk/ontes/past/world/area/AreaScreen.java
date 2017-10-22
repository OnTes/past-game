package tk.ontes.past.world.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import tk.ontes.past.PastGame;

public class AreaScreen implements Screen {

    private static final int VIEW_HEIGHT = 10*16;

    private PastGame game;
    private Area area;

    private OrthographicCamera camera;
    private Matrix4 hudMatrix;

    public AreaScreen(Area area, PastGame game) {
        this.area = area;
        this.game = game;
        camera = new OrthographicCamera();
        hudMatrix = new Matrix4();
    }

    @Override
    public void render(float delta) {
        // Update
        area.update(delta);

        // Update camera
        camera.position.set(game.player.x + game.player.width/2, game.player.y + game.player.height/2, 0);
        camera.update();

        // Clear screen
        Gdx.gl.glClearColor(153/255f, 186/255f, 255/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render game
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        area.render(game.batch);
        game.batch.end();

        // Render hud
        game.batch.setProjectionMatrix(hudMatrix);
        game.batch.begin();
        game.player.inventory.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = VIEW_HEIGHT * width / height;
        camera.viewportHeight = VIEW_HEIGHT;
        camera.update();

        hudMatrix.setToOrtho2D(0, 0, width, height);
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
