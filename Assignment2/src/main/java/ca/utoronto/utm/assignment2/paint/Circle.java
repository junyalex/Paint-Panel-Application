package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
        private Point centre;
        private double radius;

        public Circle(Point centre, int radius){
                this.centre = centre;
                this.radius = radius;
        }

        public Point getCentre() {
                return centre;
        }

        public void setCentre(Point centre) {
                this.centre = centre;
        }

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }
        @Override
        public DrawStrategy getDrawStrategy() {
                return new CircleDrawStrategy();
        }
}
