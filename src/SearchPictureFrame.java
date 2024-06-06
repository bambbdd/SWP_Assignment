import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPictureFrame extends JFrame {
    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextArea tagsTextArea;
    private JTextArea commentsTextArea;
    private JTextField stuffTypeTextField;
    private JTextField stuffNameTextField;
    private JTextField stuffTagsTextField;
    private JTextField generalSearchTextField;

    public SearchPictureFrame() {
        setSize(570, 400);
        setTitle("Search Picture");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel timeSearchPanel = new JPanel(new GridLayout(2, 3));
        timeSearchPanel.setBorder(BorderFactory.createTitledBorder("Time Search"));

        timeSearchPanel.add(new JLabel("From"));
        fromTextField = new JTextField(20);
        timeSearchPanel.add(fromTextField);
        timeSearchPanel.add(new JLabel("yyyy-MM-dd_HH:mm:ss"));
        timeSearchPanel.add(new JLabel("To"));
        toTextField = new JTextField(20);
        timeSearchPanel.add(toTextField);


        JPanel keywordSearchPanel = new JPanel(new BorderLayout());
        keywordSearchPanel.setBorder(BorderFactory.createTitledBorder("Keyword Search"));

        JPanel keywordCenterPanel = new JPanel(new GridLayout(1, 2));

        JPanel TagsCommentsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        TagsCommentsPanel.add(new Label("Tags"));
        tagsTextArea = new JTextArea();
        TagsCommentsPanel.add(tagsTextArea);
        TagsCommentsPanel.add(new Label("Comments"));
        commentsTextArea = new JTextArea();
        TagsCommentsPanel.add(commentsTextArea);
        keywordCenterPanel.add(TagsCommentsPanel);

        JPanel stuffPanel = new JPanel();

        stuffPanel.setLayout(new GridLayout(0, 1, 5, 5));

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        typePanel.add(new JLabel("Type "));
        stuffTypeTextField = new JTextField(23);
        typePanel.add(stuffTypeTextField);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        namePanel.add(new JLabel("Name"));
        stuffNameTextField = new JTextField(23);
        namePanel.add(stuffNameTextField);

        JPanel tagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        tagsPanel.add(new JLabel("Tags  "));
        stuffTagsTextField = new JTextField(23);
        tagsPanel.add(stuffTagsTextField);

        stuffPanel.add(typePanel);
        stuffPanel.add(namePanel);
        stuffPanel.add(tagsPanel);

        stuffPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keywordCenterPanel.add(stuffPanel);

        JPanel keywordBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        keywordBottomPanel.add(new JLabel("General Search"));
        generalSearchTextField = new JTextField(10);
        keywordBottomPanel.add(generalSearchTextField);

        keywordSearchPanel.add(keywordBottomPanel, BorderLayout.SOUTH);
        keywordSearchPanel.add(keywordCenterPanel, BorderLayout.CENTER);


        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton andSearch = new JButton("AND SEARCH");
        btnPanel.add(andSearch);
        JButton orSearch = new JButton("OR SEARCH");
        btnPanel.add(orSearch);
        JButton close = new JButton("CLOSE");
        btnPanel.add(close);

        andSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        orSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(timeSearchPanel, BorderLayout.NORTH);
        mainPanel.add(keywordSearchPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public String getFromTextField() { return this.fromTextField.getText(); }
    public String getToTextField() { return this.toTextField.getText(); }
    public String getTagsTextArea() { return this.tagsTextArea.getText(); }
    public String getCommentsTextArea() { return this.commentsTextArea.getText(); }
    public String getStuffTypeTextField() {return this.stuffTypeTextField.getText(); }
    public String getStuffNameTextField() { return this.stuffNameTextField.getText(); }
    public String getStuffTagsTextField() { return this.stuffTagsTextField.getText(); }
    public String getGeneralSearchTextField() { return this.generalSearchTextField.getText(); }
}
