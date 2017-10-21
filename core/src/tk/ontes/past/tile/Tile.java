package tk.ontes.past.tile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;

import java.io.IOException;

public abstract class Tile extends Rectangle {

    private Area area;
    public int id;

    public boolean canCollide;

    public Tile(float x, float y, float width, float height, boolean canCollide, int id, Area area) {
        super(x, y, width, height);
        this.canCollide = canCollide;
        this.id = id;
        this.area = area;
    }

    public void save(XmlWriter xml) {
        try {
            xml.attribute("id", id);
            xml.attribute("x", x);
            xml.attribute("y", y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void update(float delta);
    public abstract void entityCollision(Entity entity, Side side);
    public abstract void draw(PastGame game);

    public static Tile fromXml(XmlReader.Element xml, Area area) {
        switch (xml.getInt("id")) {
            case Tile.ID.DIRT:
                return new Dirt(xml, area);
            case Tile.ID.DIRT_GRASS:
                return new DirtGrass(xml, area);
        }
        return null;
    }

    public static class ID {
        public static final int DIRT = 0;
        public static final int DIRT_GRASS = 1;
    }
}
