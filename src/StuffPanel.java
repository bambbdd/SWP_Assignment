import javax.swing.*;
import java.awt.*;

public class StuffPanel extends JPanel {
    private StuffSection[] stuffSections = new StuffSection[20];
    private int stuffSectionsCount = 0;
    public StuffPanel() {
        System.out.println("No StuffInfo Here");
    }
    public StuffPanel(Stuff[] stuffs) {
        int cnt = 0;

        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(400, 200));
        JPanel scrollablePanel = new JPanel(new GridLayout(0, 1));

        while (stuffs[cnt] != null) {
            StuffSection newStuffSection = new StuffSection(stuffs[cnt]);
            scrollablePanel.add(newStuffSection);
            cnt++;

            stuffSections[stuffSectionsCount++] = newStuffSection;
        }

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        add(scrollPane);
    }

    public void addStuffInfo(StuffSection newPanel) {
        add(newPanel);
    }
    public String getStuffInfo() {
        String stuffInfo = "<";
        for(int i = 0 ; i < stuffSectionsCount; i++) {
            stuffSections[i].setStuff();
            stuffInfo = stuffInfo + stuffSections[i].getStuff();
        }
        stuffInfo = stuffInfo + ">";
        return stuffInfo.replaceAll(" ", "");
    }

    public StuffSection[] getStuffSectionList() { return stuffSections; }
}
