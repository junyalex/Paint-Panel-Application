package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PaintModel extends Observable {
        private Stack<Command> commandHistory = new Stack<>();
        private Stack<Command> undoHistory = new Stack<>();
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<Shape> PreviewShapes = new ArrayList<>();
        private Shape selectedShape = null;
        private Shape toBePasted = null;
        public static int toBePastedStack = 5;
        public ArrayList<Shape> getShapes() {
                return shapes;
        }

        public void executeCommand(Command command) {
                command.execute();
                commandHistory.push(command);
                undoHistory.clear();
                setChanged();
                notifyObservers();
        }

        public void undo() {
                if (!commandHistory.isEmpty()) {
                        Command last = commandHistory.pop();
                        last.undo();
                        undoHistory.push(last);
                        setChanged();
                        notifyObservers();
                }
        }

        public void redo() {
                if (!undoHistory.isEmpty()) {
                        Command last = undoHistory.pop();
                        last.execute();
                        commandHistory.push(last);
                        setChanged();
                        notifyObservers();
                }
        }

        public void addShape(Shape s){
                shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public void removeShape( ){
                shapes.removeLast();
                this.setChanged();
                this.notifyObservers();
        }

        public void addShapePreview(Shape s){
                PreviewShapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public void setSelectedShape(Shape s){
                this.selectedShape = s;
                this.setChanged();
                this.notifyObservers();
        }
        public Shape getSelectedShape(){
                return this.selectedShape;
        }

        public void setToBePasted(Shape s){
                this.toBePasted = s;
        }
        public Shape getToBePasted(){
                return this.toBePasted;
        }

        public void drawAllShapes(GraphicsContext g2d) {
                for (Shape shape : shapes) {
                        String currStyle = shape.getFillStyle();
                        shape.getDrawStrategy().draw(shape, g2d, currStyle);
                }

                for (Shape tempShape : PreviewShapes) {
                        String currStyle = tempShape.getFillStyle();
                        tempShape.getDrawStrategy().draw(tempShape, g2d, currStyle);
                }
                PreviewShapes.clear();
        }
}
