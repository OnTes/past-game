package tk.ontes.past.world.area.entity;

import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.world.area.Area;

import java.io.IOException;

public abstract class Humanoid extends GravityEntity {

    private static final int SPEED = 120;
    private static final int JUMP_FORCE = 160;
    private static final float WALKING_ANIM_SPEED = 0.1f;

    protected boolean direction; //true=right, false=left
    private boolean walking = false;
    private boolean newWalking = false;

    protected int textureNum = 0;
    private float timer = 0;

    public Humanoid(float x, float y, float velX, float velY, boolean direction, int id, Area area) {
        super(x, y, 12, 18, velX, velY, id, area);
        this.direction = direction;
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

        walking = newWalking;
        newWalking = false;

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

    protected void walkLeft(float delta) {
        direction = false;
        newWalking = true;
        moveX(-SPEED * delta);
    }

    protected void walkRight(float delta) {
        direction = true;
        newWalking = true;
        moveX(SPEED * delta);
    }

    protected void jump() {
        if(onGround) {
            velY = JUMP_FORCE;
            onGround = false;
        }
    }
}
