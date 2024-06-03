import javax.swing.*;
import java.awt.*;

public class StuffPanel extends JPanel {
    public StuffPanel() {
        System.out.println("No StuffInfo Here");
    }
    public StuffPanel(Stuff[] stuffs) {
        int cnt = 0;

        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(400, 200));
        JPanel scrollablePanel = new JPanel(new GridLayout(0, 1));

        while (stuffs[cnt] != null) {
            scrollablePanel.add(new StuffSection(stuffs[cnt]));
            cnt++;
        }

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        add(scrollPane);
    }

    public void addStuffInfo(StuffSection newPanel) {
        add(newPanel);
    }
}
