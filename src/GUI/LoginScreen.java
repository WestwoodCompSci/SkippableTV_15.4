package GUI;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;


public class LoginScreen{

public static void main(String[] args) {

    
    final ScreenFunc gui = new ScreenFunc();
    
    // add the action listener to the button
            // notice how we reference the gui here by running the methods in the
            // gui class
            // this action listener could be created in the gui
            // class but in general you don't want to do that because actions will 
            // involve multiple classes
    gui.setButtonActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            gui.setText((gui.isBoxChecked() ? "yes" : "no"));
        }
    });
}
}