package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
        private Point centre;
        private double radius;
        private Color color;

        public Circle(Point centre, int radius){
                this.centre = centre;
                this.radius = radius;
                this.color = Color.YELLOW;
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

        public Color getColor() {return color; }

        public void setColor(Color color) {this.color = color; }

        @Override
        public DrawStrategy getDrawStrategy() {
                return new CircleDrawStrategy();
        }
}
