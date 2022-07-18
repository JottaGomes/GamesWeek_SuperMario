package org.academiadecodigo.bootcamp.master;
import org.academiadecodigo.bootcamp.objects.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private Field field;
    private Handler handler;
    MenuSelection menuSelection;

    GameObject gameObject;
    private Sound sound;
    private ExecutorService soundThreadPool = Executors.newCachedThreadPool();
    private int delay;

    public void init(){

        sound = new Sound("resources/super-mario-sound-theme.wav");
        sound.setLoop(999);
        this.delay = 17;
        field = new Field(1600, 896);
        handler = new Handler(field);
        menuSelection = new MenuSelection(MenuType.START_MENU);
    }


    public void start()  {


        while (!menuSelection.isOut()) {
            System.out.println(" ");
        }
       handler.buildLevel(handler.getLevel());

            while (true) {


                menuSelection.setReset(false);

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                for (int i = 0; i < handler.getGameObjects().size(); i++) {

                    handler.getGameObjects().get(i).tick();


                    if (handler.getGameObjects().get(i) instanceof Player && ((Player) handler.getGameObjects().get(i)).isWinner()) {
                        new MenuSelection(MenuType.NEXTLEVEL_MENU);
                        handler.setLevel(handler.getLevel() + 1);
                        while (!menuSelection.isReset()) {
                            System.out.println("");
                        }

                            for (GameObject obj : handler.getGameObjects()) {

                                if (obj instanceof Player) {

                                    ((Player) obj).getPlayer().delete();
                                }
                                if (obj instanceof Princess) {

                                    ((Princess) obj).getPrincess().delete();
                                }

                                if (obj instanceof Turtle) {

                                    ((Turtle) obj).getTurtle().delete();
                                }
                                if (obj instanceof Coins) {
                                    ((Coins) obj).getCoins().delete();
                                }
                                if (obj instanceof Block) {

                                    ((Block) obj).getBlock().delete();
                                }
                                if (obj instanceof SpecialBlock) {
                                    ((SpecialBlock) obj).getSpecialBlock().delete();

                                }
                                if (obj instanceof LavaGenerator) {
                                    for (LavaGenerator.Lava lava : ((LavaGenerator) obj).getLavaDrops()) {
                                        lava.getLava().delete();
                                    }
                                    ((LavaGenerator) obj).getLavaDrops().clear();
                                }
                                if (obj instanceof Bowser) {
                                    for(Bowser.FireBall fireBall :((Bowser) obj).getFireBalls()){
                                        fireBall.getFireBall().delete();
                                    }
                                    ((Bowser) obj).getFireBalls().clear();
                                    ((Bowser) obj).getBowser().delete();
                                }

                        }
                        handler.getLives().getCh().delete();
                        handler.getCoins().getCh().delete();
                        handler.getGameObjects().clear();

                        handler.buildLevel(handler.getLevel());
                    }

                    if (handler.getGameObjects().get(i) instanceof Player && ((Player) handler.getGameObjects().get(i)).isDead()) {
                        new MenuSelection(MenuType.GAMEOVER_MENU);
                        while (!menuSelection.isReset()) {
                            System.out.println("");
                        }
                        for (GameObject obj : handler.getGameObjects()) {

                            if (obj instanceof Player) {

                                ((Player) obj).getPlayer().delete();
                            }
                            if (obj instanceof Princess) {

                                ((Princess) obj).getPrincess().delete();
                            }

                            if (obj instanceof Turtle) {

                                ((Turtle) obj).getTurtle().delete();
                            }
                            if (obj instanceof Coins) {
                                ((Coins) obj).getCoins().delete();
                            }
                            if (obj instanceof Block) {

                                ((Block) obj).getBlock().delete();
                            }
                            if (obj instanceof SpecialBlock) {
                                ((SpecialBlock) obj).getSpecialBlock().delete();

                            }
                            if (obj instanceof LavaGenerator) {
                                for(int j = 0; j < ((LavaGenerator) obj).getLavaDrops().size(); j++){
                                    ((LavaGenerator) obj).getLavaDrops().get(j).getLava().delete();
                                }
                                ((LavaGenerator) obj).getLavaDrops().clear();
                            }
                            if (obj instanceof Bowser) {
                                for(Bowser.FireBall fireBall :((Bowser) obj).getFireBalls()){
                                    fireBall.getFireBall().delete();
                                }
                                ((Bowser) obj).getFireBalls().clear();
                                ((Bowser) obj).getBowser().delete();
                            }
                        }
                        handler.getLives().getCh().delete();
                        handler.getCoins().getCh().delete();
                        handler.getGameObjects().clear();
                       handler.buildLevel(handler.getLevel());
                    }
                }
                handler.updateGameObjects();
            }
        }
}

