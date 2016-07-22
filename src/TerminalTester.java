import models.Table;
import ui.Terminal;
import ui.UserInterface;

/**
 * Created by Anvar on 21.07.16.
 */
public class TerminalTester {

    public static void main(String[] args) {
        UserInterface terminal = new Terminal();

//        terminal.showMenu();
//        terminal.showMessage("Message from Controller");
//        terminal.showTable(new Table());
        terminal.showMoveTypeView();
    }

}
