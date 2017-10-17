package tk.ontes.past.tile;

import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;

import java.util.Random;

public class Grass extends Tile {

    private int textureNum;


    public Grass( int x, int y, Area area) {
        super(area);
        set(x, y, 16, 16);
        collidable = true;
        textureNum = new Random().nextInt(3);
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
