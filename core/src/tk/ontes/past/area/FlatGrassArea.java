package tk.ontes.past.area;

import tk.ontes.past.entity.Player;
import tk.ontes.past.tile.Dirt;
import tk.ontes.past.tile.DirtGrass;

import java.util.Random;

public class FlatGrassArea extends Area {

    public FlatGrassArea(int width) {
        super();
        Random random = new Random();
        for(int x = 0; x < width; x++) {
            tiles.add(new Dirt(x*16, 0, random.nextInt(3), this));
            tiles.add(new Dirt(x*16, 16, random.nextInt(3), this));
            tiles.add(new DirtGrass(x*16, 2*16, random.nextInt(3), this));
        }
        player = new Player((width*16)/2 - 6, 3*16, this);
        entities.add(player);
    }
}
