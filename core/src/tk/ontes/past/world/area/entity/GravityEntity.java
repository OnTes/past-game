package tk.ontes.past.world.area.entity;

import tk.ontes.past.Side;

public abstract class GravityEntity extends PhysicsEntity {

    protected boolean onGround = false;
    protected boolean newOnGround = false;

    public GravityEntity(float x, float y, float width, float height, float velX, float velY, int id, tk.ontes.past.world.area.Area area) {
        super(x, y, width, height, velX, velY, id, area);
    }

    public void update(float delta) {
        velY -= tk.ontes.past.world.area.Area.GRAVITY * delta;
        super.update(delta);

        onGround = newOnGround;
        newOnGround = false;
    }

    public void setOnGround(Side side) {
        if(side == Side.BOTTOM && velY < 0) {
            newOnGround = true;
            velY = 0;
        }
        else if(side == Side.TOP) {
            velY = 0;
        }
    }
}
