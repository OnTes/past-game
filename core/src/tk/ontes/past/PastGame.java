package tk.ontes.past;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.world.area.Area;
import tk.ontes.past.world.area.entity.Entity;
import tk.ontes.past.player.Player;
import tk.ontes.past.world.WorldScreen;
import tk.ontes.past.world.area.tile.Tile;
import tk.ontes.past.world.FlatGrassWorld;
import tk.ontes.past.world.World;

import java.io.IOException;

public class PastGame extends Game {

    private static final float VERSION = 0.1f;
    private static final String SAVE_FILE = "save.pgs";

    public static AssetManager assets = new AssetManager();
    public SpriteBatch batch;

    public Array<World> worlds;
	public Player player;

    @Override
	public void create () {
        batch = new SpriteBatch();
        loadAssets();

        worlds = new Array<World>();

        generate();
	}

	private void generate() {
        World world = new FlatGrassWorld(0, this);
        world.generate();
        worlds.add(world);
        player = Player.fromValues(0, 0, 0, this);
        setScreen(new WorldScreen(world, this));
    }

    private void load(String patch) {
	    System.out.println("Loading...");

        XmlReader.Element xml = null;
        try {
            xml = new XmlReader().parse(Gdx.files.local(patch));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(XmlReader.Element xmlWorld: xml.getChildrenByName("world")) {
            World world = World.fromXml(xmlWorld, this);
            worlds.add(world);

            for(XmlReader.Element xmlArea: xmlWorld.getChildrenByName("area")) {

                Area area = Area.fromXml(xmlArea, world);
                world.areas.add(area);

                for(XmlReader.Element xmlTile: xmlArea.getChildrenByName("tile")) {
                    area.tiles.add(Tile.fromXml(xmlTile, area));
                }
                for(XmlReader.Element xmlEntity: xmlArea.getChildrenByName("entity")) {
                    area.entities.add(Entity.fromXml(xmlEntity, area));
                }
            }
        }

        XmlReader.Element xmlPlayer = xml.getChildByName("player");
        player = Player.fromXml(xmlPlayer, this);

        System.out.println("Done!");
    }

    private void save(String patch) {
	    System.out.println("Saving...");

        XmlWriter xmlWriter = new XmlWriter(Gdx.files.local(patch).writer(false));
        try {
            XmlWriter xml = xmlWriter.element("PastGameSave");
            xml.attribute("version", VERSION);

            for(World world: worlds) {
                XmlWriter xmlWorld = xml.element("world");
                world.save(xmlWorld);
                for(Area area: world.areas) {

                    XmlWriter xmlArea = xmlWorld.element("area");
                    area.save(xmlArea);

                    for(Tile tile: area.tiles) {
                        XmlWriter xmlTile = xmlArea.element("tile");
                        tile.save(xmlTile);
                        xmlTile.pop();
                    }
                    for(Entity entity: area.entities) {
                        if(entity.id == Entity.ID.PLAYER) continue;
                        XmlWriter xmlEntity = xmlArea.element("entity");
                        entity.save(xmlEntity);
                        xmlEntity.pop();
                    }
                    xmlArea.pop();
                }
                xmlWorld.pop();
            }

            XmlWriter xmlPlayer = xml.element("player");
            player.save(xmlPlayer);
            xmlPlayer.pop();

            xml.pop();
            xmlWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }

    private void loadAssets() {
        System.out.println("Loading assets...");

        assets.load("textures/grass.png", Texture.class);
        assets.load("textures/dirt.png", Texture.class);
        assets.load("textures/player.png", Texture.class);
        assets.load("textures/invslot.png", Texture.class);
        assets.load("textures/shovel.png", Texture.class);
        assets.load("fonts/pixel.fnt", BitmapFont.class);
        assets.finishLoading();

        System.out.println("Loading done!");
    }

    @Override
    public void render() {
	    if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
	        save(SAVE_FILE);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
	        reset();
	        load(SAVE_FILE);
	        setScreen(new WorldScreen(worlds.get(0), this)); //TODO
        }
        super.render();
    }

    public void reset() {
        worlds.clear();
        player = null;
    }

    @Override
	public void dispose () {
		assets.dispose();
	}
}
