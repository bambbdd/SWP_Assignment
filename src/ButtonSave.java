import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ButtonSave {
    private PictureSection[] pictures;
    private PicturePanel picPanel;
    public ButtonSave() {
        System.out.println("No Parameter");
    }
    public ButtonSave(PicturePanel picPanel) {
        this.picPanel = picPanel;
        pictures = picPanel.getPictureSectionlist();
        for(int i = 0 ; i < picPanel.getPictureSectionListCount(); i++)
            pictures[i].setPicture();
    }

    public void writePicturesToFile() throws IOException {
        deleteAllData();
        writeLineToFile("// Picture information - saved by GUI");
        for(int i = 0 ; i < picPanel.getPictureSectionListCount(); i++) {
            int j = 0;
            Stuff[] newStuffs = pictures[i].getPicture().getStuff();
            while(newStuffs[j] != null){
                StuffList.refreshStuff(newStuffs[j++]);
            }
            String pictureInfoLine = pictures[i].getPictureInfo();
            writeLineToFile(pictureInfoLine);
        }
        writeLineToFile("// end of Picture information saved by GUI");
    }

    public void deleteAllData() throws IOException {
        File file = new File(PictureSearchFrame.getFilePath());
        if (file.exists()) {
            new FileWriter(file).close();
        } else {
            throw new IOException("File Not Found");
        }
    }

    public void writeLineToFile(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PictureSearchFrame.getFilePath(), true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("File Not Found", e);
        }
    }
}
