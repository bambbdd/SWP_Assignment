import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class StuffSection extends JPanel {
    private JTextField typeField;
    private JTextField nameField;
    private JTextField tagsField;
    private Stuff newStuff;



    public StuffSection() {
        this(new Stuff(" ; ; ; "));
    }
    public StuffSection(Stuff stuff) {

        setLayout(new GridLayout(0, 1, 5, 5));

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.typeField = new JTextField(stuff.getType(), 35);
        typePanel.add(new JLabel("Type "));
        typePanel.add(this.typeField);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.nameField = new JTextField(stuff.getName(), 35);
        namePanel.add(new JLabel("Name"));
        namePanel.add(this.nameField);

        JPanel tagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.tagsField = new JTextField(stuff.getTag(), 35);
        tagsPanel.add(new JLabel("Tags  "));
        tagsPanel.add(this.tagsField);

        add(typePanel);
        add(namePanel);
        add(tagsPanel);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public String getNewType() { return this.typeField.getText(); }
    public String getNewName() { return this.nameField.getText(); }
    public String getNewTags() { return this.tagsField.getText(); }

    public String getStuff() {
        return this.newStuff.getStuffInfo();
    }

    public void setStuff() {
        String type = this.getNewType().trim();
        String name = this.getNewName().trim();
        String tags = this.getNewTags().trim();
        String ID = StuffList.setStuffID(name);

        String trimStuff = String.format("%s;%s;%s;%s", ID, type, name, tags);
        StuffList.addStuff(trimStuff);
        this.newStuff = new Stuff(trimStuff);
    }
}
