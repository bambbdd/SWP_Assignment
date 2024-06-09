import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchPictureFrame extends JFrame {
    private PictureSearchPanel pictureSearchPanel;
    private PictureList newPictureList;
    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextArea tagsTextArea;
    private JTextArea commentsTextArea;
    private JTextField stuffTypeTextField;
    private JTextField stuffNameTextField;
    private JTextField stuffTagsTextField;
    private JTextField generalSearchTextField;

    public SearchPictureFrame() {
        System.out.println("No Parameter in SearchPictureFrame");
    }

    public SearchPictureFrame(PictureSearchPanel pictureSearchPanel) {
        this.pictureSearchPanel = pictureSearchPanel;
        this.newPictureList = this.pictureSearchPanel.getPicturePanel().getPictureList();
        setSize(570, 400);
        setTitle("Search Picture");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel timeSearchPanel = new JPanel(new GridLayout(2, 3));
        timeSearchPanel.setBorder(BorderFactory.createTitledBorder("Time Search"));

        timeSearchPanel.add(new JLabel("From"));
        fromTextField = new JTextField(20);
        timeSearchPanel.add(fromTextField);
        timeSearchPanel.add(new JLabel("yyyy-MM-dd_HH:mm:ss:SSS"));
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
                andFunction();
                pictureSearchPanel.refreshPicPanel(newPictureList);
            }
        });

        orSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orFunction();
                pictureSearchPanel.refreshPicPanel(newPictureList);
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

    public LocalDateTime getFromTextField() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
        return LocalDateTime.parse(this.fromTextField.getText(), formatter);
    }
    public LocalDateTime getToTextField() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
        return LocalDateTime.parse(this.toTextField.getText(), formatter);
    }
    public String getTagsTextArea() {
        String sourceString = this.tagsTextArea.getText();
        sourceString = sourceString.replace("\n", " ");
        return sourceString;
    }
    public String getCommentsTextArea() {
        String sourceString = this.commentsTextArea.getText();
        sourceString = sourceString.replace("\n", " ");
        return sourceString;
    }
    public String getStuffTypeTextField() { return this.stuffTypeTextField.getText(); }
    public String getStuffNameTextField() { return this.stuffNameTextField.getText(); }
    public String getStuffTagsTextField() { return this.stuffTagsTextField.getText(); }
    public String getGeneralSearchTextField() { return this.generalSearchTextField.getText(); }

    private String[] getKeywordPictureTags() {
        return getTagsTextArea().split(" ");
    }
    private String[] getKeywordPictureComments() {
        return getCommentsTextArea().split(" ");
    }
    private String[] getKeywordStuffType() {
        return getStuffTypeTextField().split(" ");
    }
    private String[] getKeywordStuffName() {
        return getStuffNameTextField().split(" ");
    }
    private String[] getKeywordStuffTags() {
        return getStuffTagsTextField().split(" ");
    }
    private Boolean isPictureTagHere(Picture picture, String pictureTag) {
        if (picture.getTag().contains(pictureTag))
            return true;
        else
            return false;
    }
    private Boolean isPictureCommentHere(Picture picture, String pictureComment) {
        if (picture.getComment().contains(pictureComment))
            return true;
        else
            return false;
    }
    private Boolean isStuffTypeHere(Picture picture, String stuffType) {
        for (int i = 0 ; i < picture.getStuffIdx(); i++) {
            if (picture.getStuff()[i].getType().contains(stuffType))
                return true;
        }
        return false;
    }
    private Boolean isStuffNameHere(Picture picture, String stuffName) {
        for (int i = 0 ; i < picture.getStuffIdx(); i++) {
            if (picture.getStuff()[i].getName().contains(stuffName))
                return true;
        }
        return false;
    }
    private Boolean isStuffTagHere(Picture picture, String stuffTag) {
        for (int i = 0 ; i < picture.getStuffIdx(); i++) {
            if (picture.getStuff()[i].getTag().contains(stuffTag))
                return true;
        }
        return false;
    }
    private boolean andOperator(boolean[] values) {
        for (boolean value : values)
            if (!value)
                return false;
        return true;
    }
    private boolean orOperator(boolean[] values) {
        for (boolean value : values)
            if (value)
                return true;
        return false;
    }
    private void andFunction() {
        for(int i = newPictureList.getSize() - 1; i >= 0 ; i--) {
            boolean[] error = new boolean[8];
            for(int j = 0 ; j < error.length; j++)
                error[j] = false;

            if (!this.fromTextField.getText().isEmpty()) {
                if (newPictureList.get(i).getTimestamp().isBefore(getFromTextField()))
                    error[0] = true;
            }
            if (!this.toTextField.getText().isEmpty()) {
                if (newPictureList.get(i).getTimestamp().isAfter(getToTextField()))
                    error[1] = true;
            }
            if (!getKeywordPictureTags()[0].isEmpty()) {
                for (String pictureTag : getKeywordPictureTags()) {
                    if (!isPictureTagHere(newPictureList.get(i), pictureTag)) {
                        error[2] = true;
                        break;
                    }
                }
            }
            if (!getKeywordPictureComments()[0].isEmpty()) {
                for (String pictureComment : getKeywordPictureComments()) {
                    if (!isPictureCommentHere(newPictureList.get(i), pictureComment)) {
                        error[3] = true;
                        break;
                    }
                }
            }
            if (!getKeywordStuffType()[0].isEmpty()) {
                for (String stuffType : getKeywordStuffType()) {
                    if (!isStuffTypeHere(newPictureList.get(i), stuffType)) {
                        error[4] = true;
                        break;
                    }
                }
            }
            if (!getKeywordStuffName()[0].isEmpty()) {
                for (String stuffName : getKeywordStuffName()) {
                    if (!isStuffNameHere(newPictureList.get(i), stuffName)) {
                        error[5] = true;;
                        break;
                    }
                }
            }
            if (!getKeywordStuffTags()[0].isEmpty()) {
                for (String stuffTag : getKeywordStuffTags()) {
                    if (!isStuffTagHere(newPictureList.get(i), stuffTag)) {
                        error[6] = true;
                        break;
                    }
                }
            }

            // General Search
            if (!generalSearchTextField.getText().isEmpty()) {
                boolean[] general = new boolean[5];
                for (boolean e : general)
                    e = false;
                if (!isPictureTagHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[0] = true;
                if (!isPictureCommentHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[1] = true;
                if (!isStuffTypeHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[2] = true;
                if (!isStuffNameHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[3] = true;
                if (!isStuffTagHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[4] = true;

                error[7] = andOperator(general);
            }

            if (orOperator(error))
                newPictureList.deletePictureByIndex(i);
        }
    }

    private void orFunction() {

        for(int i = newPictureList.getSize() - 1; i >= 0 ; i--) {
            boolean[] timestamperror = new boolean[2]; // OR
            boolean[] error = new boolean[6]; // AND
            for(int j = 0 ; j < timestamperror.length; j++)
                timestamperror[j] = false;
            for(int j = 0 ; j < error.length; j++)
                error[j] = true;


            if (!this.fromTextField.getText().isEmpty()) {
                if (newPictureList.get(i).getTimestamp().isBefore(getFromTextField()))
                    timestamperror[0] = true;
            }
            if (!this.toTextField.getText().isEmpty()) {
                if (newPictureList.get(i).getTimestamp().isAfter(getToTextField()))
                    timestamperror[1] = true;
            }

            if (!getKeywordPictureTags()[0].isEmpty()) {
                for (String pictureTag : getKeywordPictureTags()) {
                    if (isPictureTagHere(newPictureList.get(i), pictureTag)) {
                        error[0] = false;
                        break;
                    }
                }
            }
            if (!getKeywordPictureComments()[0].isEmpty()) {
                for (String pictureComment : getKeywordPictureComments()) {
                    if (isPictureCommentHere(newPictureList.get(i), pictureComment)) {
                        error[1] = false;
                        break;
                    }
                }
            }
            if (!getKeywordStuffType()[0].isEmpty()) {
                for (String stuffType : getKeywordStuffType()) {
                    if (isStuffTypeHere(newPictureList.get(i), stuffType)) {
                        error[2] = false;
                        break;
                    }
                }
            }
            if (!getKeywordStuffName()[0].isEmpty()) {
                for (String stuffName : getKeywordStuffName()) {
                    if (isStuffNameHere(newPictureList.get(i), stuffName)) {
                        error[3] = false;
                        break;
                    }
                }
            }
            if (!getKeywordStuffTags()[0].isEmpty()) {
                for (String stuffTag : getKeywordStuffTags()) {
                    if (isStuffTagHere(newPictureList.get(i), stuffTag)) {
                        error[4] = false;
                        break;
                    }
                }
            }

            if (!generalSearchTextField.getText().isEmpty()) {
                boolean[] general = new boolean[5];
                for (boolean e : general)
                    e = false;
                if (!isPictureTagHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[0] = true;
                if (!isPictureCommentHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[1] = true;
                if (!isStuffTypeHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[2] = true;
                if (!isStuffNameHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[3] = true;
                if (!isStuffTagHere(newPictureList.get(i), getGeneralSearchTextField()))
                    general[4] = true;

                error[5] = andOperator(general);
            }

            if (orOperator(timestamperror))
                newPictureList.deletePictureByIndex(i);
            else {
                if (andOperator(error))
                    newPictureList.deletePictureByIndex(i);
            }
        }
    }
}
