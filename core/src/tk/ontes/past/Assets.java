package tk.ontes.past;

import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public Texture texPlayer;
    public Texture texGrass;
    public Texture texDirt;

    public Assets() {
        texPlayer = new Texture("textures/player.png");
        texGrass = new Texture("textures/grass.png");
        texDirt = new Texture("textures/dirt.png");
    }

    public void dispose() {
        texPlayer.dispose();
        texGrass.dispose();
        texDirt.dispose();
    }
}
