package tk.ontes.past.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import tk.ontes.past.PastGame;
import tk.ontes.past.Player;
import tk.ontes.past.area.Area;
import tk.ontes.past.world.World;

public class WorldScreen implements Screen {

    private static final int VIEW_HEIGHT = 10*16;

    private PastGame game;

    private OrthographicCamera camera;

    private World world;
    private Player player;

    public WorldScreen(World world, PastGame game) {
        this.world = world;
        this.game = game;
        this.player = game.player;
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float delta) {
        // Handle input
        handleInput(delta);

        // Update
        world.update(delta);

        // Update camera
        camera.position.set(player.x, player.y, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Clear screen
        Gdx.gl.glClearColor(153/255f, 186/255f, 255/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render
        game.batch.begin();
        world.render(game);
        game.batch.end();
    }

    private void handleInput(float delta) {
        // Enter area
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            for(Area area: world.areas) {
                if(area.x == player.x && area.y == player.y) {
                    if(area.tiles.size == 0) area.generate();
                    game.setScreen(new AreaScreen(area, game));
                }
            }
        }
        // Movement
        //TODO
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
