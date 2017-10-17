package tk.ontes.past.tile;

import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;

public class DirtGrass extends Tile {

    private int textureNum;


    public DirtGrass(int x, int y, int textureNum, Area area) {
        super(true, area);
        set(x, y, 16, 16);
        this.textureNum = textureNum;
    }

    @Override
    public void draw(PastGame game) {
        game.batch.draw(game.assets.texGrass, x-2, y-2, textureNum*20, 0, 20, 20);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void entityCollision(Entity entity, Side side) {

    }
}
