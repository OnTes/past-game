package tk.ontes.past.area;

import com.badlogic.gdx.utils.Array;
import tk.ontes.past.PastGame;
import tk.ontes.past.entity.Entity;
import tk.ontes.past.entity.Player;
import tk.ontes.past.tile.Tile;

public abstract class Area {

    public static final float GRAVITY = 400;

    public Array<Tile> tiles;
    public Array<Entity> entities;

    public Player player;

    public Area() {
        tiles = new Array<Tile>();
        entities = new Array<Entity>();
    }

    public void draw(PastGame game) {

        for (int i = 0; i < entities.size; i++) {
            entities.get(i).draw(game);
        }
        for (int i = 0; i < tiles.size; i++) {
            tiles.get(i).draw(game);
        }
    }

    public void update(float delta) {
        for (int i = 0; i < tiles.size; i++) {
            tiles.get(i).update(delta);
        }
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).update(delta);
        }
    }
}
