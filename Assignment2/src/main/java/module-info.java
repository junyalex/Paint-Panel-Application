module ca.utoronto.utm.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens ca.utoronto.utm.assignment2 to javafx.fxml;
    exports ca.utoronto.utm.assignment2.scribble;
    exports ca.utoronto.utm.assignment2.paint;
    exports ca.utoronto.utm.assignment2.paint.shape;
    exports ca.utoronto.utm.assignment2.paint.command;
    exports ca.utoronto.utm.assignment2.paint.strategy;
    exports ca.utoronto.utm.assignment2.paint.eventhandler;
    exports ca.utoronto.utm.assignment2.paint.panel;


}