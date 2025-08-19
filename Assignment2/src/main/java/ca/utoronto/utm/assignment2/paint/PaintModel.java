package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.command.Command;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

/**
 * The PaintModel class is responsible for managing the state of the paint program, including
 * keeping track of the shapes on the canvas, handling undo/redo operations, and managing
 * selected shapes and shape previews. It extends {@link Observable} to notify observers about
 * changes in the model.
 */
public class PaintModel extends Observable {
        private Stack<Command> commandHistory = new Stack<>();
        private Stack<Command> undoHistory = new Stack<>();
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<Shape> PreviewShapes = new ArrayList<>();
        private Shape selectedShape = null;
        private Shape toBePasted = null;
        public static int toBePastedStack = 5;

        /**
         * Returns the list of shapes currently in the model.
         *
         * @return the list of shapes
         */
        public ArrayList<Shape> getShapes() {
                return shapes;
        }

        /**
         * Executes the given command, updates the command history, and notifies observers.
         *
         * @param command the command to execute
         */
        public void executeCommand(Command command) {
                command.execute();
                commandHistory.push(command);
                undoHistory.clear();
                setChanged();
                notifyObservers();
        }

        /**
         * Undoes the most recent command and notifies observers.
         */
        public void undo() {
                if (!commandHistory.isEmpty()) {
                        Command last = commandHistory.pop();
                        last.undo();
                        undoHistory.push(last);
                        setChanged();
                        notifyObservers();
                }
        }

        /**
         * Redoes the most recent undone command and notifies observers.
         */
        public void redo() {
                if (!undoHistory.isEmpty()) {
                        Command last = undoHistory.pop();
                        last.execute();
                        commandHistory.push(last);
                        setChanged();
                        notifyObservers();
                }
        }

        /**
         * Adds a shape to the model and notifies observers of the change.
         *
         * @param s the shape to add
         */
        public void addShape(Shape s){
                shapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        /**
         * Removes the last shape from the model and notifies observers of the change.
         */

        public void removeShape( ){
                shapes.removeLast();
                this.setChanged();
                this.notifyObservers();
        }

        /**
         * Adds a shape to the preview list, which is used for previewing shapes before they are added
         * to the model, and notifies observers.
         *
         * @param s the shape to preview
         */
        public void addShapePreview(Shape s){
                PreviewShapes.add(s);
                this.setChanged();
                this.notifyObservers();
        }

        /**
         * Sets the currently selected shape in the model and notifies observers.
         *
         * @param s the shape to set as selected
         */
        public void setSelectedShape(Shape s){
                this.selectedShape = s;
                this.setChanged();
                this.notifyObservers();
        }
        /**
         * Returns the currently selected shape in the model.
         *
         * @return the selected shape
         */
        public Shape getSelectedShape(){
                return this.selectedShape;
        }

        /**
         * Sets the shape that is to be pasted and notifies observers.
         *
         * @param s the shape to be pasted
         */
        public void setToBePasted(Shape s){
                this.toBePasted = s;
        }

        /**
         * Returns the shape that is to be pasted.
         *
         * @return the shape to be pasted
         */
        public Shape getToBePasted(){
                return this.toBePasted;
        }

        /**
         * Draws all shapes on the canvas, including both regular shapes and preview shapes.
         * Preview shapes are drawn temporarily and cleared afterward.
         *
         * @param g2d the GraphicsContext used to draw the shapes
         */
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
