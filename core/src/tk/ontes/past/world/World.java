package tk.ontes.past.world;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.area.Area;

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

    }

    public void render(PastGame game) {
        /*for(int i = 0; i < areas.size; i++) {
            areas.get(i).draw(game);
        }*/
    }

    public static World fromXml(XmlReader.Element xml, PastGame game) {
        System.out.println(xml.getInt("id"));
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
