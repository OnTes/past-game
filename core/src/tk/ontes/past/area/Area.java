package tk.ontes.past.area;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.entity.Entity;
import tk.ontes.past.tile.Tile;
import tk.ontes.past.world.World;

import java.io.IOException;

public abstract class Area {

    public static final float GRAVITY = 400;

    private World world;
    public int id;

    public int x;
    public int y;

    public Array<Tile> tiles;
    public Array<Entity> entities;

    public Area(int x, int y, int id, World world) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.world = world;

        tiles = new Array<Tile>();
        entities = new Array<Entity>();
    }

    public abstract void generate();

    public void save(XmlWriter xml) {
        try {
            xml.attribute("id", id);
            xml.attribute("x", x);
            xml.attribute("y", y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void draw(PastGame game);

    public void render(PastGame game) {
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).draw(game);
        }
        for (int i = 0; i < tiles.size; i++) {
            tiles.get(i).draw(game);
        }
    }

    public void update(float delta) {
        for (int i = 0; i < tiles.size; i++) {
            tiles.get(i).update(delta);
        }
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).update(delta);
        }
    }

    public static Area fromXml(XmlReader.Element xml, World world) {
        switch (xml.getInt("id")) {
            case Area.ID.FLAT_GRASS:
                return new FlatGrassArea(xml, world);
        }
        return null;
    }

    public static class ID {
        public static final int FLAT_GRASS = 0;
    }
}
