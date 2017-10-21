package tk.ontes.past;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.item.BigItem;
import tk.ontes.past.item.SmallItem;
import tk.ontes.past.world.World;


import java.io.IOException;

public class Player {

    private PastGame game;

    public World world;
    public int x;
    public int y;

    public Array<BigItem> bigItems;
    public Array<SmallItem> smallItems;

    public Player(World world, int x, int y, PastGame game) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.game = game;

        bigItems = new Array<BigItem>();
        smallItems = new Array<SmallItem>();
    }

    public Player(XmlReader.Element xml, PastGame game) {
        this(game.world, xml.getInt("x"), xml.getInt("y"), game);
    }

    public void save(XmlWriter xml) {
        try {
            xml.attribute("world", world.num);
            xml.attribute("x", x);
            xml.attribute("y", y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
