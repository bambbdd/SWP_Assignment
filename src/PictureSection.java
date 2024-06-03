import javax.swing.*;
import java.awt.*;

public class PictureSection extends JPanel {
    public PictureSection() {
        System.out.println("No PictureInfo Here");
    }

    public PictureSection(Picture picture) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel(picture.getStrTimestamp() + "        "), BorderLayout.WEST);
        topPanel.add(new TextField(picture.getTag(), 20), BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        String[] imageName = picture.getImage().split(";");
        JLabel iconLabel = new JLabel(scaleImage(imageName[1], 250, 250));
        centerPanel.add(iconLabel, BorderLayout.WEST);
        centerPanel.add(new StuffPanel(picture.getStuff()), BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(new TextField(picture.getComment()), BorderLayout.SOUTH);
    }

    private ImageIcon scaleImage(String imgName, int width, int height) {
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

}
