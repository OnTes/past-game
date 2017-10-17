package tk.ontes.past.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.tile.Tile;

public class Player extends GravityEntity {

    private static final int SPEED = 120;
    private static final int JUMP_FORCE = 160;

    private Action action = Action.STANDING;
    private int textureNum = 0;
    private float timer = 0;
    private boolean direction = true; //true=right, false=left

    public Player(int x, int y, Area area) {
        super(area);

        set(x, y, 12, 18);
    }

    @Override
    public void draw(PastGame game) {
        game.batch.draw(game.assets.texPlayer, x-2, y, 16, 20, textureNum*16, 0, 16, 20, !direction, false);
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        updateGravity(delta);

        timer += delta;

        switch (action) {
            case WALKING: {
                if (timer > 0.1) {
                    if (textureNum < 1 || textureNum > 3) setTextureNum(1);
                    else setTextureNum(textureNum+1);
                }
                break;
            }
            case STANDING: {
                setTextureNum(0);
                break;
            }
            case JUMPING: {
                setTextureNum(5);
                break;
            }
            case FALLING: {
                setTextureNum(6);
                break;
            }
        }
    }

    private void handleInput(float delta) {

        // Horizontal movement
        if(Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = true;
            moveX(SPEED * delta);
            if(onGround) action = Action.WALKING;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = false;
            moveX(-SPEED * delta);
            if(onGround) action = Action.WALKING;
        }
        else if(onGround) {
            action = Action.STANDING;
        }

        //Jump
        if(Gdx.input.isKeyPressed(Input.Keys.W) && onGround) {
            velY = JUMP_FORCE;
            onGround = false;
            action = Action.JUMPING;
        }

        if(!onGround && velY < 0) action =  Action.FALLING;
    }

    @Override
    public void entityCollision(Entity entity, Side side, boolean didIt) {

    }

    @Override
    public void tileCollision(Tile tile, Side side) {
        updateOnGround(side);
        moveBack(tile, side);
    }

    private void setTextureNum(int number) {
        textureNum = number;
        timer = 0;
    }

    private enum Action {
        STANDING, WALKING, JUMPING, FALLING
    }
}
