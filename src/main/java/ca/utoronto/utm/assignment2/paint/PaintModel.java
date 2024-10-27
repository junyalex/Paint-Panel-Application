package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PaintModel extends Observable {
        private Stack<Command> commandHistory = new Stack<>();
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<Shape> PreviewShapes = new ArrayList<>();

        public void executeCommand(Command command) {
                command.execute();
                commandHistory.push(command);
                setChanged();
                notifyObservers();
        }

        public Stack<Command> getCommandHistory() {
                return commandHistory;
        }

        public void addShape(Shape s){
                shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public void addShapePreview(Shape s){
                PreviewShapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        public void drawAllShapes(GraphicsContext g2d) {
                for (Shape shape : shapes) {
                        shape.getDrawStrategy().draw(shape, g2d);
                }

                for (Shape tempShape : PreviewShapes) {
                        tempShape.getDrawStrategy().draw(tempShape, g2d);
                }
                PreviewShapes.clear();
        }
}
