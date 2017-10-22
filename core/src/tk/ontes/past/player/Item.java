package tk.ontes.past.player;

import com.badlogic.gdx.utils.XmlWriter;

import java.io.IOException;

public abstract class Item {

    protected Player player;
    public int id;
    public int slot;

    public Item(int slot, int id, Player player) {
        this.player = player;
        this.id = id;
        this.slot = slot;
    }

    public void save(XmlWriter xml) {
        try {
            xml.attribute("id", id);
            xml.attribute("slot", slot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void draw();
    public abstract void use();
}
