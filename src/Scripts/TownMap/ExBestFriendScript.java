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
                addRequirement(new FlagRequirement("hasTalkedToButcher", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("HUH?! Oh hey, it’s been a while. Hm, this is for my mom?\n Thanks... oh Alex? ");
                    addText("it’s been a while since I’ve heard that name... ");
                    addText("Maybe you didn’t know but a couple months ago we went\n our separate ways.");
                    addText("Basically ditched me for that girlfriend of his. \nI’d also keep my eye on the butcher’s son, Peter. ");
                    addText("Also, these may help you more than they did for me... \nsome papers with your brother’s handwriting on them.");
                    addText("Planned on giving them to your mom, but you might get\n more use out of them. Good luck! ", new String[] { "\"Ask about ex\"", "\"Ask about Peter\"" });
                }});
             addScriptAction(new ChangeFlagScriptAction("hasTalkedToButcher", true));
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
                addScriptAction(new TextboxScriptAction() {{
                    addText("Alex’s girlfriend, Camilla, might know more, she’s \nusually hanging around town.");
                    addText("Maybe she’s out shopping or something- whatever,\n none of my business.");
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
                addScriptAction(new TextboxScriptAction("I’d also keep my eye on the butcher’s son, Peter."));
                addScriptAction(new TextboxScriptAction("Your brother might’ve really done it this time, pushing his\n buttons and tormenting him all the time."));
                addScriptAction(new TextboxScriptAction("He’s usually working at the butcher shop."));
            }});
        }});


        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToButcher", true));
                addScriptAction(new TextboxScriptAction() {{
                    addText("Sorry, I don’t really want to get involved. Maybe those \npages I gave you can help you out. ");
                   
                }});
            }});
        }});
        
               
        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}