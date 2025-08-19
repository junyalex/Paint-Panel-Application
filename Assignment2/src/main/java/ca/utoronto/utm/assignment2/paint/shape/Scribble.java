package ca.utoronto.utm.assignment2.paint.shape;

import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.ScribbleDrawStrategy;

import java.util.ArrayList;
/**
 * Represents a scribble, which is a freehand drawing made up of a series of connected points.
 * A scribble is a subclass of {@link Shape} and provides additional functionality
 * for managing a list of points and drawing the scribble on a canvas.
 */
public class Scribble extends Shape {

    public ArrayList<Point> points;

    /**
     * Constructor for creating a scribble from a list of points.
     *
     * @param points the list of points that form the scribble
     */
    public Scribble(ArrayList<Point> points) {
        super();
        this.points = points;
    }
    /**
     * Gets the last point in the scribble.
     *
     * @return the last point in the list of points
     */
    public Point getLastPoint(){
        return this.points.getLast();
    }

    /**
     * Gets the list of points that make up the scribble.
     *
     * @return the list of points in the scribble
     */
    public ArrayList<Point> getPoints(){
        return this.points;
    }

    /**
     * Checks if a given point is inside the scribble. A point is considered inside if it
     * lies on one of the line segments that make up the scribble, within a given offset
     * (which accounts for the thickness of the scribble).
     *
     * @param selectPoint the point to check if it is inside the scribble
     * @return true if the point is on any of the line segments of the scribble, false otherwise
     */
    @Override
    public boolean contains(Point selectPoint) {
        double offset = this.getThickness()/2;

        for (int i=0; i<this.points.size()-1; i++) {
            Point p1 = this.points.get(i);
            Point p2 = this.points.get(i+1);

            boolean isOnSegment = isSelectedPointOnSegment(p1, p2, selectPoint, offset);
            if(isOnSegment) {return true;}
        }
        return false;
    }


    /**
     * Helper method to check if a given point is on a line segment defined by two points.
     * The check takes into account the thickness of the scribble by applying an offset.
     *
     * @param p1 the first point defining the segment
     * @param p2 the second point defining the segment
     * @param selectPoint the point to check
     * @param offset the tolerance for how close the point needs to be to the segment to be considered on it
     * @return true if the point is within the offset distance of the segment, false otherwise
     */
    private boolean isSelectedPointOnSegment(Point p1, Point p2, Point selectPoint, double offset) {
        // using projection formula...

        if (p1.x == p2.x && p1.y == p2.y) {
            return offset >= Math.sqrt(Math.pow(selectPoint.x - p1.x, 2) + Math.pow(selectPoint.y - p1.y, 2));
        }

        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double dSquared = dx * dx + dy * dy;

        double t = ((selectPoint.x - p1.x) * dx + (selectPoint.y - p1.y) * dy) / dSquared;
        if(t < 0 || t > 1) {return false;}

        double closestX = p1.x + t * dx;
        double closestY = p1.y + t * dy;
        double closestMagnitude = Math.sqrt(Math.pow(selectPoint.x - closestX, 2) + Math.pow(selectPoint.y - closestY, 2));

        return offset >= closestMagnitude;
    }

    /**
     * Returns the draw strategy associated with this scribble. The draw strategy defines how
     * the scribble is drawn on the canvas.
     *
     * @return the {@link DrawStrategy} to use for drawing this scribble
     */
    @Override
    public DrawStrategy getDrawStrategy() {
        return new ScribbleDrawStrategy();
    }


    /**
     * Moves the scribble by a given distance in the x and y directions. Each point in the
     * scribble is translated by the specified amounts.
     *
     * @param x the distance to move in the x direction
     * @param y the distance to move in the y direction
     */
    @Override
    public void move(double x, double y) {
        for (Point point : points) {
            point.x += x;
            point.y += y;
        }
    }
    /**
     * Creates and returns a new copy of this scribble. The new scribble has the same points,
     * color, thickness, and fill style as the original.
     *
     * @return a new {@link Scribble} object that is a copy of this one
     */
    @Override
    public Shape clone(){
        ArrayList<Point> points = new ArrayList<>();
        for (Point point : this.points) {
            points.add(new Point(point.x, point.y));
        }
        Shape clone = new Scribble(points);
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setFillStyle(this.getFillStyle());
        return clone;
    }
}
