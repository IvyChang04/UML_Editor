import UML_editor.Window;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args){
        Window my_window = new Window();
        my_window.setTitle("UML editor");
		my_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		my_window.setSize(1250, 750);
		my_window.setVisible(true);
    }
}
