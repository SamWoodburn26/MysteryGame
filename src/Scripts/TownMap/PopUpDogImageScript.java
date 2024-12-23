package Scripts.TownMap;
/*
 * SER225- Mystery Game
 * the dawgs- Adelina Chocho, Ella Berry, Morgan Montz, Sam Woodburn, Tuana Turhan
 * Fall 2024
 * 
 * package- Scripts- TownMap
 * class- pop-up scary dog image
*/

import java.util.ArrayList;
import Level.*;
import ScriptActions.*;

public class PopUpDogImageScript extends Script{


 @Override
 public ArrayList<ScriptAction> loadScriptActions() {

     ArrayList<ScriptAction> scriptActions = new ArrayList<>();

     System.out.println("scaryDogPopUp triggered");

     //change the image interact flag to true
     scriptActions.add(new ChangeFlagScriptAction("scaryDogPopUp", true));
     
     return scriptActions;
 }
}
