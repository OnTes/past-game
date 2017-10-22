package tk.ontes.past.world.area;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.world.area.tile.Dirt;
import tk.ontes.past.world.area.tile.DirtGrass;
import tk.ontes.past.world.World;

public class FlatGrassArea extends Area {

    private static final int WIDTH = 32;

    public FlatGrassArea(int x, int y, World world) {
        super(x, y, ID.FLAT_GRASS, world);
    }

    public FlatGrassArea(XmlReader.Element xml, World world) {
        this(xml.getInt("xOnMap"), xml.getInt("yOnMap"), world);
    }

    @Override
    public void generate() {
        for(int i = 0; i < WIDTH; i++) {
            tiles.add(new Dirt(i*16, 0, MathUtils.random(2), this));
            tiles.add(new Dirt(i*16, 16, MathUtils.random(2), this));
            tiles.add(new DirtGrass(i*16, 2*16, MathUtils.random(2), this));
        }
    }

    @Override
    public void updateOnMap(float delta) {

    }

    @Override
    public void drawOnMap(SpriteBatch batch) {
        //game.batch.draw(game.assets.texFlatGrass, xOnMap-2, yOnMap-2);
    }
}
