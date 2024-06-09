import javax.swing.*;
import java.awt.*;

public class PicturePanel extends JPanel {
    private PictureSection[] pictureSectionList = new PictureSection[30];
    private int pictureSectionListCount = 0;
    private PictureList pictureList;
    private JPanel scrollablePanel;

    public PicturePanel() {
        System.out.println("No PictureListInfo Here");
    }

    public PicturePanel(PictureList picList) {
        this.pictureList = picList;
        scrollablePanel = new JPanel(new GridLayout(0, 1));

        for (int i = 0; i < picList.getSize(); i++) {
            pictureSectionList[pictureSectionListCount++] = new PictureSection(picList.get(i));
            scrollablePanel.add(pictureSectionList[pictureSectionListCount - 1]);
        }
        add(scrollablePanel);
    }

    public PictureSection[] getPictureSectionlist() { return this.pictureSectionList; }
    public int getPictureSectionListCount() { return this.pictureSectionListCount; }
    public PictureList getPictureList() { return this.pictureList; }
}
