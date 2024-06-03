import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Picture {
    private String ID;
    private String timestamp;
    private String Image;
    private Stuff[] stuff = new Stuff[10];
    private int stuffIdx = 0;
    private String Tag;
    private String comment;

    public Picture() {
    }

    public Picture(String[] infoFields) {
        this.ID = setID(infoFields[0]);
        this.timestamp = setTimestamp(infoFields[1]);
        this.Image = setImageInfo(infoFields[2]);
        this.stuff = setStuffInfo(infoFields[3]);
        this.Tag = setTagInfo(infoFields[4]);
        this.comment = setCommentInfo(infoFields[5]);
    }

    // print all args(except for Stuff, Tag, comment)
    public void print() {
        String result = String.format("< %s > < %s > < %s >", this.ID, this.timestamp, this.Image);
        System.out.println(result);
    }

    // getTimestamp to compare Timestamp
    public LocalDateTime getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
        return LocalDateTime.parse(this.timestamp, formatter);
    }
    public String getStrTimestamp() { return this.timestamp; }

    // getID to check IDConflict
    public String getID() { return this.ID; }

    public String getImage() { return this.Image; }

    public Stuff[] getStuff() { return this.stuff; }

    public String getStuffString() {
        String line = "";
        for(int i = 0 ; i < this.stuffIdx; i++) {
            line = line + stuff[i].getStuffInfo();
        }
        return line;
    }

    public String getTag() { return this.Tag; }

    public String getComment() { return this.comment; }

    // trimTxt to trim and remove >, <
    private String trimTxt(String txt) { return txt.trim().replaceAll(">", "").replace("<", ""); }

    // setID to initialize
     public String setID(String ID) {
        return trimTxt(ID);
    }

    // setTimestamp to initialize
    public String setTimestamp(String timestamp) {
        return trimTxt(timestamp);
    }

    // setImageInfo to initialize
    public String setImageInfo(String ImageInfo) {
        return trimTxt(ImageInfo);
    }

    public Stuff[] setStuffInfo(String StuffInfo) {
        String trimStuffInfo = trimTxt(StuffInfo);
        String[] strArr = trimStuffInfo.split("]");
        Stuff[] stuffArr = new Stuff[10];
        for (String token : strArr) {
            String tmp = token.replace("[", "");
            if (!StuffInfo.equals("<>"))
                StuffList.addStuff(tmp);
            if (!tmp.isEmpty()) {
                stuffArr[this.stuffIdx++] = new Stuff(tmp);
            }
        }
        return stuffArr;
    }

    public String setTagInfo(String TagInfo) {
        String trimTagInfo =  trimTxt(TagInfo);
        trimTagInfo = trimTagInfo.replaceAll("#", " #");
        return trimTagInfo.trim();
    }

    public String setCommentInfo(String CommentInfo) {
        return trimTxt(CommentInfo);
    }

    public String getPictureInfo() {
        String line = "";
        String idLine = "< " + getID() + " > ";
        String timestampLine = "< " + getStrTimestamp() + " > ";
        String imageLine = "< " + getImage() + " > ";
        String stuffLine = "< " + getStuffString() + " > ";
        String tagLine = "< " + getTag() + " > ";
        String commentLine = "< " + getComment() + " > ";

        line = line + idLine + timestampLine + imageLine + stuffLine + tagLine + commentLine;
        return line;
    }
}
