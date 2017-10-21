package tk.ontes.past;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import tk.ontes.past.area.Area;
import tk.ontes.past.entity.Entity;
import tk.ontes.past.screen.WorldScreen;
import tk.ontes.past.tile.Tile;
import tk.ontes.past.world.FlatGrassWorld;
import tk.ontes.past.world.World;

import java.io.IOException;

public class PastGame extends Game {

    private static final float VERSION = 0.1f;
    private static final String SAVE_FILE = "save.pgs";

	public SpriteBatch batch;
	public AssetManager assets;

	public World world;
	public Player player;

    @Override
	public void create () {
        batch = new SpriteBatch();
        assets = new AssetManager();
        loadAssets();

        generate();
	}

	private void generate() {
        world = new FlatGrassWorld(0, this);
        world.generate();
        player = new Player(world, 0, 0, this);
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

        XmlReader.Element xmlWorld = xml.getChildByName("world");

        world = World.fromXml(xmlWorld, this);

        if (world == null) System.out.println("World == null");

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

        XmlReader.Element xmlPlayer = xml.getChildByName("player");
        player = new Player(xmlPlayer, this);

        System.out.println("Done!");
    }

    private void save(String patch) {
	    System.out.println("Saving...");

        XmlWriter xmlWriter = new XmlWriter(Gdx.files.local(patch).writer(false));
        try {
            XmlWriter xml = xmlWriter.element("PastGameSave");
            xml.attribute("version", VERSION);

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
                    XmlWriter xmlEntity = xmlArea.element("entity");
                    entity.save(xmlEntity);
                    xmlEntity.pop();
                }
                xmlArea.pop();
            }
            xmlWorld.pop();
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
        assets.load("textures/grass.png", Texture.class);
        assets.load("textures/dirt.png", Texture.class);
        assets.load("textures/player.png", Texture.class);
        assets.finishLoading();
    }

    @Override
    public void render() {
	    if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
	        save(SAVE_FILE);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
	        world = null;
	        load(SAVE_FILE);
	        setScreen(new WorldScreen(world, this));
        }
        super.render();
    }

    @Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
	}
}
