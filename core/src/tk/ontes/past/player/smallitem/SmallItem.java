package tk.ontes.past.player.smallitem;

import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.player.Item;
import tk.ontes.past.player.Player;

public abstract class SmallItem extends Item {

    public SmallItem(int slot, int id, Player player) {
        super(slot, id, player);
    }

    public static SmallItem fromXml(XmlReader.Element xml, Player player) {
        switch (xml.getInt("id")) {

        }
        return null;
    }

    public static class ID {

    }
}
