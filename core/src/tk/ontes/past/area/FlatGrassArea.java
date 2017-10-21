package tk.ontes.past.area;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.PastGame;
import tk.ontes.past.entity.PlayerEntity;
import tk.ontes.past.tile.Dirt;
import tk.ontes.past.tile.DirtGrass;
import tk.ontes.past.world.World;

public class FlatGrassArea extends Area {

    private static final int WIDTH = 32;

    public FlatGrassArea(int x, int y, World world) {
        super(x, y, ID.FLAT_GRASS, world);
    }

    public FlatGrassArea(XmlReader.Element xml, World world) {
        this(xml.getInt("x"), xml.getInt("y"), world);
    }

    @Override
    public void generate() {
        for(int i = 0; i < WIDTH; i++) {
            tiles.add(new Dirt(i*16, 0, MathUtils.random(2), this));
            tiles.add(new Dirt(i*16, 16, MathUtils.random(2), this));
            tiles.add(new DirtGrass(i*16, 2*16, MathUtils.random(2), this));
        }
        entities.add(new PlayerEntity((WIDTH*16)/2 - 6, 3*16, 0, 0, true, this));
    }

    @Override
    public void draw(PastGame game) {
        //game.batch.draw(game.assets.texFlatGrass, x-2, y-2);
    }
}
