package tk.ontes.past.player.largeitem;

import com.badlogic.gdx.utils.XmlReader;
import tk.ontes.past.player.Player;

public class Shovel extends LargeItem {

    public Shovel(int slot, Player player) {
        super(slot, ID.SHOVEL, player);
    }

    public Shovel(XmlReader.Element xml, Player player) {
        super(xml.getInt("slot"), ID.SHOVEL, player);
    }

    @Override
    public void draw() {

    }

    @Override
    public void use() {

    }
}
