package gui.sections;

import java.awt.Color;

public interface IMergable {
    public static Color DIFFERENT_COLOR = Color.yellow;
    public static Color SAME_COLOR = Color.white;

    public void setInputEnabled(boolean enabled);
    public void compareToSymmetrical(IMergable other);

}