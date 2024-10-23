package ca.utoronto.utm.assignment2.paint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<>();
        private ArrayList<Square> squares=new ArrayList<Square>();
        private ArrayList<Scribble> scibbles = new ArrayList<>();
        private ArrayList<Shape> shapes = new ArrayList<>();

        public void addShape(Shape s){
                shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Shape> getShape(){
            return shapes;
        }



        //////////////////////////////Im leaving these to let the code run, but we can delete after///

        public void addPoint(Point p){
                this.points.add(p);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Point> getPoints(){
                return points;
        }

        public void addCircle(Circle c){
                this.circles.add(c);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Circle> getCircles(){
                return circles;
        }


        public void addRectangle(Rectangle r){
                this.rectangles.add(r);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Rectangle> getRectangles(){
                return rectangles;
        }

        public void addSquare(Square s){
                this.squares.add(s);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Square> getSquares(){
                return squares;
        }
        public void addScribble(Scribble s){
                this.scibbles.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Scribble> getScribbles(){
                return scibbles;
        }
}
