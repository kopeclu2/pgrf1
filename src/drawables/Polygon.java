package drawables;

import utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Polygon implements Drawable {

    private List<Point> points;

    public Polygon() {
        points=new ArrayList<>();
    }
    public void addPoint(Point p){
        points.add(p);
    }

    @Override
    public void draw(Renderer renderer) {
        // TODO: 15.10.2018 -for cyklus propojit vsechny body i pvni s poslendim #
        
    }
}
