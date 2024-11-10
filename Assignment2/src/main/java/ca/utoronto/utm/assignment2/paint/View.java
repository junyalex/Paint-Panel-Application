package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class View implements EventHandler<ActionEvent> {

        private PaintModel paintModel;
        private PaintPanel paintPanel;
        private ShapeChooserPanel shapeChooserPanel;
        private UndoRedoPanel undoRedoPanel;
        private ColorChooserPanel colorChooserPanel;

        private CopyEventHandler copyEventHandler = new CopyEventHandler();
        private PasteEventHandler pasteEventHandler = new PasteEventHandler();
        private CutEventHandler cutEventHandler = new CutEventHandler();

        public View(PaintModel model, Stage stage) {
            this.paintModel = model;
            this.undoRedoPanel = new UndoRedoPanel(this);
            this.paintPanel = new PaintPanel(this.paintModel);
            this.colorChooserPanel = new ColorChooserPanel(this, this.paintModel);
            this.shapeChooserPanel = new ShapeChooserPanel(this);

            VBox vBox = new VBox(10);
            vBox.getChildren().addAll(this.shapeChooserPanel,
                    this.undoRedoPanel, this.colorChooserPanel);
            vBox.setPadding(new Insets(5, 5, 5, 5));

            BorderPane root = new BorderPane();
            root.setTop(createMenuBar());
            root.setCenter(this.paintPanel);
            root.setLeft(vBox);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(paintPanel);
            paintPanel.widthProperty().bind(stackPane.widthProperty());
            paintPanel.heightProperty().bind(stackPane.heightProperty());

            root.setCenter(stackPane);

            Scene scene = new Scene(root);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.setScene(scene);
            stage.setTitle("Paint");
            stage.show();
        }

        public PaintModel getPaintModel() {
                return this.paintModel;
        }

        // ugly way to do this?
        public void setMode(String mode){
            this.paintPanel.setMode(mode);
        }
        private MenuBar createMenuBar() {

                MenuBar menuBar = new MenuBar();
                Menu menu;
                MenuItem menuItem;

                // A menu for File

                menu = new Menu("File");

                menuItem = new MenuItem("New");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Open");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Save");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Exit");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                // Another menu for Edit

                menu = new Menu("Edit");

                menuItem = new MenuItem("Cut");
                menuItem.setOnAction(cutEventHandler);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Copy");
                menuItem.setOnAction(copyEventHandler);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Paste");
                menuItem.setOnAction(pasteEventHandler);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);


                // Style Selector
                menu = new Menu("Style");
                FillStyleEventHandler fillStyleEventHandler = new FillStyleEventHandler();

                Menu fillStyle = new Menu("Fill Style");
                MenuItem filledStyle = new MenuItem("Filled");
                filledStyle.setOnAction(fillStyleEventHandler);
                MenuItem outlinedStyle = new MenuItem("Outlined");
                outlinedStyle.setOnAction(fillStyleEventHandler);
                fillStyle.getItems().addAll(filledStyle, outlinedStyle);

                Menu thicknessStyle = new Menu("Thickness");
                // you can add each thickness menu here
                ThicknessEventHandler thicknessEventHandler = new ThicknessEventHandler();
                menuItem = new MenuItem("1");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("2");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("3");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("4");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("5");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("6");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("7");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("8");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("9");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menuItem = new MenuItem("10");
                menuItem.setOnAction(thicknessEventHandler);
                thicknessStyle.getItems().add(menuItem);

                menu.getItems().addAll(fillStyle, thicknessStyle);

                menuBar.getMenus().add(menu);



                return menuBar;
        }


        @Override
        public void handle(ActionEvent event) {
                System.out.println(((MenuItem) event.getSource()).getText());
                String command = ((MenuItem) event.getSource()).getText();
                System.out.println(command);
                if (command.equals("Exit")) {
                        Platform.exit();
                }
        }

}
