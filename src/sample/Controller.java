package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;

public class Controller {
    public TextField tName;
    public TextField tMarks;
    public TextArea texta;

    private int studentExist(String newName) {
        String[] list = texta.getText().split("\n");

        for (String s : list) {
            String[] savedName = s.split(",");
            if (savedName[0].equals(newName))
                return (0);
        }
        return (1);
    }

    @FXML
    private int submitStudent () {
        if (tMarks.getText().equals("") || tName.getText().equals(""))
            return (-1);
        if (studentExist(tName.getText()) == 1) {
            texta.appendText(tName.getText() + "," );
            texta.appendText(tMarks.getText() + "\n" );
        }
        return (0);
    }

    @FXML
    private int exportCSV() {
        if (texta.getText().equals(""))
            return (-1);
        Window saveWindow = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(saveWindow);
        if (file != null)
            saveFile(texta.getText(), file);
        return (0);
    }

    @FXML
    private int importCSV() {
        Window importWindow = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(importWindow);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                texta.appendText(line + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (0);
    }

    private void saveFile(String data, File file) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
