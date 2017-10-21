package tk.ontes.past.entity;

import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.area.Area;

import java.io.IOException;

public abstract class PhysicsEntity extends Entity {

    public float velX;
    public float velY;

    public PhysicsEntity(float x, float y, float width, float height, float velX, float velY, int id, Area area) {
        super(x, y, width, height, id, area);
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void save(XmlWriter xml) {
        super.save(xml);
        try {
            xml.attribute("velX", velX);
            xml.attribute("velY", velY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(float delta) {
        moveX(velX * delta);
        moveY(velY * delta);
    }
}
