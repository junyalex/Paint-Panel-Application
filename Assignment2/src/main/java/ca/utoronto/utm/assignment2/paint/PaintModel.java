package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<>();
        private ArrayList<Square> squares=new ArrayList<Square>();
        private ArrayList<Scribble> scibbles = new ArrayList<>();
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<Oval> ovals = new ArrayList<Oval>();
        private ArrayList<Triangle> triangles = new ArrayList<>();
        private static ArrayList<Shape> temp_Shapes = new ArrayList<>();

        public void addShape(Shape s){
                shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public void addShapeTemp(Shape s){
                temp_Shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Shape> getShape(){
            return shapes;
        }

        public void drawAllShapes(GraphicsContext g2d){
                for(Shape s : shapes){
                        s.getDrawStrategy().draw(s, g2d);
                }
        }

        public void drawAllTempShapes(GraphicsContext g2d){
                for(Shape s : temp_Shapes){
                        s.getDrawStrategy().draw(s, g2d);
                }
                temp_Shapes.clear();
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

        public void addOval(Oval o){
                this.ovals.add(o);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Oval> getOval(){
                return ovals;
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
