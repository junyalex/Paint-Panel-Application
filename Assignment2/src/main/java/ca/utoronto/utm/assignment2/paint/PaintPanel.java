package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import ca.utoronto.utm.assignment2.scribble.ScribblePanel;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode="Circle";
    private PaintModel model;

    double start_x ,start_y; // instance used for saving first location of x,y when mouse clicked
    private Circle circle; // This is VERY UGLY, should somehow fix this!! (fixed)
    private Rectangle rectangle;
    private Square square;
    private Scribble scribble;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.mode=mode;
        System.out.println(this.mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
        switch(this.mode){
            case "◯":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                     Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                        this.circle=new Circle(centre, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.circle != null) {
                        double deltaX = mouseEvent.getX() - this.circle.getCentre().x;
                        double deltaY = mouseEvent.getY() - this.circle.getCentre().y;
                        double radius = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
                        this.circle.setRadius(radius);
                        this.model.addCircle(this.circle);
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.circle!=null){
                        // Problematic notion of radius and centre!!
                        System.out.println("Added Circle");
                        this.circle=null;
                    }
                }

                break;

            case "▭": // Drag to draw Rectangle
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)){
                System.out.println("Started Rectangle");
                Point corner1 = new Point(mouseEvent.getX(), mouseEvent.getY()); // left_top
                Point corner2 = new Point(0, 0);   // right_bot
                this.rectangle = new Rectangle(corner1, corner2);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)){

                    this.rectangle.setCorner2(new Point(mouseEvent.getX(), mouseEvent.getY()));

                    this.model.addRectangle(this.rectangle);

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)){
                    this.rectangle.setCorner2(new Point(mouseEvent.getX(), mouseEvent.getY()));
                    this.rectangle = null;
                }

                break;

            case "□":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Square");
                    Point corner = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.square=new Square(corner, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(this.square != null) {
                        double deltaX = mouseEvent.getX() - this.square.getCorner().x;
                        double deltaY = mouseEvent.getY() - this.square.getCorner().y;
                        double dim = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
                        this.square.setDim(dim);
                        this.model.addSquare(this.square);
                    }
                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.square!=null){
                        // Problematic notion of radius and centre!!
                        System.out.println("Added Square");
                        this.square=null;
                    }
                }

                break;

            case "Squiggle (〜)":
                GraphicsContext gc = this.getGraphicsContext2D();
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    ArrayList<Point> point_list = new ArrayList<>();
                    point_list.add(startPoint);
                    this.scribble = new Scribble(point_list);
                }
                else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED){
                    ArrayList<Point> points = this.scribble.points;
                    double curx = mouseEvent.getX();
                    double cury = mouseEvent.getY();
                    double lastx = points.getLast().x;
                    double lasty = points.getLast().y;


                    gc.strokeLine(lastx, lasty, curx, cury);
                    points.add(new Point(curx, cury));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    double currx = mouseEvent.getX();
                    double curry = mouseEvent.getY();

                    this.scribble.points.add(new Point(currx, curry));
                    Point p = this.scribble.getLastPoint();
                    gc.strokeLine(p.x, p.y, currx, curry);
                    this.model.addScribble(this.scribble);
                    this.scribble = null;
                    }
                break;
            case "Polyline (└───┐)": break;
            default: break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
                GraphicsContext g2d = this.getGraphicsContext2D();
                g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
                ArrayList<Shape> shapes = this.model.getShape();



                // What we need to do /////////////////////////////////
                for (Shape s : shapes){
                    // printshape()
                    // need to implement something to print all shapes by 1 code
                    // instead of things at the bottom
                }


                // Draw Lines
                ArrayList<Point> points = this.model.getPoints();

                g2d.setFill(Color.RED);
                for(int i=0;i<points.size()-1; i++){
                        Point p1=points.get(i);
                        Point p2=points.get(i+1);
                        g2d.strokeLine(p1.x,p1.y,p2.x,p2.y);
                }

                // Draw Circles
                ArrayList<Circle> circles = this.model.getCircles();

                g2d.setFill(Color.GREEN);
                for(Circle c: this.model.getCircles()){
                        double x = c.getCentre().x;
                        double y = c.getCentre().y;
                        double radius = c.getRadius();
                        g2d.fillOval(x-radius, y-radius, radius*2, radius*2);
                }

                // Draw Rectangles
                ArrayList<Rectangle> rectangles = this.model.getRectangles();
                for(Rectangle r: this.model.getRectangles()){
                    double corner_x = Math.min(r.getCorner1().getX(), r.getCorner2().getX());
                    double corner_y = Math.min(r.getCorner1().getY(), r.getCorner2().getY());
                    double width = r.getWidth();
                    double height = r.getHeight();
                    g2d.fillRect(corner_x, corner_y, width, height);

                }

                // Draw Squares
                ArrayList<Square> squares = this.model.getSquares();

                g2d.setFill(Color.RED);
                for(Square s: this.model.getSquares()){
                    double x = s.getCorner().x;
                    double y = s.getCorner().y;
                    double dim = s.getDim();
                    g2d.fillRect(x, y, dim, dim);
                }

                // Draw scribbles
                g2d.setFill(Color.GREEN);
                ArrayList<Scribble> scribbles = new ArrayList<>();
                for (Scribble s: this.model.getScribbles()){
                    ArrayList<Point> points1 = s.points;

                    for (int i = 0; i < points1.size() - 1 ; i++){
                        Point p1 = points1.get(i);
                        Point p2 = points1.get(i+1);

                        g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }

                }
    }

