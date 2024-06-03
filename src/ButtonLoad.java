import javax.swing.*;
import java.io.File;

public class ButtonLoad {
    private File selectedFile;
    public ButtonLoad() {
        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
        }
    }

    public File getSelectedFile() { return this.selectedFile; }

    public String getSelectedAbsolutePath() { return this.selectedFile.getAbsolutePath(); }
}
