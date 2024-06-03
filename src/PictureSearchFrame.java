import javax.swing.*;

public class PictureSearchFrame extends JFrame {
    private static String filePath;

    public PictureSearchFrame() {
        System.out.println("Filename Required");
    }
    public PictureSearchFrame(String Filename) {
        filePath = Filename;
        PictureList newPictureList = new PictureList(Filename);
        add(new PictureSearchPanel(newPictureList));
        setSize(800, 800);
        setTitle("Simple Picture Search");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setNewFilePath(String newFilePath) { filePath = newFilePath; }
    public static String getFilePath() { return filePath; }
}
