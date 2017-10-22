package tk.ontes.past.world;

import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.PastGame;
import tk.ontes.past.world.area.FlatGrassArea;

public class FlatGrassWorld extends World{

    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;

    public FlatGrassWorld(int num, PastGame game) {
        super(num, WIDTH, HEIGHT, ID.FLAT_GRASS, game);
    }

    public FlatGrassWorld(XmlReader.Element xml, PastGame game) {
        this(xml.getInt("num"), game);
    }

    @Override
    public void generate() {
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                areas.add(new FlatGrassArea(x, y, this));
            }
        }
    }
}
