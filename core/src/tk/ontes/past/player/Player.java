package tk.ontes.past.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.world.World;
import tk.ontes.past.world.area.Area;
import tk.ontes.past.world.area.AreaScreen;
import tk.ontes.past.world.area.entity.Entity;
import tk.ontes.past.world.area.entity.Humanoid;
import tk.ontes.past.world.area.tile.Tile;


import java.io.IOException;

public class Player extends Humanoid{

    private PastGame game;

    public Inventory inventory;

    public Player(float x, float y, float velX, float velY, boolean direction, Area area, PastGame game) {
        super(x, y, velX, velY, direction, ID.PLAYER, area);
        this.game = game;

        inventory = new Inventory();
    }

    public Player(XmlReader.Element xml, Area area, PastGame game) {
        this(xml.getFloat("x"), xml.getFloat("y"), xml.getFloat("velX"), xml.getFloat("velY"), xml.getBoolean("direction"), area, game);
    }

    public void save(XmlWriter xml) {
        super.save(xml);
        try {
            xml.attribute("worldNum", area.world.num);
            xml.attribute("areaX", area.x);
            xml.attribute("areaY", area.y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        super.update(delta);
    }

    private void handleInput(float delta) {
        // Horizontal movement
        if(Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) walkLeft(delta);
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) walkRight(delta);

        //Jump
        if(Gdx.input.isKeyPressed(Input.Keys.W)) jump();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(PastGame.assets.get("textures/player.png", Texture.class), x-2, y, 16, 20, textureNum*16, 0, 16, 20, !direction, false);
    }

    @Override
    public void entityCollision(Entity entity, Side side, boolean didIt) {

    }

    @Override
    public void tileCollision(Tile tile, Side side) {
        setOnGround(side);
    }

    public void updateOnMap(float delta) {
        handleInputOnMap(delta);
    }

    private void handleInputOnMap(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if(area.tiles.size == 0) area.generate();
            reset();
            area.entities.add(this);
            game.setScreen(new AreaScreen(area, game));
        }
    }

    public void drawOnMap(SpriteBatch batch) {

    }

    public void reset() {
        x = 0;
        y = 0;
        velX = 0;
        velY = 0;
        direction = true;
    }

    public static Player fromXml(XmlReader.Element xml, PastGame game) {
        return fromValues(xml.getInt("worldNum"), xml.getInt("areaX"), xml.getInt("areaY"), game);
    }

    public static Player fromValues(int worldNum, int areaX, int areaY, PastGame game) {
        return new Player(0, 0, 0, 0, true, Area.find(game, worldNum, areaX, areaY), game);
    }
}
