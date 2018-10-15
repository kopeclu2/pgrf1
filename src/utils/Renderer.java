package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {
    private int color;
    BufferedImage img;

    public Renderer(BufferedImage img) {
        this.img = img;
        color = Color.RED.getRGB();
    }

    private void drawPixel(int x, int y) {
        if (x<0 || x>=800)return;
        if (y<0 || y>= 600) return;
        img.setRGB(x, y, color);
    }

    public void lineTrivial(int x1, int y1, int x2, int y2) {
        //y=kx+q
        int dx = x1 - x2; //rozdil prvni x a vystupni x
        int dy = y1 - y2; //-||-

        if (Math.abs(dx) > Math.abs(dy)) {
            //ridici osa X
            //otoceni souradnic

            if (x1 > x2) {//prohozeni promenych
                int p = x1;
                x1 = x2;
                x2 = p;
                p = y1;
                y1 = y2;
            }


            for (int x = x1; x < x2; x++) {
                float k = (float) dy / (float) dx;//zavislot x na yx o kolik se zvedne
                float q = y1 - k * x1;
                float y = k * x + q;
                drawPixel(x, (int) y);//q = pocatek y souradnice
            }


        } else {
            if (Math.abs(dy) > Math.abs(dx)) {
                //ridici osa X
                //otoceni souradnic

                if (y1 > y2) {//prohozeni promenych
                    int p = y1;
                    y1 = y2;
                    y2 = p;
                    p = x1;
                    x1 = x2;
                }
                float k = (float) dx / (float) dy;//zavislot x na yx o kolik se zvedne
                for (int y = y1; y < y2; y++) {
                    int x = x1 + (int) (k * (y - y1));
                    drawPixel(x, y);//q = pocatek y souradnice
                }


            }


        }


    }

    public void lineDDA(int x1, int y1, int x2, int y2) {

        int dx,dy,l;
        float k,G,H;
        //k smernice,  rirstek x, h irustek po ose y

        dx = x2-x1;
        dy = y2-y1;
        k = dy/(float)dx;

        //urceni idici osy
        if (Math.abs(dx)>Math.abs(dy)) {

            G=1;
            H=k;
            if (x1>x2){
                int temp = x1;
                x1=x2;
                x2=temp;
                temp = y1;
                y1=y2;
                y2=temp;
            }
        }else{

            G=1/k;
            H=1;
            if (y1>y2){
                int temp = x1;
                x1=x2;
                x2=temp;
                temp = y1;
                y1=y2;
                y2=temp;

            }

        }
        float x = x1;
        float y =y1;

        int max = Math.max(Math.abs(dx),Math.abs(dy));
        for (int i = 0; i <= max; i++) {
            drawPixel(Math.round(x),Math.round(y));
            x = x + G;
            y = y + H;

        }

    }

    public void polygon(int x1,int y1,int x2, int y2,int count){
        double x0 = x2-x1;
        double y0 = y2-y1;
        double circleRadius = 2*Math.PI;
        double step = circleRadius/(double)count;
        for (double i = 0; i < circleRadius; i+=step) {
            //dle roaci matice
            double x = x0 * Math.cos(step) + y0 * Math.sin(step);
            double y = y0 * Math.cos(step) - x0 * Math.sin(step);
            lineDDA((int)x0+x1,(int)y0+y1,(int)x+x1,(int)y+y1);

        }

    }
}