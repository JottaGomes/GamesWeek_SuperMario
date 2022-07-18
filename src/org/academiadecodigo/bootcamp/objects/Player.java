package org.academiadecodigo.bootcamp.objects;
import org.academiadecodigo.bootcamp.master.Handler;
import org.academiadecodigo.bootcamp.master.Sound;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends GameObject implements KeyboardHandler {

    public static final int gravity = 1;
    private Handler handler;

    private int lives;
    private boolean directionLeft;
    private boolean dead;
    private int maxVelGrav;
    private int maxVel;
    private int time;
    private boolean hurt;
    private boolean invulnerable;
    private boolean falling;
    private boolean jumping;
    private Picture[] frames;
    private Picture player;
    private Sound coinSound;
    private Sound jumpSound;
    private Sound kickSound;
    private Sound hurtSound;
    private boolean winner = false;

    public Player(int x, int y, Handler handler){

        this.handler = handler;
        falling = true;
        jumping = false;
        this.posX = x;
        this.posY = y;
        this.velX = 0;
        this.velY = 0;
        this.maxX = posX + 32;
        this.maxY = posY + 64;
        this.maxVel = 6;
        this.maxVelGrav = 15;
        this.lives = 3;
        this.invulnerable = false;

        this.hurtSound = new Sound("resources/hurt-sound.wav");
        this.kickSound = new Sound("resources/kick-sound.wav");
        this.jumpSound = new Sound("resources/jump-sound.wav");
        this.coinSound = new Sound("resources/coin-sound.wav");
        frames = new Picture[]{new Picture(x, y, "resources/mario-right.png"), new Picture(x, y, "resources/mario-right-run.png"), new Picture(x, y, "resources/mario-left.png"), new Picture(x, y, "resources/mario-left-run.png"), new Picture(x, y, "resources/mario-jump-right.png"), new Picture(x, y, "resources/mario-jump-left.png"),new Picture(x, y, "resources/mario-right-run-1.png"),new Picture(x, y, "resources/mario-left-run-1.png")};

        keyboardInit();
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isWinner() {
        return winner;
    }

    public Picture getPlayer(){
        return player;
    }

    @Override
    public void render() {

        player = frames[0];
        player.draw();
    }

    @Override
    public void tick() {

        if(velX > 0){
            directionLeft = false;
        }
        if(velX < 0){
            directionLeft = true;
        }
        if(velX == 0 && !directionLeft && !jumping){
            player.delete();
            player = frames[0];
            player.draw();
        }
        if(velX == 0 && directionLeft && !jumping){
            player.delete();
            player = frames[2];
            player.draw();
        }
        if(velX > 0 && !jumping){
            player.delete();
            if(time % 2 == 0) {
                player = frames[1];
                player.draw();

            }else{
                player = frames[6];
                player.draw();
            }
        }
        if(velX < 0 && !jumping){
            player.delete();
            if(time % 2 == 0) {
                player = frames[3];
                player.draw();

            }else{
                player = frames[7];
                player.draw();
            }
        }
        if(jumping && directionLeft){
            player.delete();
            player = frames[5];
            player.draw();
        }
        if(jumping && !directionLeft){
            player.delete();
            player = frames[4];
            player.draw();
        }


        int prevPosX = posX;
        int prevPosY = posY;

        posX += velX;
        posY += velY;
        this.maxX = posX + 32;
        this.maxY = posY + 64;

        velY += gravity;

        if(velY > maxVelGrav && falling == true) {
            velY = maxVelGrav;
        }
        if(velX > maxVel) {
            velX = maxVel;
        }
        if(velX < -maxVel) {
            velX = -maxVel;
        }

        if(hurt && time % 10 == 0){

            velX = 0;
            hurt = false;
        }

        if(invulnerable && time % 80 == 0){

            invulnerable = false;
        }

        crashes();


        for(Picture pic : frames){
            pic.translate(posX - prevPosX, posY - prevPosY);
        }

        time++;
    }

    private void die(){

        dead = true;
    }

    private void crashes(){

        for(int i = 0; i < handler.getGameObjects().size(); i++){

            if(handler.getGameObjects().get(i) instanceof LavaGenerator){

                for(LavaGenerator.Lava lava : ((LavaGenerator) handler.getGameObjects().get(i)).getLavaDrops()){

                    if(handler.detectCollision(getDown(), lava)){

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                                posY = lava.getMaxY() - 64;
                                velY -= 10;
                                invulnerable = true;
                        }
                    }

                    if(handler.detectCollision(getUp(), lava)){

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posY = lava.getMaxY();

                            int random = ((int) (Math.random() * 2));

                            if(random == 0){
                                velX += 10;
                            }else {
                                velX -= 10;
                            }
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.detectCollision(getLeft(), lava)){

                        if(invulnerable == false) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = lava.getPosX() + 32;
                            velX += 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.detectCollision(getRight(), lava)){

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = lava.getPosX() - 32;
                            velX -= 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                }
            }
            if(handler.getGameObjects().get(i) instanceof Bowser){

                for(Bowser.FireBall fireBall : ((Bowser) handler.getGameObjects().get(i)).getFireBalls()){

                    if(handler.detectCollision(getDown(), fireBall)){

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posY = fireBall.getMaxY() - 64;
                            velY -= 10;
                            invulnerable = true;
                        }
                    }

                    if(handler.detectCollision(getUp(), fireBall)){

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posY = fireBall.getMaxY();

                            int random = ((int) (Math.random() * 2));

                            if(random == 0){
                                velX += 10;
                            }else {
                                velX -= 10;
                            }
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.detectCollision(getLeft(), fireBall)){

                        if(invulnerable == false) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = fireBall.getPosX() + 32;
                            velX += 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.detectCollision(getRight(), fireBall)){

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = fireBall.getPosX() - 32;
                            velX -= 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                }
            }

            if(handler.getGameObjects().get(i) != this) {

                if (handler.detectCollision(getDown(), handler.getGameObjects().get(i))) {

                    if(handler.getGameObjects().get(i) instanceof Block || handler.getGameObjects().get(i) instanceof SpecialBlock) {

                        velY = 0;
                        posY = handler.getGameObjects().get(i).getPosY() - player.getHeight();
                        falling = false;
                        jumping = false;
                    }
                    if(handler.getGameObjects().get(i) instanceof Turtle) {

                        if(!invulnerable) {
                            kickSound.play(true);
                            ((Turtle) handler.getGameObjects().get(i)).die();
                            posY = handler.getGameObjects().get(i).getPosY() - player.getHeight();
                            velY -= 25;
                            time = 0;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Bowser) {

                        if(!invulnerable) {
                            kickSound.play(true);
                            ((Bowser) handler.getGameObjects().get(i)).setLives(((Bowser) handler.getGameObjects().get(i)).getLives() - 1);
                            if(((Bowser) handler.getGameObjects().get(i)).getLives() == 0){
                                winner = true;
                            }
                            posY = handler.getGameObjects().get(i).getPosY() - player.getHeight();
                            velY -= 35;
                            time = 0;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Coins) {

                        ((Coins) handler.getGameObjects().get(i)).die();
                        coinSound.play(true);

                    }
                    if(handler.getGameObjects().get(i) instanceof Princess) {

                        velY = 0;
                        posY = handler.getGameObjects().get(i).getPosY() - player.getHeight();
                        falling = false;
                        jumping = false;
                        winner =true;
                    }
                }

                if (handler.detectCollision(getUp(), handler.getGameObjects().get(i))) {
                    if(handler.getGameObjects().get(i) instanceof Coins) {

                        coinSound.play(true);
                        ((Coins) handler.getGameObjects().get(i)).die();
                    }
                    if(handler.getGameObjects().get(i) instanceof Block || handler.getGameObjects().get(i) instanceof SpecialBlock) {

                        velY = 0;
                        posY = handler.getGameObjects().get(i).maxY ;

                    }
                    if(handler.getGameObjects().get(i) instanceof Princess) {

                        velY = 0;
                        posY = handler.getGameObjects().get(i).maxY ;

                    }
                }

                if (handler.detectCollision(getLeft(), handler.getGameObjects().get(i))) {

                    if(handler.getGameObjects().get(i) instanceof Coins) {

                        coinSound.play(true);
                        ((Coins) handler.getGameObjects().get(i)).die();
                    }

                    if(handler.getGameObjects().get(i) instanceof Block || handler.getGameObjects().get(i) instanceof SpecialBlock) {

                        posX = handler.getGameObjects().get(i).getPosX() + 32;
                    }

                    if(handler.getGameObjects().get(i) instanceof Turtle) {

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = handler.getGameObjects().get(i).getPosX() + 32;
                            velX += 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Bowser) {

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = handler.getGameObjects().get(i).getPosX() + 126;
                            velX += 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Princess) {

                        posX = handler.getGameObjects().get(i).getPosX() + 32;
                        winner =true;

                    }
                }

                if (handler.detectCollision(getRight(), handler.getGameObjects().get(i))) {

                    if(handler.getGameObjects().get(i) instanceof Coins) {

                        coinSound.play(true);
                        ((Coins) handler.getGameObjects().get(i)).die();
                    }

                    if(handler.getGameObjects().get(i) instanceof Block || handler.getGameObjects().get(i) instanceof SpecialBlock) {
                        posX = handler.getGameObjects().get(i).getPosX() - 32;
                    }
                    if(handler.getGameObjects().get(i) instanceof Turtle) {

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = handler.getGameObjects().get(i).getPosX() - 32;
                            velX -= 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Bowser) {

                        if(!invulnerable) {
                            hurtSound.play(true);
                            hurt = true;
                            lives--;
                            handler.getLives().setNum(handler.getLives().getNum() - 1);
                            if (lives == 0) {
                                die();
                            }
                            posX = handler.getGameObjects().get(i).getPosX() - 32;
                            velX -= 10;
                            invulnerable = true;
                            time = 0;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Princess) {
                        posX = handler.getGameObjects().get(i).getPosX() - 32;
                        winner =true;

                    }
                }
            }
        }
    }

    private Rectangle getLeft(){

        return new Rectangle(posX, posY + 5, player.getWidth() / 4, player.getHeight() - 10);
    }

    private Rectangle getRight(){

        return new Rectangle(posX + (player.getWidth() / 4) * 3, posY + 5, player.getWidth() / 4, player.getHeight() - 10);
    }

    private Rectangle getDown(){

        return new Rectangle(posX + 10, posY + (player.getHeight() / 4) * 3, player.getWidth() - 20, player.getHeight() / 4);
    }

    private Rectangle getUp(){

        return new Rectangle(posX + 10, posY, player.getWidth() - 20, player.getHeight() / 6);
    }



    private void keyboardInit(){

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_D);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_A);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent jumpPressed = new KeyboardEvent();
        jumpPressed.setKey(KeyboardEvent.KEY_SPACE);
        jumpPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent rightReleased = new KeyboardEvent();
        rightReleased.setKey(KeyboardEvent.KEY_D);
        rightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent leftReleased = new KeyboardEvent();
        leftReleased.setKey(KeyboardEvent.KEY_A);
        leftReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


        keyboard.addEventListener(rightPressed);
        keyboard.addEventListener(leftPressed);
        keyboard.addEventListener(jumpPressed);


        keyboard.addEventListener(rightReleased);
        keyboard.addEventListener(leftReleased);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent){

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_D) {

            if(!hurt) {
                if (velX <= maxVel) {
                    velX += 4;
                }
            }
        }
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_A) {

            if(!hurt) {
                if (velX >= -maxVel) {

                    velX += -4;
                }
            }
        }
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            if (!hurt) {
                if (jumping == false && falling == false) {

                    jumpSound.play(true);
                    velY -= 18;
                    jumping = true;
                }
            }
        }
        }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent){

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_D) {

                velX = 0;
        }
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_A) {

            velX = 0;
        }

    }
}
