import javax.swing.*;
import java.awt.*;

public class PicturePanel extends JPanel {

    public PicturePanel() {
        System.out.println("No PictureListInfo Here");
    }

    public PicturePanel(PictureList picList) {
        JPanel scrollablePanel = new JPanel(new GridLayout(0, 1));

        for (int i = 0; i < picList.size(); i++) {
            scrollablePanel.add(new PictureSection(picList.get(i)));
        }
        add(scrollablePanel);
    }
}
