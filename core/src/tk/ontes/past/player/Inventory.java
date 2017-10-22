package tk.ontes.past.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import tk.ontes.past.PastGame;
import tk.ontes.past.player.largeitem.LargeItem;
import tk.ontes.past.player.smallitem.SmallItem;

public class Inventory {

    private static final int MAX_SLOT_SIZE = 4*16;

    private static final int LARGEITEMS_COUNT = 3;
    private static final int SMALLITEMS_COUNT = 10;

    public Array<LargeItem> largeItems;
    public Array<SmallItem> smallItems;

    private int selectedSlot = 0;

    public Inventory() {
        largeItems = new Array<LargeItem>();
        smallItems = new Array<SmallItem>();
    }

    public void render(SpriteBatch batch) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float size =  width/(LARGEITEMS_COUNT+SMALLITEMS_COUNT+3);
        if(size > MAX_SLOT_SIZE) size = MAX_SLOT_SIZE;
        float space = (width - size*(LARGEITEMS_COUNT+SMALLITEMS_COUNT+1)) / 2;

        for(int i = 0; i < LARGEITEMS_COUNT; i++) {
            batch.draw(PastGame.assets.get("textures/invslot.png", Texture.class), size*i + space, height-size, size, size, 0, 0, 20, 20, false, false);
        }
        for(int i = 0; i < SMALLITEMS_COUNT; i++) {
            batch.draw(PastGame.assets.get("textures/invslot.png", Texture.class), size*(i+LARGEITEMS_COUNT+1) + space, height-size, size, size, 0, 0, 20, 20, false, false);
        }
    }
}
