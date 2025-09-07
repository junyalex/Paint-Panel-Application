# Paint Application

A paint application built with JavaFX as part of CSC207H5 (Software Design) coursework at Universtiy of Toronto. This project allows users to draw various shapes, manipulate them, and customize their appearance.

# Development Team 
This project was developed by a team of 4 students using Agile methodology with Scrum framework. The team maintained detailed records of daily activities and sprint progress throughout the development cycle
, ensuring transparent communication and efficient project management. 

Sprint-based Development: Organized work into weekly sprints with clear goals and deliverables
Daily Standups: Team members recorded daily progress, blockers, and next steps
Weekly Sprint Reviews: Regular meetings to review completed work and plan upcoming features


## Features

*   **Drawing Tools**:
    *   Draw various shapes including Circles, Ovals, Rectangles, Squares, Triangles, Polylines and Scrrible.
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

*   **Model-View-Controller (MVC)**: The application is structured to separate the data, user interface, and control logic.
*   **Command**: All drawing and editing actions are implemented as command objects (e.g., `DrawCircleCommand`, `CopyCommand`). This encapsulates each request as an object, enabling the Undo/Redo functionality.
*   **Strategy**: The drawing logic for each shape is handled by a specific strategy (e.g., `CircleDrawStrategy`, `RectangleDrawStrategy`). This allows the drawing algorithm to be selected at runtime.
*   **Factory**: A `ShapeFactory` is used to create shape objects, decoupling the client code from concrete shape classes.

## Project Structure

The source code is organized as follows:

*   `paint`: The main package containing the `Paint` application entry point.
*   `paint/shape`: Defines all the geometric shapes (`Circle`, `Rectangle`, etc.).
*   `paint/command`: Contains all the command classes for actions like drawing, cutting, and pasting.
*   `paint/strategy`: Contains the drawing strategies for each shape.
*   `paint/panel`: Contains the UI components for the application.
*   `paint/eventhandler`: Contains event handlers for UI interactions.

## Learning Outcomes & Technical Skills
__Team Collaboration & Project Management__:

- Scrum methodology implementation with sprint planning and daily standups.  
- Git workflow management and merge conflict resolution.

__Software Design & Architecture__:

- Object-Oriented Programming (OOP) principles in Java.
- Design pattern implementation (MVC, Command, Strategy, Factory).
- Clean code practices and maintainable architecture.

__Technical Development__:

- JavaFX GUI development and event handling.
- Version control best practices with Git.
