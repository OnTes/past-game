package tk.ontes.past.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import tk.ontes.past.PastGame;
import tk.ontes.past.Side;
import tk.ontes.past.area.Area;
import tk.ontes.past.tile.Tile;

public abstract class Entity extends Rectangle{

    private static final float COLLISON_OFFSET = 0.001f;

    private Area area;

    public Entity(Area area) {
        this.area = area;
    }

    public abstract void draw(PastGame game);
    public abstract void update(float delta);

    public abstract void entityCollision(Entity entity, Side side, boolean didIt);
    public abstract void tileCollision(Tile tile, Side side);

    protected void moveX(float dist) {
        if(dist == 0) return;

        x += dist;

        if (dist > 0) checkCollisions(Side.RIGHT);
        else checkCollisions(Side.LEFT);

    }

    protected void moveY(float dist) {
        if(dist == 0) return;

        y += dist;

        if (dist > 0) checkCollisions(Side.TOP);
        else checkCollisions(Side.BOTTOM);
    }

    private void checkCollisions(Side side) {
        // Check for tile collisions
        Array<Tile> collidedTiles = new Array<Tile>();

        for(Tile tile: area.tiles) {
            if(tile.collidable && overlaps(tile)) {
                collidedTiles.add(tile);
            }
        }

        // Check for entity collisions
        Array<Entity> collidedEntities = new Array<Entity>();

        for(Entity entity: area.entities) {
            if (entity == this) continue;

            if(overlaps(entity)) {
                collidedEntities.add(entity);
            }
        }
        // Inform itself and collided tile/entity
        for(Tile tile: collidedTiles) {
            tileCollision(tile, side);
            tile.entityCollision(this, getOppositeSide(side));
        }

        for(Entity entity: collidedEntities) {
            entityCollision(entity, side, true);
            entity.entityCollision(this, getOppositeSide(side), false);
        }
    }

    protected void moveBack(Rectangle rect, Side side) {
        switch (side) {
            case RIGHT:
                if(rect.x - width < x) x = rect.x - width;
                break;
            case LEFT:
                if(rect.x + rect.width > x) x= rect.x + rect.width;
                break;
            case TOP:
                if(rect.y - height < y) y = rect.y - height;
                break;
            case BOTTOM:
                if(rect.y + rect.height > y) y = rect.y + rect.height;
                break;
        }
    }

    @Override
    public boolean overlaps(Rectangle r) {
        return x + COLLISON_OFFSET < r.x + r.width
                && x + width > r.x + COLLISON_OFFSET
                && y + COLLISON_OFFSET < r.y + r.height
                && y + height > r.y + COLLISON_OFFSET;
    }

    private Side getOppositeSide(Side side) {
        switch(side) {
            case TOP:
                return Side.BOTTOM;
            case  BOTTOM:
                return Side.TOP;
            case LEFT:
                return Side.RIGHT;
            case RIGHT:
                return Side.LEFT;
            default:
                return side;
        }
    }
}
