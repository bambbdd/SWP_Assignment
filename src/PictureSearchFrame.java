import javax.swing.*;

public class PictureSearchFrame extends JFrame {
    private static String filePath;
    private static PictureList newPictureList;
    private static PictureSearchPanel newPictureSearchPanel;

    public PictureSearchFrame() {
        System.out.println("Filename Required");
    }
    public PictureSearchFrame(String Filename) {
        filePath = Filename;
        newPictureList = new PictureList(Filename);
        newPictureSearchPanel = new PictureSearchPanel(newPictureList);
        add(newPictureSearchPanel);
        setSize(800, 800);
        setTitle("Simple Picture Search");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setNewFilePath(String newFilePath) { filePath = newFilePath; }
    public static void setOriginalPictureSearchPanel() {
        newPictureList = new PictureList(filePath);
        newPictureSearchPanel.refreshPicPanel(newPictureList);
    }
    public static String getFilePath() { return filePath; }

}
