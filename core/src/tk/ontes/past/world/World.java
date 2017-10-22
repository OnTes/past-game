package tk.ontes.past.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.world.area.Area;

import java.io.IOException;

public abstract class World {

    private PastGame game;
    public int id;

    public int num;
    public int width;
    public int height;

    public Array<Area> areas;

    public World(int num, int width, int height, int id, PastGame game) {
        this.num = num;
        this.width = width;
        this.height = height;
        this.id = id;
        this.game = game;

        areas = new Array<Area>();
    }

    public abstract void generate();

    public void save(XmlWriter xml) {
        try {
            xml.attribute("id", id);
            xml.attribute("num", num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(float delta) {
        game.player.updateOnMap(delta);
        for(int i = 0; i < areas.size; i++) {
            areas.get(i).updateOnMap(delta);
        }
    }

    public void render(SpriteBatch batch) {
        game.player.drawOnMap(batch);
        for(int i = 0; i < areas.size; i++) {
            areas.get(i).drawOnMap(batch);
        }
    }


    public static World find(PastGame game, int worldNum) {
        for(World world: game.worlds) {
            if(world.num == worldNum) return world;
        }
        return null;
    }

    public static World fromXml(XmlReader.Element xml, PastGame game) {
        switch (xml.getInt("id")) {
            case ID.FLAT_GRASS:
                return new FlatGrassWorld(xml, game);
        }
        return null;
    }

    public static class ID {
        public static final int FLAT_GRASS = 0;
    }
}
