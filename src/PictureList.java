import java.io.File;
import java.util.Scanner;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PictureList {
    private Picture[] pictureArr;
    private int size;

    public PictureList(String infoFileName) {
        // initialize PictureList
        this.pictureArr = new Picture[100];
        this.size = 0;

        try {
            File file = new File(infoFileName);
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                int error = 0; // if error == 1 then program doesn't save the object
                String line = input.nextLine();
                String line_trimmed = line.replaceAll(" ", "");
                if(line.startsWith("//")) // ignore start line
                    continue;
                String[] infoFields = line_trimmed.split("(?=<)|(?<=>)"); // split based on <, >


                // check IDConflict
                for(int i = 0 ; i < this.size; i++) {
                    if(this.pictureArr[i].getID().equals(trimTxt(infoFields[0]))) {
                        System.out.println("ID Conflict (a picture with the same ID already exists); Skip the input line: " + line);
                        error = 1;
                        break;
                    }
                }

                // check NoTimestamp && check TimestampFormat
                String format = "yyyy-MM-dd_HH:mm:ss:SSS";
                if(infoFields[1].equals("<>")) {
                    System.out.println("No time stamp in the input : " + line);
                    error = 1;
                }
                else {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                        LocalDateTime.parse(trimTxt(infoFields[1]), formatter); // check for an error format
                    } catch (Exception e) {
                        System.out.println("Wrong DateTime Format : " + trimTxt(infoFields[1]));
                        error = 1;
                    }
                }

                // check IDFormat
                if(!infoFields[0].startsWith("<m_")) {
                    System.out.println("Wrong ID Format");
                    error = 1;
                }
                else {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                        LocalDateTime.parse(trimTxt(infoFields[0].replace("m_", "")), formatter); // check for an error format
                    } catch (Exception e) {
                        System.out.println("Wrong ID Format");
                        error = 1;
                    }
                }

                // check PictureInfo
                if(!trimTxt(infoFields[2].replace(";", "")).endsWith(".jpg")) {
                    System.out.println("Wrong PictureInfoFormat");
                    error = 1;
                }

                // parse the Picture
                if(error != 1)
                    this.pictureArr[this.size++] = new Picture(infoFields);
            }
        }
        catch(Exception e) {
            System.out.println("Unknown Picture Info File");
        }
    }

    // return the size of PictureList(pictureArr)
    public int size() { return this.size; }

    // return the Picture with correct index
    public Picture get(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.pictureArr[i];
    }

    // add to PictureList(pictureArr) if you have another Picture object
    public void add(Picture pic) {
        this.pictureArr[this.size++] = pic;
    }

    // sort PictureList(pictureArr) by date
    public void sortByDate() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (get(i).getTimestamp().isAfter(get(j).getTimestamp())) {
                    // Swap
                    Picture yoonsang = pictureArr[i];
                    pictureArr[i] = pictureArr[j];
                    pictureArr[j] = yoonsang;
                }
            }
        }
    }

    // trimTxt to trim and remove >, <
    private String trimTxt(String txt) { return txt.trim().replaceAll(">", "").replace("<", ""); }

}