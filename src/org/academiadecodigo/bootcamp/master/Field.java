package org.academiadecodigo.bootcamp.master;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Field {

    public static final int PADDING = 10;

    private int width;
    private int height;
    private Picture field;

    public Field(int width, int height){

        this.width = width;
        this.height = height;
        field = new Picture(PADDING, PADDING, "resources/sky.png");
        render();
    }

    private void render(){
        field.draw();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
