package tk.ontes.past.entity;

import tk.ontes.past.Side;
import tk.ontes.past.area.Area;

public abstract class GravityEntity extends PhysicsEntity {

    protected boolean onGround = false;
    protected boolean newOnGround = false;

    public GravityEntity(float x, float y, float width, float height, float velX, float velY, int id, Area area) {
        super(x, y, width, height, velX, velY, id, area);
    }

    public void update(float delta) {
        velY -= Area.GRAVITY * delta;
        super.update(delta);

        onGround = newOnGround;
        newOnGround = false;
    }

    public void updateOnGround(Side side) {
        if(side == Side.BOTTOM && velY < 0) {
            newOnGround = true;
            velY = 0;
        }
        else if(side == Side.TOP) {
            velY = 0;
        }
    }
}
