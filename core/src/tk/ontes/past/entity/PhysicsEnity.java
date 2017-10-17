package tk.ontes.past.entity;

import tk.ontes.past.area.Area;

public abstract class PhysicsEnity extends Entity {

    public float velX = 0;
    public float velY = 0;

    public PhysicsEnity(Area area) {
        super(area);
    }

    public void updatePhysics(float delta) {
        moveX(velX * delta);
        moveY(velY * delta);
    }
}
