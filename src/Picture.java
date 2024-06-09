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
        setIDInfo(infoFields[0]);
        setTimestampInfo(infoFields[1]);
        setImageInfo(infoFields[2]);
        setStuffInfo(infoFields[3]);
        setTagInfo(infoFields[4]);
        setCommentInfo(infoFields[5]);
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

    public int getStuffIdx() { return this.stuffIdx; }

    // trimTxt to trim and remove >, <
    private String trimTxt(String txt) { return txt.trim().replaceAll(">", "").replace("<", ""); }

    // setID to initialize
     public void setIDInfo(String ID) {
        this.ID = trimTxt(ID);
    }

    // setTimestamp to initialize
    public void setTimestampInfo(String timestamp) {
        this.timestamp = trimTxt(timestamp);
    }

    // setImageInfo to initialize
    public void setImageInfo(String ImageInfo) {
        this.Image = trimTxt(ImageInfo);
    }

    public void setStuffInfo(String StuffInfo) {
        this.stuffIdx = 0;
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

        this.stuff = stuffArr;
    }

    public void setTagInfo(String TagInfo) {
        String trimTagInfo =  trimTxt(TagInfo);
        trimTagInfo = trimTagInfo.replaceAll("#", " #");
        this.Tag = trimTagInfo.trim();
    }

    public void setCommentInfo(String CommentInfo) {
        this.comment = trimTxt(CommentInfo);
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
