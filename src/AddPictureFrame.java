import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddPictureFrame extends JFrame {
    private StuffSection[] stuffSection = new StuffSection[10];
    private int stuffSectionCount = 0;
    private Picture newPicture;
    private JTextField timeText;
    private JTextField tagsText;
    private JTextField commentsText;

    public AddPictureFrame() {

        setSize(580, 300);
        setTitle("Add a Picture");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel topPanel = new JPanel(new GridBagLayout());
        JLabel time = new JLabel("Time");
        timeText = new JTextField(20);
        JLabel tags = new JLabel(("(Picture) Tags"));
        tagsText = new JTextField(20);
        topPanel.add(time);
        topPanel.add(timeText);
        topPanel.add(tags);
        topPanel.add(tagsText);

        mainPanel.add(topPanel, BorderLayout.NORTH);


        JPanel bottomPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel commentsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        commentsPanel.add(new JLabel("Comments "));
        commentsText = new JTextField(50);
        commentsPanel.add(commentsText);
        bottomPanel.add(commentsPanel);

        JPanel btnPanel = new JPanel(new BorderLayout());
        JButton MoreStuff = new JButton("More Stuff");
        JButton InputEnd = new JButton("OK - INPUT END");
        btnPanel.add(MoreStuff, BorderLayout.WEST);
        btnPanel.add(InputEnd, BorderLayout.EAST);
        bottomPanel.add(btnPanel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        JPanel centerPanel = new JPanel(new BorderLayout());
        JButton SelectImageFile = new JButton("Select Image File");
        centerPanel.add(SelectImageFile, BorderLayout.WEST);

        JPanel stuffs = new JPanel();
        stuffs.setLayout(new GridLayout(0, 1));
        stuffs.setPreferredSize(new Dimension(400, 200));
        JPanel scrollablePanel = new JPanel(new GridLayout(0, 1));
        StuffSection initStuffSection = new StuffSection();
        this.stuffSection[stuffSectionCount++] = initStuffSection;
        scrollablePanel.add(initStuffSection);
        JScrollPane jScrollPane = new JScrollPane(scrollablePanel);
        centerPanel.add(jScrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);

        MoreStuff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StuffSection newStuffSection = new StuffSection();
                stuffSection[stuffSectionCount++] = newStuffSection;
                scrollablePanel.add(newStuffSection);
                scrollablePanel.revalidate();
                scrollablePanel.repaint();
            }
        });

        InputEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] pictureInfo = new String[6];
                pictureInfo[0] = "m_" + timeText.getText();
                pictureInfo[1] = timeText.getText();
                pictureInfo[2] = "ImageInfo";
                pictureInfo[4] = tagsText.getText();
                pictureInfo[5] = commentsText.getText();

                String stuffStr = "";
                for(int i = 0 ; i < stuffSectionCount; i++) {
                    stuffSection[i].setStuff();
                    stuffStr = stuffStr + stuffSection[i].getStuff();
                }
                pictureInfo[3] = stuffStr;

                newPicture = new Picture(pictureInfo);
                writePicture(newPicture.getPictureInfo());
                dispose();
            }
        });
    }

    public void writePicture(String InputString) {
        File file = new File(PictureSearchFrame.getFilePath());
        BufferedReader reader = null;
        BufferedWriter writer = null;
        boolean endTagFound = false;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("// end of Picture information saved by GUI")) {
                    endTagFound = true;
                    break;
                }
            }

            if (!endTagFound) {
                System.out.println("End Tag Not Found");
                return;
            }

            File tempFile = new File(file.getAbsolutePath() + ".temp");
            writer = new BufferedWriter(new FileWriter(tempFile));

            reader.close();
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                if (line.trim().equals("// end of Picture information saved by GUI")) {
                    writer.write(InputString);
                    writer.newLine();
                    writer.write("// end of Picture information saved by GUI");
                    writer.newLine();
                }
            }

        } catch (Exception e) {
            System.out.println("Failed To Add Picture");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File originalFile = new File(PictureSearchFrame.getFilePath());
        File tempFile = new File(originalFile.getAbsolutePath() + ".temp");
        if (tempFile.renameTo(originalFile)) {
            System.out.println("File updated successfully!");
        } else {
            System.out.println("Failed to update file!");
        }
    }
}
