package ca.utoronto.utm.assignment2.paint;
import java.util.ArrayList;
import java.util.List;

public class Polyline extends Shape{

    private ArrayList<Point> points;
    public static ArrayList<Point> last_points = new ArrayList<>();
    public static Boolean newPoly = true;

    public Polyline(){
        this.points = new ArrayList<>();
    }

    public ArrayList<Point> getPoints(){
        return this.points;
    }

    public void addPoint(Point point){
        this.points.add(point);
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new PolylineDrawStrategy();
    }


}
