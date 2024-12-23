package Scripts.TownMap;


import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;


public class ExBestFriendScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

      
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToMax", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("HUH?! Oh hey, it’s been a while. Hm, this is for my mom?\n Thanks... oh Alex? ");
                    addText("it’s been a while since I’ve heard that name... ");
                    addText("Maybe you didn’t know but a couple months ago we went\n our separate ways.");
                    addText("Basically ditched me for that girlfriend of his...");
                    addText("Also, these may help you more than they did for me... \nsome papers with your brother’s handwriting on them.");
                    addText("Planned on giving them to your mom, but you might get\n more use out of them. Good luck! ", new String[] { "\"Ask about ex\"", "\"Ask about bro\"" });
                }});
             addScriptAction(new ChangeFlagScriptAction("hasTalkedToMax", true));
            }});
        }});
        //Ask about Ex-Girlfriend
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 0;
                    }
                });
                addRequirement(new FlagRequirement("hasTalkedToMax", true));
                addScriptAction(new TextboxScriptAction() {{
                    addText("Alex’s girlfriend, Camilla, might know more, she’s \nusually hanging around town.");
                    addText("Always thought she was a bad influence, drinking \n so young and getting into trouble.");
                    addText("Maybe she’s out shopping or at that bar- whatever,\n none of my business.");
                }});
            }});
            //option- Ask about peter
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addRequirement(new FlagRequirement("hasTalkedToMax", true));
                addScriptAction(new TextboxScriptAction("Your brother loved hanging around town, sometimes at the \nbar sometimes in the woods."));
                addScriptAction(new TextboxScriptAction("He loved to do hunt with your uncle and stuff the prizes, \nalways thought they were creepy."));
                addScriptAction(new TextboxScriptAction("We started to grow distant when he decided to live up that \n party life at the bar with Camilla."));
                addScriptAction(new TextboxScriptAction("He was a pretty cool dude until that girl came along...\n I hope he's alright..."));
            }});
        }});

    
        
               
        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}