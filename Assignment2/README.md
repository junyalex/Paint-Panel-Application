# Paint Application

A simple paint application built with JavaFX. This project allows users to draw various shapes, manipulate them, and customize their appearance.

## Features

*   **Drawing Tools**:
    *   Draw various shapes including Circles, Ovals, Rectangles, Squares, Triangles, and Polylines.
    *   Free-form drawing with the Scribble tool.
*   **Shape Manipulation**:
    *   Select and move shapes on the canvas.
    *   Cut, Copy, and Paste shapes.
    *   Delete selected shapes.
*   **Styling**:
    *   Choose a drawing color.
    *   Adjust the line thickness.
    *   Select a fill style (filled or outline).
*   **Editing**:
    *   Unlimited Undo and Redo functionality.

## Design Patterns

This project utilizes several key design patterns:

*   **Model-View-Controller (MVC)**: The application is structured to separate the data (`PaintModel`), the user interface (`View`), and the control logic.
*   **Command**: All drawing and editing actions are implemented as command objects (e.g., `DrawCircleCommand`, `CopyCommand`). This encapsulates each request as an object, enabling the Undo/Redo functionality.
*   **Strategy**: The drawing logic for each shape is handled by a specific strategy (e.g., `CircleDrawStrategy`, `RectangleDrawStrategy`). This allows the drawing algorithm to be selected at runtime.
*   **Factory**: A `ShapeFactory` is used to create shape objects, decoupling the client code from concrete shape classes.

## How to Build and Run

This is a Maven project. To build and run the application, you need to have Java (version 22 or higher) and Maven installed.

1.  **Clone the repository.**
2.  **Navigate to the project directory.**
3.  **Run the application using the following Maven command:**

    ```bash
    mvn clean javafx:run
    ```

## Project Structure

The source code is organized as follows:

*   `paint`: The main package containing the `Paint` application entry point.
*   `paint/shape`: Defines all the geometric shapes (`Circle`, `Rectangle`, etc.).
*   `paint/command`: Contains all the command classes for actions like drawing, cutting, and pasting.
*   `paint/strategy`: Contains the drawing strategies for each shape.
*   `paint/panel`: Contains the UI components for the application.
*   `paint/eventhandler`: Contains event handlers for UI interactions.
