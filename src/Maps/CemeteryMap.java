/*
 * SER225- Mystery Game
 * the dawgs- Adelina Chocho, Ella Berry, Morgan Montz, Sam Woodburn, Tuana Turhan
 * Fall 2024
 * 
 * package- Maps
 * class- CemeteryMap: displays the outdoor cemetery map
 */

package Maps;

import java.util.ArrayList;

import Level.*;
import NPCs.BodyBone;
import NPCs.BodyBoneThree;
import NPCs.BodyBoneTwo;
import NPCs.BottomLegs;
import NPCs.Brother;
import NPCs.Crow;
import NPCs.RandBone;
import NPCs.Skull;
import NPCs.SkullOne;
import NPCs.SkullThree;
import NPCs.SkullTwo;
import Scripts.CemeteryMap.CemeteryToTownScript;
import Scripts.CemeteryMap.DeathScript;
import Scripts.House1Map.BrotherScript;
import Tilesets.CemeteryTileset;

import Utils.Point;


public class CemeteryMap extends Map {
    public CemeteryMap() {
        super("cemetery_map.txt", new CemeteryTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Skull skull1 = new Skull(4, getMapTile(8, 18).getLocation().subtractY(0));
        npcs.add(skull1);

        Crow crow1 = new Crow(2, getMapTile(7, 15).getLocation().subtractY(0));
        npcs.add(crow1);

        BodyBone bodybone = new BodyBone(4, getMapTile(9, 23).getLocation().subtractY(0));
        npcs.add(bodybone);

        BodyBoneTwo bodyBoneTwo= new BodyBoneTwo(4, getMapTile(30,19).getLocation().subtractY(0));
        npcs.add(bodyBoneTwo);

        BodyBoneThree bodyBoneThree= new BodyBoneThree(4, getMapTile(17,26).getLocation().subtractY(0));
        npcs.add(bodyBoneThree);

        SkullOne skullOne = new SkullOne(4, getMapTile(19,19).getLocation().subtractY(0));
        npcs.add(skullOne);

        SkullTwo skullTwo = new SkullTwo(4, getMapTile(28,25).getLocation().subtractY(0));
        npcs.add(skullTwo);

        SkullThree skullThree = new SkullThree(4, getMapTile(20,15).getLocation().subtractY(0));
        npcs.add(skullThree);


        BottomLegs bottomLegs = new BottomLegs(4, getMapTile(11,27).getLocation().subtractY(0));
        npcs.add(bottomLegs);

        RandBone randBone = new RandBone (4, getMapTile(10,30).getLocation().subtractY(0));
        npcs.add(randBone);

        RandBone randBone1 = new RandBone (4, getMapTile(9,29).getLocation().subtractY(0));
        npcs.add(randBone1);

        RandBone randBone2 = new RandBone (4, getMapTile(11,29).getLocation().subtractY(0));
        npcs.add(randBone2);
        
        Brother max = new Brother(1, getMapTile(28, 5).getLocation().subtractY(0));
        max.setInteractScript(new BrotherScript());
        npcs.add(max);

        return npcs;
    }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

    
        //temp trigger to test death cutscene
        Point deathLoc = getMapTile(45,12).getLocation();
        triggers.add(new Trigger(deathLoc.x, deathLoc.y, 10, 160, new DeathScript(), "deathScript"));
        
        Point toTown = getPositionByTileIndex(0,20);
        triggers.add(new Trigger(toTown.x + 10, toTown.y, 10,100, new CemeteryToTownScript(), "CemeteryToTown"));
    
        return triggers;
    }

    

   /*  @Override
    public void loadScripts() {

        getMapTile(6, 23).setInteractScript(new SimpleTextScript("Your house"));

    }*/

}
