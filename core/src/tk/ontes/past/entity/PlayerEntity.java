package tk.ontes.past.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.tile.Tile;

import java.io.IOException;

public class PlayerEntity extends GravityEntity {

    private static final int SPEED = 120;
    private static final int JUMP_FORCE = 160;
    private static final float WALKING_ANIM_SPEED = 0.1f;

    private int textureNum = 0;
    private float timer = 0;
    private boolean direction; //true=right, false=left
    private boolean walking = false;

    public PlayerEntity(float x, float y, float velX, float velY, boolean direction, Area area) {
        super(x, y, 12, 18, velX, velY, ID.PLAYER_ENTITY, area);
        this.direction = direction;
    }

    public PlayerEntity(float x, float y, Area area) {
        this(x, y, 0, 0, true, area);
    }

    public PlayerEntity(XmlReader.Element xml, Area area) {
        this(xml.getFloat("x"), xml.getFloat("y"), xml.getFloat("velX"), xml.getFloat("velY"), xml.getBoolean("direction"), area);
    }

    @Override
    public void save(XmlWriter xml) {
        super.save(xml);
        try {
            xml.attribute("direction", direction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (onGround && walking) { // = walking
            timer += delta;
            if (timer > WALKING_ANIM_SPEED) {
                timer = 0;
                if (textureNum < 1 || textureNum > 3) textureNum = 1;
                else textureNum++;
            }
        }
        else if (onGround) { // = standing
            textureNum = 0;
        }
        else if (velY > 0) { // = jumping
            textureNum = 5;
        }
        else { // = falling
            textureNum = 6;
        }
    }

    public void handleInput(float delta, int upKey, int downKey, int leftKey, int rightKey) {

        // Horizontal movement
        if(Gdx.input.isKeyPressed(leftKey) && !Gdx.input.isKeyPressed(rightKey)) {
            direction = false;
            walking = true;
            moveX(-SPEED * delta);
        }
        else if(Gdx.input.isKeyPressed(rightKey) && !Gdx.input.isKeyPressed(leftKey)) {
            direction = true;
            walking = true;
            moveX(SPEED * delta);
        }
        else {
            walking = false;
        }

        //Jump
        if(Gdx.input.isKeyPressed(upKey) && onGround) {
            velY = JUMP_FORCE;
            onGround = false;
        }
    }

    @Override
    public void entityCollision(Entity entity, Side side, boolean didIt) {

    }

    @Override
    public void tileCollision(Tile tile, Side side) {
        updateOnGround(side);
        moveBack(tile, side);
    }

    @Override
    public void draw(PastGame game) {
        game.batch.draw(game.assets.get("textures/player.png", Texture.class), x-2, y, 16, 20, textureNum*16, 0, 16, 20, !direction, false);
    }
}
