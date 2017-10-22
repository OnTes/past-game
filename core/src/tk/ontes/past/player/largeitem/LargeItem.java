package tk.ontes.past.player.largeitem;

import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.player.Item;
import tk.ontes.past.player.Player;

public abstract class LargeItem extends Item {

    public LargeItem(int slot, int id, Player player) {
        super(slot, id, player);
    }

    public static LargeItem fromXml(XmlReader.Element xml, Player player) {
        switch (xml.getInt("id")) {
            case 0:
                return new Shovel(xml, player);
        }
        return null;
    }

    public static class ID {
        public static final int SHOVEL = 0;
    }
}
