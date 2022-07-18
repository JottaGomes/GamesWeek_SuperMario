package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Chars extends GameObject {

    private Text ch;
    private int num;
    private Picture heart;
    private Picture coin;

    public Chars(int x, int y, String string, Color color, int sizeX, int sizeY){
        this.posX = x;
        this.posY= y;
        this.ch = new Text (x, y, string);
        render(color, sizeX, sizeY);

    }

    public Chars (int x, int y, int coins, Color color, int sizeX, int sizeY){
        this.posX = x;
        this.posY = y;
        num = coins;
        Integer numInt = num;
        String coinsString = numInt.toString();
        this.ch = new Text(x,y,coinsString);
        render(color, sizeX, sizeY);
        heart = new Picture(675, 50, "resources/heart.png");
        heart.draw();
        coin = new Picture(250, 50, "resources/pixilart-drawing (1).png");
        coin.draw();
    }

    public Text getCh(){
        return ch;
    }
    public void render(Color color,int sizeX, int sizeY) {

        ch.draw();
        ch.grow(sizeX,sizeY);
        ch.setColor(color);

    }
    public void render(){
    }

    public void setNum(int num){
        this.num = num;
        Integer numInt = num;
        String stringNum = numInt.toString();
        ch.setText(stringNum);
    }

   public int getNum (){
        return num;
    }

    @Override
    public void tick() {

    }
}
