package org.academiadecodigo.bootcamp.master;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;



public class MenuSelection implements KeyboardHandler {

    private Picture menu;

    private boolean out = false;
    private boolean reset = false;

    public MenuSelection(MenuType type) {

        if (type == MenuType.GAMEOVER_MENU) {
            this.menu = new Picture(Field.PADDING, Field.PADDING, "resources/game-over.jpg");
        }
        if (type == MenuType.START_MENU) {
            this.menu = new Picture(Field.PADDING, Field.PADDING, "resources/start-game.jpg");
        }
        if (type == MenuType.NEXTLEVEL_MENU) {
            this.menu = new Picture(Field.PADDING, Field.PADDING, "resources/next-level-menu.png");
        }
        render(menu);
        keyboardInit();
    }

    public boolean isOut() {
        return out;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    private void render(Picture picture) {

        picture.draw();
    }

    private void keyboardInit() {


        Keyboard keyboard = new Keyboard(this);

        //P KEY
        KeyboardEvent pKeyPressed = new KeyboardEvent();
        pKeyPressed.setKey(KeyboardEvent.KEY_P);
        pKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pKeyPressed);


        //Q KEY
        KeyboardEvent qKeyPressed = new KeyboardEvent();
        qKeyPressed.setKey(KeyboardEvent.KEY_Q);
        qKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(qKeyPressed);

        //R KEY
        KeyboardEvent rKeyPressed = new KeyboardEvent();
        rKeyPressed.setKey(KeyboardEvent.KEY_R);
        rKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(rKeyPressed);

        //N
        KeyboardEvent nPressed = new KeyboardEvent();
        nPressed.setKey(KeyboardEvent.KEY_N);
        nPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(nPressed);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_P) {
            menu.delete();
            out = true;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {

            menu.delete();
            reset = true;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_N) {

            menu.delete();
            reset = true;
        }


    }

    @Override
    public void keyReleased (KeyboardEvent keyboardEvent){

    }

}
