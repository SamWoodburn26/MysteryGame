package Screens;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import Level.FlagManager;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;

public class ExgfPuzzle extends Screen{
    protected InputTextBox q1Ans, q2Ans, q3Ans;
    protected BufferedImage q1, q2, q3, instructions;
    protected FlagManager flagManager;
    protected  ImageLoader imageLoader = new ImageLoader();
    protected boolean q1Solved, q2Solved, q3Solved, gfPuzzleSolved = false;
    protected boolean instructionsVisible = true;
    protected KeyLocker keyLocker = new KeyLocker();

    public ExgfPuzzle(FlagManager flagManager){
        this.flagManager = flagManager;
        instructions = imageLoader.load("exgfPuzzleInstruc.png");
        //question 1
        q1 = imageLoader.load("q1.png");
        q1Ans = new InputTextBox(450,5,230,50);
        //question 2
        q2 = imageLoader.load("q2.png");
        q2Ans = new InputTextBox(520,200,260,50);
        q2Ans.setActive(false);
        //question 3
        q3 = imageLoader.load("q3.png");
        q3Ans = new InputTextBox(480, 450, 200, 50);
        q3Ans.setActive(false);
        //more questions to be added in the future
    }

    @Override
    public void initialize() {}

    @Override
    public void update() {
        if(instructionsVisible){
            if (Keyboard.isKeyUp(Key.B) && !keyLocker.isKeyLocked(Key.B)) {
            keyLocker.lockKey(Key.B);
            } 
            else if(Keyboard.isKeyDown(Key.B)){
                instructionsVisible = false;
                keyLocker.unlockKey(Key.B);
            }
        }
        else{
            //update question 1
            if(!q1Solved){
                q1Ans.setActive(true);
                q1Ans.update();
                if(q1Ans.getText().equalsIgnoreCase("mallet")){
                    q1Solved = true;
                    q1Ans.setActive(false);
                    q2Ans.clear();
                }
                if(q1Ans.getText().length() == 6 && !q1Solved){
                    q1Ans.clear();
                }
            }

            //update question 2
            if(q1Solved && !q2Solved){
                q1Ans.setActive(false);
                q2Ans.setActive(true);
                q2Ans.update();
                if(q2Ans.getText().equalsIgnoreCase("labyrinth")){
                    q2Solved = true;
                    q2Ans.setActive(false);
                    q3Ans.clear();
                }
                if(q2Ans.getText().length() == 9 && !q2Solved){
                    q2Ans.clear();
                }
            }

            //update question 3
            if(q1Solved && q2Solved && !q3Solved){
                q2Ans.setActive(false);
                q3Ans.setActive(true);
                q3Ans.update();
                if(q3Ans.getText().equalsIgnoreCase("panic")){
                    q3Solved = true;
                    q3Ans.setActive(false);
                    gfPuzzleSolved = true;
                }
                if(q3Ans.getText().length() == 5 && !q3Solved){
                    q3Ans.clear();
                }
            }

            if(gfPuzzleSolved){
                flagManager.setFlag("exGfPuzzleSolved");
            }
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        if(instructionsVisible){
            graphicsHandler.drawImage(instructions, 50, 50, 500, 500);
        }
        else{
            graphicsHandler.drawImage(q1, 5, 5, 450, 50);
            q1Ans.draw(graphicsHandler);
            if(q1Solved){
                graphicsHandler.drawImage(q2, 5, 200, 520, 50);
                q2Ans.draw(graphicsHandler);
                if(q2Solved){
                    graphicsHandler.drawImage(q3, 5, 450, 460, 50);
                    q3Ans.draw(graphicsHandler);
                }
            }
        }
    }
    
    //text box class
    public class InputTextBox {
        private StringBuilder inputText;
        private boolean isActive;
        private int x, y, width, height;
        private KeyLocker keyLocker = new KeyLocker();
        /*
         * for other people looking at this, i know there is a better
         * way to do it but i don't have time and this is easiest
         */
        protected boolean aKeyUsed, bKeyUsed, cKeyUsed, dKeyUsed, eKeyUsed, fKeyUsed, gKeyUsed, hKeyUsed, iKeyUsed, jKeyUsed, kKeyUsed, lKeyUsed, mKeyUsed, nKeyUsed, oKeyUsed, pKeyUsed, qKeyUsed, rKeyUsed, sKeyUsed, tKeyUsed, uKeyUsed, vKeyUsed, wKeyUsed, xKeyUsed, yKeyUsed, zKeyUsed = false;

        public InputTextBox(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.inputText = new StringBuilder();
            this.isActive = false;
        }

        public void update( ) {
            if (!isActive) return;

            //handling backspace
            if (Keyboard.isKeyUp(Key.BACKSPACE) && inputText.length() > 0 && !keyLocker.isKeyLocked(Key.BACKSPACE)) {
                inputText.deleteCharAt(inputText.length() - 1);
                keyLocker.lockKey(Key.BACKSPACE);
            }
            else if(Keyboard.isKeyDown(Key.BACKSPACE)){
                keyLocker.unlockKey(Key.BACKSPACE);
            }
            //handling character input
            /* key A */
            if (Keyboard.isKeyUp(Key.A) && !keyLocker.isKeyLocked(Key.A)) {
                if(aKeyUsed){
                    inputText.append('A');
                }
                else{
                   aKeyUsed = true;
                }
                keyLocker.lockKey(Key.A);
            }
            else if(Keyboard.isKeyDown(Key.A)){
                keyLocker.unlockKey(Key.A);
            }
            /* key B */
            if (Keyboard.isKeyUp(Key.B) && !keyLocker.isKeyLocked(Key.B)) {
                if(bKeyUsed){
                    inputText.append('B');
                }
                else{
                    bKeyUsed = true;
                }
                keyLocker.lockKey(Key.B);
            }
            else if(Keyboard.isKeyDown(Key.B)){
                keyLocker.unlockKey(Key.B);
            }
            /* key C */
            if (Keyboard.isKeyUp(Key.C) && !keyLocker.isKeyLocked(Key.C)) {
                if(cKeyUsed){
                    inputText.append('C');
                }
                else{
                    cKeyUsed = true;
                }
                keyLocker.lockKey(Key.C);
            }
            else if(Keyboard.isKeyDown(Key.C)){
                keyLocker.unlockKey(Key.C);
            }
            /* key D */
            if (Keyboard.isKeyUp(Key.D) && !keyLocker.isKeyLocked(Key.D)) {
                if(dKeyUsed){
                    inputText.append('D');
                }
                else{
                    dKeyUsed = true;
                }
                keyLocker.lockKey(Key.D);
            }
            else if(Keyboard.isKeyDown(Key.D)){
                keyLocker.unlockKey(Key.D);
            }
            /* key E */
            if (Keyboard.isKeyUp(Key.E) && !keyLocker.isKeyLocked(Key.E)) {
                if(eKeyUsed){
                    inputText.append('E');
                }
                else{
                    eKeyUsed = true;
                }
                keyLocker.lockKey(Key.E);
            }
            else if(Keyboard.isKeyDown(Key.E)){
                keyLocker.unlockKey(Key.E);
            }
            /* key F */
            if (Keyboard.isKeyUp(Key.F) && !keyLocker.isKeyLocked(Key.F)) {
                if(fKeyUsed){
                    inputText.append('F');
                }
                else{
                    fKeyUsed = true;
                }
                keyLocker.lockKey(Key.F);
            }
            else if(Keyboard.isKeyDown(Key.F)){
                keyLocker.unlockKey(Key.F);
            }
            /* key D */
            if (Keyboard.isKeyUp(Key.G) && !keyLocker.isKeyLocked(Key.G)) {
                if(gKeyUsed){
                    inputText.append('G');
                }
                else{
                    gKeyUsed = true;
                }
                keyLocker.lockKey(Key.G);
            }
            else if(Keyboard.isKeyDown(Key.G)){
                keyLocker.unlockKey(Key.G);
            }
            /* key H */
            if (Keyboard.isKeyUp(Key.H) && !keyLocker.isKeyLocked(Key.H)) {
                if(hKeyUsed){
                    inputText.append('H');
                }
                else{
                    hKeyUsed = true;
                }
                keyLocker.lockKey(Key.H);
            }
            else if(Keyboard.isKeyDown(Key.H)){
                keyLocker.unlockKey(Key.H);
            }
            /* key I */
            if (Keyboard.isKeyUp(Key.I) && !keyLocker.isKeyLocked(Key.I)) {
                if(iKeyUsed){
                    inputText.append('I');
                }
                else{
                    iKeyUsed = true;
                }
                keyLocker.lockKey(Key.I);
            }
            else if(Keyboard.isKeyDown(Key.I)){
                keyLocker.unlockKey(Key.I);
            }
            /* key J */
            if (Keyboard.isKeyUp(Key.J) && !keyLocker.isKeyLocked(Key.J)) {
                if(jKeyUsed){
                    inputText.append('J');
                }
                else{
                    jKeyUsed = true;
                }
                keyLocker.lockKey(Key.J);
            }
            else if(Keyboard.isKeyDown(Key.J)){
                keyLocker.unlockKey(Key.J);
            }
            /* key K */
            if (Keyboard.isKeyUp(Key.K) && !keyLocker.isKeyLocked(Key.K)) {
                if(kKeyUsed){
                    inputText.append('K');
                }
                else{
                    kKeyUsed = true;
                }
                keyLocker.lockKey(Key.K);
            }
            else if(Keyboard.isKeyDown(Key.K)){
                keyLocker.unlockKey(Key.K);
            }
            /* key L */
            if (Keyboard.isKeyUp(Key.L) && !keyLocker.isKeyLocked(Key.L)) {
                if(lKeyUsed){
                    inputText.append('L');
                }
                else{
                    lKeyUsed = true;
                }
                keyLocker.lockKey(Key.L);
            }
            else if(Keyboard.isKeyDown(Key.L)){
                keyLocker.unlockKey(Key.L);
            }
            /* key M */
            if (Keyboard.isKeyUp(Key.M) && !keyLocker.isKeyLocked(Key.M)) {
                if(mKeyUsed){
                    inputText.append('M');
                }
                else{
                    mKeyUsed = true;
                }
                keyLocker.lockKey(Key.M);
            }
            else if(Keyboard.isKeyDown(Key.M)){
                keyLocker.unlockKey(Key.M);
            }
            /* key N */
            if (Keyboard.isKeyUp(Key.N) && !keyLocker.isKeyLocked(Key.N)) {
                if(nKeyUsed){
                    inputText.append('N');
                }
                else{
                    nKeyUsed = true;
                }
                keyLocker.lockKey(Key.N);
            }
            else if(Keyboard.isKeyDown(Key.N)){
                keyLocker.unlockKey(Key.N);
            }
            /* key O */
            if (Keyboard.isKeyUp(Key.O) && !keyLocker.isKeyLocked(Key.O)) {
                if(oKeyUsed){
                    inputText.append('O');
                }
                else{
                    oKeyUsed = true;
                }
                keyLocker.lockKey(Key.O);
            }
            else if(Keyboard.isKeyDown(Key.O)){
                keyLocker.unlockKey(Key.O);
            }
            /* key P */
            if (Keyboard.isKeyUp(Key.P) && !keyLocker.isKeyLocked(Key.P)) {
                if(pKeyUsed){
                    inputText.append('P');
                }
                else{
                    pKeyUsed = true;
                }
                keyLocker.lockKey(Key.P);
            }
            else if(Keyboard.isKeyDown(Key.P)){
                keyLocker.unlockKey(Key.P);
            }
            /* key Q */
            if (Keyboard.isKeyUp(Key.Q) && !keyLocker.isKeyLocked(Key.Q)) {
                if(qKeyUsed){
                    inputText.append('Q');
                }
                else{
                    qKeyUsed = true;
                }
                keyLocker.lockKey(Key.Q);
            }
            else if(Keyboard.isKeyDown(Key.Q)){
                keyLocker.unlockKey(Key.Q);
            }
            /* key R */
            if (Keyboard.isKeyUp(Key.R) && !keyLocker.isKeyLocked(Key.R)) {
                if(rKeyUsed){
                    inputText.append('R');
                }
                else{
                    rKeyUsed = true;
                }
                keyLocker.lockKey(Key.R);
            }
            else if(Keyboard.isKeyDown(Key.R)){
                keyLocker.unlockKey(Key.R);
            }
            /* key S */
            if (Keyboard.isKeyUp(Key.S) && !keyLocker.isKeyLocked(Key.S)) {
                if(sKeyUsed){
                    inputText.append('S');
                }
                else{
                    sKeyUsed = true;
                }
                keyLocker.lockKey(Key.S);
            }
            else if(Keyboard.isKeyDown(Key.S)){
                keyLocker.unlockKey(Key.S);
            }
            /* key T */
            if (Keyboard.isKeyUp(Key.T) && !keyLocker.isKeyLocked(Key.T)) {
                if(tKeyUsed){
                    inputText.append('T');
                }
                else{
                    tKeyUsed = true;
                }
                keyLocker.lockKey(Key.T);
            }
            else if(Keyboard.isKeyDown(Key.T)){
                keyLocker.unlockKey(Key.T);
            }
            /* key U */
            if (Keyboard.isKeyUp(Key.U) && !keyLocker.isKeyLocked(Key.U)) {
                if(uKeyUsed){
                    inputText.append('U');
                }
                else{
                    uKeyUsed = true;
                }
                keyLocker.lockKey(Key.U);
            }
            else if(Keyboard.isKeyDown(Key.U)){
                keyLocker.unlockKey(Key.U);
            }
            /* key V */
            if (Keyboard.isKeyUp(Key.V) && !keyLocker.isKeyLocked(Key.V)) {
                if(vKeyUsed){
                    inputText.append('V');
                }
                else{
                    vKeyUsed = true;
                }
                keyLocker.lockKey(Key.V);
            }
            else if(Keyboard.isKeyDown(Key.V)){
                keyLocker.unlockKey(Key.V);
            }
            /* key W */
            if (Keyboard.isKeyUp(Key.W) && !keyLocker.isKeyLocked(Key.W)) {
                if(wKeyUsed){
                    inputText.append('W');
                }
                else{
                    wKeyUsed = true;
                }
                keyLocker.lockKey(Key.W);
            }
            else if(Keyboard.isKeyDown(Key.W)){
                keyLocker.unlockKey(Key.W);
            }
            /* key X */
            if (Keyboard.isKeyUp(Key.X) && !keyLocker.isKeyLocked(Key.X)) {
                if(xKeyUsed){
                    inputText.append('X');
                }
                else{
                    xKeyUsed = true;
                }
                keyLocker.lockKey(Key.X);
            }
            else if(Keyboard.isKeyDown(Key.X)){
                keyLocker.unlockKey(Key.X);
            }
            /* key Y */
            if (Keyboard.isKeyUp(Key.Y) && !keyLocker.isKeyLocked(Key.Y)) {
                if(yKeyUsed){
                    inputText.append('Y');
                }
                else{
                    yKeyUsed = true;
                }
                keyLocker.lockKey(Key.Y);
            }
            else if(Keyboard.isKeyDown(Key.Y)){
                keyLocker.unlockKey(Key.Y);
            }
            /* key Z */
            if (Keyboard.isKeyUp(Key.Z) && !keyLocker.isKeyLocked(Key.Z)) {
                if(zKeyUsed){
                    inputText.append('Z');
                }
                else{
                    zKeyUsed = true;
                }
                keyLocker.lockKey(Key.Z);
            }
            else if(Keyboard.isKeyDown(Key.Z)){
                keyLocker.unlockKey(Key.Z);
            }
        }

        public void draw(GraphicsHandler graphicsHandler) {
            graphicsHandler.drawFilledRectangle(x, y, width, height, Color.WHITE);
            graphicsHandler.drawMeatString(inputText.toString(), x + 5, y +30, null, Color.BLACK);
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getText() {
            return inputText.toString();
        }

        public void clear(){
            int size = inputText.toString().length();
            for(int i = size; i > 0; i--){
                inputText.deleteCharAt(inputText.length() - 1);
            }
        }
    }
}



