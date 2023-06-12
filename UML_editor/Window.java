package UML_editor;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Window extends JFrame{
    private Tool_bar tool_bar;
    private Canvas canvas;
    private Menu menu;

    public Window(){
        canvas = Canvas.get_canvas();
        tool_bar = new Tool_bar();
        menu = new Menu();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menu, BorderLayout.NORTH);
        getContentPane().add(tool_bar, BorderLayout.WEST);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }
}
