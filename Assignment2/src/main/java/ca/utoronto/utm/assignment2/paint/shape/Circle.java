package ca.utoronto.utm.assignment2.paint.shape;


import ca.utoronto.utm.assignment2.paint.strategy.CircleDrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;

/***
 * A circle shape is created with a centre point and a radius.
 * It extends the abstract class Shape allowing for it to be drawn
 * moved and copied.
 */

public class Circle extends Shape{
        private Point centre;
        private double radius;

        /**
         * Constructs a circle with a center point and a radius
         *
         * @param centre the center point of the circle
         * @param radius the radius of the circle
         */
        public Circle(Point centre, double radius){
                super();
                this.centre = centre;
                this.radius = radius;
                pointsHistory.add(new Point(centre.x,centre.y));
                current = 0;
        }

        /**
         * Returns the center point of the circle
         *
         * @return the center point
         */
        public Point getCentre() {
                return centre;
        }

        /**
         * Sets the center point of the circle
         *
         * @param centre the new centre point
         */
        public void setCentre(Point centre) {
                this.centre = centre;
        }

        /**
         * Returns the radius of the circle
         *
         * @return the radius
         */
        public double getRadius() {
                return radius;
        }

        /**
         * Sets the new radius of the circle
         *
         * @param radius the new radius of the circle
         */
        public void setRadius(double radius) {
                this.radius = radius;
        }

        /**
         *Checks if a given point is within or on the line of the circle
         * returns true if the point is within or on the circle
         * returns false if the point is outside the circle
         *
         * @param point the point to check
         * @return
         */
        @Override
        public boolean contains(Point point){
                double distance = Math.sqrt(Math.pow(point.x - centre.x, 2) + Math.pow(point.y - centre.y, 2));
                return distance <= radius;
        }

        /**
         * returns the drawing strategy of the circle
         * @return a circle DrawStrategy Object
         */
        @Override
        public DrawStrategy getDrawStrategy() {
                return new CircleDrawStrategy();
        }

        /**
         * Moves the circle by x and y
         * @param x the amount to move in the x-direction
         * @param y the amount to move in the y direction
         */
        @Override
        public void move(double x , double y){
                this.centre.x += x;
                this.centre.y += y;
        }

        /**
         * Creates and returns a copy of this circle
         *
         * @return a new Circle object that is a clone of this circle
         *
         */
        @Override
        public Shape clone(){
                Shape clone = new Circle(new Point(this.centre.x,this.centre.y),this.radius);
                clone.setColor(this.getColor());
                clone.setThickness(this.getThickness());
                clone.setFillStyle(this.getFillStyle());
                return clone;
        }

        /**
         * Returns a string representation of the circle, showing the coordinates of the
         * centre and the radius
         * @return a string in the format "x,y,radius"
         */
        @Override
        public String toString() {
                return "" + this.centre.x + "," + this.centre.y + "," + this.radius;
        }

}
