package Scripts.House1Map;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to walrus npc
// checkout the documentation website for a detailed guide on how this script works
public class BrotherScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addScriptAction(new TextboxScriptAction() {{
                // addRequirement(new FlagRequirement("badMax", true));
                addRequirement(new FlagRequirement("badButcher", true));
                addRequirement(new FlagRequirement("badGF", true));
                addRequirement(new FlagRequirement("badDD", true));
                    addText("... Help");
                    //good ending (join him) cuscene
                }});
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                
                addScriptAction(new TextboxScriptAction("...Die"));
                //bad ending (die) cutscene
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}