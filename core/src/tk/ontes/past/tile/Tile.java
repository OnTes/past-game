package tk.ontes.past.tile;

import com.badlogic.gdx.math.Rectangle;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;

public abstract class Tile extends Rectangle {
    private Area area;

    public boolean collidable = false;

    public Tile(Area area) {
        this.area = area;
    }

    public abstract void draw(PastGame game);
    public abstract void update(float delta);

    public abstract void entityCollision(Entity entity, Side side);
}
