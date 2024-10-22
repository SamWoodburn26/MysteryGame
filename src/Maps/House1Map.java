package Maps;

import EnhancedMapTiles.PushableRock;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.*;
//import NPCs.Bug;
import NPCs.Butcher;
//import NPCs.Dinosaur;
import NPCs.ExGf;
import NPCs.MHDaughter;
import NPCs.Mom;
//import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.House1Map.*;
import Tilesets.CommonTileset;
import Utils.Point;

import java.util.ArrayList;

import Engine.ImageLoader;

// Represents a test map to be used in a level
public class House1Map extends Map {

    public House1Map() {
        super("house1_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(17, 19).getLocation();
        System.out.println("Player starting at: " + playerStartPosition.x + ", " + playerStartPosition.y);
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Mom mom = new Mom(1, getMapTile(13, 18).getLocation().subtractY(40));

        mom.setInteractScript(new MomScript());
        npcs.add(mom);

        ExGf ex = new ExGf(1, getMapTile(25, 17).getLocation().subtractY(40));
        ex.setInteractScript(new BrotherExGFScript());
        npcs.add(ex);

        MHDaughter daughter = new MHDaughter(1, getMapTile(29, 16).getLocation().subtractY(40));
        daughter.setInteractScript(new DrugDealerScript());
        npcs.add(daughter);

        Butcher butcher = new Butcher(3, getMapTile(23, 18).getLocation().subtractX(20));
        butcher.setInteractScript(new ButcherScript());
        npcs.add(butcher);

<<<<<<< HEAD
        // Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        // walrus.setInteractScript(new WalrusScript());
        // npcs.add(walrus);

        // Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        // dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        // dinosaur.setInteractScript(new DinoScript());
        // npcs.add(dinosaur);

        // Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        // bug.setInteractScript(new BugScript());
        // npcs.add(bug);

=======
>>>>>>> 3bb74c653a8b697aecb80d53db5330e58766752f
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // trigger to enter town
        Point townLoc = getPositionByTileIndex(17, 25);
        System.out.println("Trigger for transition set at: " + townLoc.x + ", " + townLoc.y);
        triggers.add(new Trigger(townLoc.x - 50, townLoc.y + 50, 200, 10, new House1ToTownScript(), "house1ToTown"));
        // Point townLoc = getPositionByTileIndex(18,24);
        System.out.println("Trigger for transition set at: " + townLoc.x + ", " + townLoc.y);
<<<<<<< HEAD
        // Point tileLocation1 = getMapTile(6, 14).getLocation();
        /*
         * triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(),
         * "hasLostBall"));
         * triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(),
         * "hasLostBall"));
         * triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(),
         * "hasLostBall"));
         */
        // triggers.add(new Trigger(tileLocation1.x, tileLocation1.y, 100,20, new
        // ExitScript(), "exitInteract" ));

        triggers.add(new Trigger(townLoc.x, townLoc.y, 200, 10, new House1ToTownScript(), "house1ToTown"));
=======
        
        
        triggers.add(new Trigger(townLoc.x, townLoc.y,200,10, new House1ToTownScript(), "house1ToTown" ));
>>>>>>> 3bb74c653a8b697aecb80d53db5330e58766752f
        System.out.println("house1totownscript triggered");

        System.out.println("Trigger set at: " + townLoc.x + ", " + townLoc.y);

        // pop up image trigger
        Point photoLoc = getPositionByTileIndex(24, 15);

        triggers.add(new Trigger(photoLoc.x, photoLoc.y, 100, 100, new PopUpButcherImageScript(), "popUpButcherImage"));
        System.out.println("Trigger set at: " + photoLoc.x + ", " + photoLoc.y);

        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(22, 18).setInteractScript(new SimpleTextScript("Mom's house"));

        getMapTile(2, 6).setInteractScript(new TreeScript());
    }
}