package ca.utoronto.utm.assignment2.paint;

public class Point {
        double x, y; // Available to our package
        Point(double x, double y){
                this.x=x; this.y=y;
        }

        public double getX() {
                return x;
        }

        public void setX(double x) {
                this.x = x;
        }

        public double getY() {
                return y;
        }

        public void setY(double y) {
                this.y = y;
        }
}
