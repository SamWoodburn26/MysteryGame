/*
 * SER225- Mystery Game
 * the dawgs- Adelina Chocho, Ella Berry, Morgan Montz, Sam Woodburn, Tuana Turhan
 * Fall 2024
 * 
 * package- Maps
 * class- TownMap: displays the outdoor town map
 */

 package Maps;

 import Level.*;
import Scripts.LockedAreaScript;
import Scripts.SimpleTextScript;
import Scripts.House1Map.BrotherExGFScript;
import Scripts.House1Map.InvestigatorScript0;
import Scripts.House1Map.InvestigatorScript1;
import Scripts.House1Map.InvestigatorScript2;
import Scripts.House1Map.TreeScript;
import Scripts.TownMap.PopUpFileImageScript;
import Scripts.House1Map.DamienScript;
import Scripts.House1Map.DrugDealerDaughterScript;
 import Scripts.TownMap.*;
 import Tilesets.TownTileset;
 import Utils.Point;
 import java.util.ArrayList;
 import NPCs.*;
 
 public class TownMap extends Map {
     public TownMap() {
         super("town_map1.txt", new TownTileset());
         this.playerStartPosition = getMapTile(17, 20).getLocation();
     }
 
     @Override
     public ArrayList<NPC> loadNPCs() {
         ArrayList<NPC> npcs = new ArrayList<>();
 
         Max max = new Max(1, getMapTile(34, 8).getLocation().subtractY(0));
         max.setInteractScript(new ExBestFriendScript());
         npcs.add(max);

<<<<<<< HEAD
         Investigator investigator = new Investigator(1, getMapTile(30, 50).getLocation().subtractY(40));
         investigator.setInteractScript(new InvestigatorScript());
         npcs.add(investigator);
=======

         Investigator investigator0 = new Investigator(1, getMapTile(26, 50).getLocation().subtractY(40));
         investigator0.setInteractScript(new InvestigatorScript0());
         npcs.add(investigator0);

         Investigator investigator1 = new Investigator(1, getMapTile(91, 8).getLocation().subtractY(40));
         investigator1.setInteractScript(new InvestigatorScript1());
         npcs.add(investigator1);

         Investigator investigator2 = new Investigator(1, getMapTile(17, 18).getLocation().subtractY(40));
         investigator2.setInteractScript(new InvestigatorScript2());
         npcs.add(investigator2);
>>>>>>> d195f62aada29f22367c3f0262205e62c3135ae0

         Boss boss = new Boss(1, getMapTile(50, 66).getLocation().subtractY(40));
         boss.setInteractScript(new DamienScript());
         npcs.add(boss);

         Crow crow1 = new Crow(1, getMapTile(36, 50).getLocation().subtractY(40));
         npcs.add(crow1);

         ExGf ex = new ExGf(1, getMapTile(33, 37).getLocation().subtractY(40));
         ex.setInteractScript(new BrotherExGFScript());
         npcs.add(ex); 

         MHDaughter daughter = new MHDaughter(1, getMapTile(70, 57).getLocation().subtractY(40));
         daughter.setInteractScript(new DrugDealerDaughterScript());
         npcs.add(daughter);

         DrugDealer drugDealer = new DrugDealer(1,getMapTile(70, 54).getLocation().subtractY(0));
         drugDealer.setInteractScript(new DrugDealerDaughterScript());
         npcs.add(drugDealer);

         Bike bike = new Bike(1, getMapTile(87, 16).getLocation().subtractY(0));
         bike.setInteractScript(new BikeTangibility());
         npcs.add(bike);

         ScaryDog dog = new ScaryDog(1, getMapTile(26,53).getLocation().subtractY(40));
         dog.setInteractScript(new BikeTangibility());
         npcs.add(dog);

 
         return npcs;
     }
 
 
     @Override
     public ArrayList<Trigger> loadTriggers() {
         ArrayList<Trigger> triggers = new ArrayList<>();
 
         //trigger to enter the main character's house
         Point house1 = getPositionByTileIndex(21, 13);
         triggers.add(new Trigger(house1.x, house1.y, 95, 10, new TownToHouse1Script(), "townToHouse1"));
 
 
         // trigger to enter butcher
<<<<<<< HEAD
         Point butcherShop = getPositionByTileIndex(69, 46);
         triggers.add(new Trigger(butcherShop.x, butcherShop.y, 20, 10, new TownToButcherScript(), "townToButcher"));
=======
         Point butcherShop = getPositionByTileIndex(69, 47);
         triggers.add(new Trigger(butcherShop.x, butcherShop.y, 50, 10, new TownToButcherScript(), "townToButcher"));
>>>>>>> d195f62aada29f22367c3f0262205e62c3135ae0

         //trigger to enter cemetery
        //  Point cemetery = getPositionByTileIndex(96, 18);
        //  triggers.add(new Trigger(cemetery.x+20 , cemetery.y, 10, 100, new TownToCemeteryScript(), "townToButcher"));
 
 
        //  Point lockedCemetery = getPositionByTileIndex(96, 21);
        //  triggers.add(new Trigger(lockedCemetery.x , lockedCemetery.y, 10, 100, new LockedAreaScript(), "lockedCemetery"));
 

        Point lockedCemetery = getPositionByTileIndex(96, 18);
        //triggers.add(new Trigger(lockedCemetery.x , lockedCemetery.y, 1, 2, new LockedAreaScript(), "lockedCemetery"));
        triggers.add(new Trigger(lockedCemetery.x , lockedCemetery.y, 10, 10, new TownToCemeteryScript(), "townToCemetery"));
 
        Point fbiFile = getPositionByTileIndex(95, 75);
        triggers.add(new Trigger(fbiFile.x , fbiFile.y, 50, 50, new PopUpFileImageScript(), "fileImage"));
 


        // pop up image trigger
        Point photoLoc = getPositionByTileIndex(68, 13);
        triggers.add(new Trigger(photoLoc.x, photoLoc.y, 100, 5, new PopUpGraveyardImageScript(), "graveyardImage"));

         return triggers;
     }
 
     @Override
     public void loadScripts() {
        
        // file image needs multiple so player can touch file from anywhere and open
        getMapTile(94, 73).setInteractScript(new PopUpFileImageScript());
        getMapTile(95, 73).setInteractScript(new PopUpFileImageScript());
        getMapTile(93, 74).setInteractScript(new PopUpFileImageScript());
        getMapTile(94, 74).setInteractScript(new PopUpFileImageScript());
        getMapTile(94, 75).setInteractScript(new PopUpFileImageScript());
        getMapTile(95, 74).setInteractScript(new PopUpFileImageScript());
        getMapTile(95, 75).setInteractScript(new PopUpFileImageScript());

         getMapTile(24, 13).setInteractScript(new SimpleTextScript("Your house"));
 
         getMapTile(73, 44).setInteractScript(new SimpleTextScript("Peter's Butcher Shop"));
 
         getMapTile(36, 18).setInteractScript(new SimpleTextScript("Max's house"));
 
         getMapTile(52, 44).setInteractScript(new SimpleTextScript("Town Hall"));
 



         getMapTile(2, 6).setInteractScript(new TreeScript());


     }
 
 }
 