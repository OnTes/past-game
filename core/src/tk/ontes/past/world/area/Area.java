package tk.ontes.past.world.area;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.world.area.entity.Entity;
import tk.ontes.past.world.area.tile.Tile;
import tk.ontes.past.world.World;

import java.io.IOException;

public abstract class Area {

    public static final float GRAVITY = 400;

    public World world;
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
            xml.attribute("xOnMap", x);
            xml.attribute("yOnMap", y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).draw(batch);
        }
        for (int i = 0; i < tiles.size; i++) {
            tiles.get(i).draw(batch);
        }
    }

    public void update(float delta) {
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).update(delta);
        }
        for (int i = 0; i < tiles.size; i++) {
            tiles.get(i).update(delta);
        }
    }

    public abstract void updateOnMap(float delta);
    public abstract void drawOnMap(SpriteBatch batch);


    public static Area find(World world, int areaX, int areaY) {
        for(Area area: world.areas) {
            if(area.x == areaX && area.y == areaY) return area;
        }
        return null;
    }

    public static Area find(PastGame game, int worldNum, int areaX, int areaY) {
        for(Area area: World.find(game, worldNum).areas) {
            if(area.x == areaX && area.y == areaY) return area;
        }
        return null;
    }

    public static Area fromXml(XmlReader.Element xml, World world) {
        switch (xml.getInt("id")) {
            case Area.ID.FLAT_GRASS:
                return new tk.ontes.past.world.area.FlatGrassArea(xml, world);
        }
        return null;
    }

    public static class ID {
        public static final int FLAT_GRASS = 0;
    }
}
