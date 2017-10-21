package tk.ontes.past.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import tk.ontes.past.PastGame;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;
import tk.ontes.past.entity.PlayerEntity;

public class AreaScreen implements Screen {

    private static final int VIEW_HEIGHT = 10*16;

    private PastGame game;

    private OrthographicCamera camera;

    private Area area;
    private PlayerEntity playerEntity;

    public AreaScreen(Area area, PastGame game) {
        this.area = area;
        this.game = game;
        camera = new OrthographicCamera();

        playerEntity = null;
        for(Entity entity: area.entities) {
            if(entity.id == Entity.ID.PLAYER_ENTITY) {
                playerEntity = (PlayerEntity) entity;
                break;
            }
        }
    }

    @Override
    public void render(float delta) {
        // Handle input
        handleInput(delta);

        // Update
        area.update(delta);

        // Update camera
        camera.position.set(playerEntity.x + playerEntity.width/2, playerEntity.y + playerEntity.height/2, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Clear screen
        Gdx.gl.glClearColor(153/255f, 186/255f, 255/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render
        game.batch.begin();
        area.render(game);
        game.batch.end();
    }

    private void handleInput(float delta) {
        playerEntity.handleInput(delta, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D);
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
