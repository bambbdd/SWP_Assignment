import javax.swing.*;
import java.awt.*;

public class PictureSection extends JPanel {
    private Picture picture;
    private JTextField tagField;
    private JTextField commentField;
    private StuffPanel stuffPanel;
    public PictureSection() {
        System.out.println("No PictureInfo Here");
    }

    public PictureSection(Picture picture) {
        this.picture = picture;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel(picture.getStrTimestamp() + "        "), BorderLayout.WEST);
        tagField = new JTextField(picture.getTag(), 20);
        topPanel.add(tagField, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        String[] imageName = picture.getImage().split(";");
        JLabel iconLabel = new JLabel(scaleImage(imageName[1], 250, 250));
        centerPanel.add(iconLabel, BorderLayout.WEST);
        stuffPanel = new StuffPanel(picture.getStuff());
        centerPanel.add(stuffPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        commentField = new JTextField(picture.getComment());
        add(commentField, BorderLayout.SOUTH);
    }

    public void setPicture() {
        picture.setTagInfo(getTagField());
        picture.setCommentInfo(getCommentField());
        picture.setStuffInfo(getStuffInfo());
    }

    public String getTagField() { return this.tagField.getText(); }
    public String getCommentField() { return this.commentField.getText(); }
    public String getStuffInfo() {
        return stuffPanel.getStuffInfo();
    }

    public String getPictureInfo() {
        return picture.getPictureInfo();
    }

    public Picture getPicture() { return this.picture; }

    private ImageIcon scaleImage(String imgName, int width, int height) {
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

}
