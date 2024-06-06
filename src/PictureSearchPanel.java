import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PictureSearchPanel extends JPanel {
    PicturePanel picPanel;
    JScrollPane scrollPane;
    public PictureSearchPanel() {
        this(null);
    }
    public PictureSearchPanel(PictureList picList) {
        setSize(600, 800);
        setLayout(new BorderLayout(0, 0));

        this.picPanel = new PicturePanel(picList);
        this.scrollPane = new JScrollPane(picPanel);
        add(this.scrollPane, BorderLayout.CENTER);

        JButton btnShowAllPictures = new JButton("Show All Pictures");
        btnShowAllPictures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureList showAllPictures = new PictureList(PictureSearchFrame.getFilePath());
                picPanel = new PicturePanel(showAllPictures);

                scrollPane.setViewportView(picPanel);
                picPanel.revalidate();
                picPanel.repaint();
            }
        });
        add(btnShowAllPictures, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.EAST);
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        JButton btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPictureFrame newAddPictureFrame = new AddPictureFrame();
                newAddPictureFrame.setVisible(true);
            }
        });
        panel.add(btnAdd);

        JButton btnDelete = new JButton("DELETE");
        panel.add(btnDelete);

        JButton btnLoad = new JButton("LOAD");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonLoad newButtonLoad = new ButtonLoad();
                String newFileName = newButtonLoad.getSelectedAbsolutePath();
                PictureSearchFrame.setNewFilePath(newFileName);
                PictureList newPictureList = new PictureList(newFileName);
                picPanel = new PicturePanel(newPictureList);

                scrollPane.setViewportView(picPanel);
                picPanel.revalidate();
                picPanel.repaint();
            }
        });
        panel.add(btnLoad);

        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonSave newButtonSave = new ButtonSave(picPanel);
                try {
                    newButtonSave.writePicturesToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(btnSave);

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchPictureFrame newSearchPictureFrame = new SearchPictureFrame();
                newSearchPictureFrame.setVisible(true);
            }
        });
        panel.add(btnSearch);
    }

    public void refreshPicPanel(PictureList pictureList) {
        picPanel = new PicturePanel(pictureList);

        scrollPane.setViewportView(picPanel);
        picPanel.revalidate();
        picPanel.repaint();
        revalidate();
        repaint();
    }
}
