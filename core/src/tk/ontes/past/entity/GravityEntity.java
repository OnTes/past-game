package tk.ontes.past.entity;

import tk.ontes.past.Side;
import tk.ontes.past.area.Area;

public abstract class GravityEntity extends PhysicsEnity{

    protected boolean onGround = true;
    protected boolean newOnGround = false;

    public GravityEntity(Area area) {
        super(area);
    }

    public void updateGravity(float delta) {
        velY -= Area.GRAVITY * delta;
        updatePhysics(delta);

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
