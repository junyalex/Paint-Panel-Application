package ca.utoronto.utm.assignment2.paint;

public class FillStyleManager{
    private static FillStyleManager instance;
    private String currentStyle = "Outlined";

    private FillStyleManager() {}

    public static FillStyleManager getInstance() {
        if (instance == null) {
            instance = new FillStyleManager();
        }
        return instance;
    }

    public String getStyle() {
        return currentStyle;
    }

    public void setStyle(String style) {
        this.currentStyle = style;
    }
}
