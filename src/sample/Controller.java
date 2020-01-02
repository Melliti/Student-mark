package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public TextField tName;
    public TextField tMarks;

    public TextArea texta;
    @FXML
    private int submitStudent () {
        if (tMarks.getText().equals("") || tName.getText().equals(""))
            return (-1);
        texta.appendText(tName.getText() + "," );
        texta.appendText(tMarks.getText() + "\n" );
        return (0);
    }
}
