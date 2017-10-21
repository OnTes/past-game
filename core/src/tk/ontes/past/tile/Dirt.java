package tk.ontes.past.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;

import java.io.IOException;

public class Dirt extends Tile {

    private int textureNum;

    public Dirt(float x, float y, int textureNum, Area area) {
        super(x, y, 16, 16, true, ID.DIRT, area);
        this.textureNum = textureNum;
    }

    public Dirt(XmlReader.Element xml, Area area) {
        this(xml.getFloat("x"), xml.getFloat("y"), xml.getInt("t"), area);
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

    }

    @Override
    public void draw(PastGame game) {
        game.batch.draw(game.assets.get("textures/dirt.png", Texture.class), x - 2, y - 2, textureNum * 20, 0, 20, 20);
    }
}
