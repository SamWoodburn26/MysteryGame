/*
 * SER225- Mystery Game
 * the dawgs- Adelina Chocho, Ella Berry, Morgan Montz, Sam Woodburn, Tuana Turhan
 * Fall 2024
 * 
 * package- Screens
 * class- JournalUI: Manages the journal UI in the game, handling page navigation, visibility toggles, 
 * and dynamic content updates based on gameplay flags (more pages added as game is played)
 */

package Screens;

import Engine.*;
import Level.FlagManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class JournalUI {
    protected LinkedList<BufferedImage> journalPages;
    //basic pages
    protected BufferedImage journalCover, emptyJournal, journalPageIntro;
    //specific journal pages- journalPage_whoItIsAbout
    protected BufferedImage journalPage_Max, journalPage_Peter, journalPage_Exgf;
    protected BufferedImage currPagePic;
    protected FlagManager flagManager;
    protected int currPage;
    protected boolean journalIsVisible = false;
    protected boolean bookOpen = false;
    protected KeyLocker keyLocker = new KeyLocker();

    //constructor with FlagManager parameter
    public JournalUI(FlagManager flagManager) {
        this.flagManager = flagManager;
        // Load journal images
        journalCover = ImageLoader.load("JournalCover.png");
        emptyJournal = ImageLoader.load("EmptyJournal.png");
        journalPageIntro = ImageLoader.load("JPageIntro.png");
        journalPage_Max = ImageLoader.load("jPage_Max.png");
        journalPage_Peter = ImageLoader.load("jPage_Peter.png");
        journalPage_Exgf = ImageLoader.load("jPage_exGf.png");
        //add first images to list and a few empty
        journalPages = new LinkedList<BufferedImage>();
        journalPages.add(journalCover);
        journalPages.add(journalPageIntro);
        journalPages.add(emptyJournal);
        //set the current page to the journal cover
        currPage = 0;
    }

    public void setFlagManager(FlagManager flagManager){
        this.flagManager = flagManager;
    }

    //is the journal screen visible
    public void toggleVisibility() {
        journalIsVisible = !journalIsVisible;
        bookOpen = false; // Reset to cover when toggled
    }

    //update
    public void update() {
        //if the player has talked to mom, add that page
        if (journalIsVisible && flagManager.isFlagSet("hasTalkedToMom")) {
            // Check if the page from mom is not already added to avoid adding it multiple times
            if (!journalPages.contains(journalPage_Max)) {
                //remove blank page, add page from max, add blank page back
                journalPages.removeLast();
                journalPages.add(journalPage_Max);
                journalPages.add(emptyJournal);
            }
            //check for talking to max
            if (journalIsVisible && flagManager.isFlagSet("hasTalkedToMax")) {
                //check if added, if not add butcher page than ex gf page
                if(!journalPages.contains(journalPage_Peter)  &&  !journalPages.contains(journalPage_Exgf)){
                    //remove blank page, add others, add blank page back
                    journalPages.removeLast();
                    journalPages.add(journalPage_Peter);
                    journalPages.add(journalPage_Exgf);
                    journalPages.add(emptyJournal);
                }
            }
            
        }

        //if right arrow key is clicked, increase current page count, move to next page if available in list
        if (Keyboard.isKeyUp(Key.RIGHT) && !keyLocker.isKeyLocked(Key.RIGHT)) {
            bookOpen = true;
            if (bookOpen && currPage < journalPages.size() - 1) {
                currPage++;
                currPagePic = journalPages.get(currPage);
                //lock the key until it is pressed again
                keyLocker.lockKey(Key.RIGHT);  
            }
        }
        if (Keyboard.isKeyDown(Key.RIGHT)) {
            //unlock when key is pressed down again
            keyLocker.unlockKey(Key.RIGHT);  
        }
        // Lock the right key if it's the last page
        if (currPage == journalPages.size() - 1) {
            keyLocker.lockKey(Key.RIGHT);  
        }

        //if left arrow key is clicked, decrease current page count, move to previous page if not already at page 0
        if (Keyboard.isKeyUp(Key.LEFT) && !keyLocker.isKeyLocked(Key.LEFT)) {
            if (bookOpen && currPage > 0) {
                currPage--;
                currPagePic = journalPages.get(currPage);
                // Lock the key until it is pressed again
                keyLocker.lockKey(Key.LEFT);  
            }
        }
        if (Keyboard.isKeyDown(Key.LEFT)) {
            // Unlock when key is pressed down again
            keyLocker.unlockKey(Key.LEFT);  
        }
    }

    //draw
    public void draw(GraphicsHandler graphicsHandler) {
        //draw while the journal is visible/ is open
        if (journalIsVisible) {
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
            //if the book was opened draw the current page, if not draw the cover
            if (bookOpen) {
                graphicsHandler.drawImage(currPagePic, 0, 0, 800, 550);
            }
            else{
                graphicsHandler.drawImage(journalCover, 20, 20, 700, 550);
            }
        }
    }

}



