package tk.ontes.past.area;

import tk.ontes.past.entity.Player;
import tk.ontes.past.tile.Dirt;
import tk.ontes.past.tile.Grass;

public class FlatGrassArea extends Area {

    public FlatGrassArea(int width) {
        super();
        for(int x = 0; x < width; x++) {
            tiles.add(new Dirt(x*16, 0, this));
            tiles.add(new Dirt(x*16, 16, this));
            tiles.add(new Grass(x*16, 2*16, this));
        }
        player = new Player((width*16)/2 - 6, 3*16, this);
        entities.add(player);
    }
}
