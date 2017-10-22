package tk.ontes.past.world.area.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.Side;
import tk.ontes.past.world.area.Area;
import tk.ontes.past.world.area.GameObject;
import tk.ontes.past.world.area.tile.Tile;

public abstract class Entity extends GameObject{

    public Entity(float x, float y, float width, float height, int id, Area area) {
        super(x, y, width, height, id, area);
    }

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
            if(overlaps(tile)) {
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
            tile.entityCollision(this, Side.getOpposite(side));
        }

        for(Entity entity: collidedEntities) {
            entityCollision(entity, side, true);
            entity.entityCollision(this, Side.getOpposite(side), false);
        }
    }

    public void moveBack(Rectangle rect, Side side) {
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

    public static Entity fromXml(XmlReader.Element xml, Area area){
        switch (xml.getInt("id")) {

        }
        return null;
    }

    public static class ID {
        public static final int PLAYER = 0;
    }
}
