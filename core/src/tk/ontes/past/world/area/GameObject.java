package tk.ontes.past.world.area;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlWriter;

import java.io.IOException;

public abstract class GameObject extends Rectangle {

    private static final float COLLISION_OFFSET = 0.001f;

    public Area area;
    public int id;

    public GameObject(float x, float y, float width, float height, int id, Area area) {
        super(x, y, width, height);
        this.id = id;
        this.area = area;
    }

    public void save(XmlWriter xml) {
        try {
            xml.attribute("id", id);
            xml.attribute("xOnMap", x);
            xml.attribute("yOnMap", y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch);

    @Override
    public boolean overlaps(Rectangle r) {
        return x + COLLISION_OFFSET < r.x + r.width
                && x + width > r.x + COLLISION_OFFSET
                && y + COLLISION_OFFSET < r.y + r.height
                && y + height > r.y + COLLISION_OFFSET;
    }
}
