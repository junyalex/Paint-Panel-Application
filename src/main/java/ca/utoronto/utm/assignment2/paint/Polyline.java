package ca.utoronto.utm.assignment2.paint;
import java.util.List;

public class Polyline extends Shape{

    private List<Point> points;

    public Polyline(List<Point> points){
        this.points = points;
    }

    public List<Point> getPoints(){
        return points;
    }

    public void addPoint(Point point){
        points.add(point);
    }

    @Override
    public DrawStrategy getDrawStrategy() {
        return new PolylineDrawStrategy();
    }
}
