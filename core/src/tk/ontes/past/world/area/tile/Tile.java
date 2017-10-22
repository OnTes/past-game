package tk.ontes.past.world.area.tile;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.Side;
import tk.ontes.past.world.area.Area;
import tk.ontes.past.world.area.GameObject;
import tk.ontes.past.world.area.entity.Entity;

import java.io.IOException;

public abstract class Tile extends GameObject {

    public Tile(float x, float y, float width, float height, int id, Area area) {
        super(x, y, width, height, id, area);
    }

    public abstract void entityCollision(Entity entity, Side side);

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
