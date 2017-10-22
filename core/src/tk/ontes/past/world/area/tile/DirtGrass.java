package tk.ontes.past.world.area.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.world.area.Area;
import tk.ontes.past.world.area.entity.Entity;

import java.io.IOException;

public class DirtGrass extends Tile {

    private int textureNum;

    public DirtGrass(float x, float y, int textureNum, Area area) {
        super(x, y, 16, 16, ID.DIRT_GRASS, area);
        this.textureNum = textureNum;
    }

    public DirtGrass(XmlReader.Element xml, Area area) {
        this(xml.getFloat("xOnMap"), xml.getFloat("yOnMap"), xml.getInt("t"), area);
    }

    @Override
    public void save(XmlWriter xml) {
        super.save(xml);
        try {
            xml.attribute("t", textureNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void entityCollision(Entity entity, Side side) {
        entity.moveBack(this, Side.getOpposite(side));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(PastGame.assets.get("textures/grass.png", Texture.class), x-2, y-2, textureNum*20, 0, 20, 20);
    }
}
