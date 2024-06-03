import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPictureFrame extends JFrame {
    public SearchPictureFrame() {
        setSize(570, 400);
        setTitle("Search Picture");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel timeSearchPanel = new JPanel(new GridLayout(2, 3));
        timeSearchPanel.setBorder(BorderFactory.createTitledBorder("Time Search"));

        timeSearchPanel.add(new JLabel("From"));
        JTextField fromText = new JTextField(20);
        timeSearchPanel.add(fromText);
        timeSearchPanel.add(new JLabel("yyyy-MM-dd_HH:mm:ss"));
        timeSearchPanel.add(new JLabel("To"));
        JTextField toText = new JTextField(20);
        timeSearchPanel.add(toText);


        JPanel keywordSearchPanel = new JPanel(new BorderLayout());
        keywordSearchPanel.setBorder(BorderFactory.createTitledBorder("Keyword Search"));

        JPanel keywordCenterPanel = new JPanel(new GridLayout(1, 2));

        JPanel TagsCommentsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        TagsCommentsPanel.add(new Label("Tags"));
        TagsCommentsPanel.add(new TextArea());
        TagsCommentsPanel.add(new Label("Comments"));
        TagsCommentsPanel.add(new TextArea());
        keywordCenterPanel.add(TagsCommentsPanel);

        JPanel stuffPanel = new JPanel();

        stuffPanel.setLayout(new GridLayout(0, 1, 5, 5));

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        typePanel.add(new JLabel("Type "));
        typePanel.add(new TextField(20));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        namePanel.add(new JLabel("Name"));
        namePanel.add(new TextField(20));

        JPanel tagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        tagsPanel.add(new JLabel("Tags  "));
        tagsPanel.add(new TextField(20));

        stuffPanel.add(typePanel);
        stuffPanel.add(namePanel);
        stuffPanel.add(tagsPanel);

        stuffPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keywordCenterPanel.add(stuffPanel);

        JPanel keywordBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        keywordBottomPanel.add(new JLabel("General Search"));
        keywordBottomPanel.add(new JTextField(10));

        keywordSearchPanel.add(keywordBottomPanel, BorderLayout.SOUTH);
        keywordSearchPanel.add(keywordCenterPanel, BorderLayout.CENTER);


        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton andSearch = new JButton("AND SEARCH");
        btnPanel.add(andSearch);
        JButton orSearch = new JButton("OR SEARCH");
        btnPanel.add(orSearch);
        JButton close = new JButton("CLOSE");
        btnPanel.add(close);
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
}
