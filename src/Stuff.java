public class Stuff {
    private String ID;
    private String type;
    private String name;
    private String tag;

    public Stuff() {
        this(";;;");
    }

    public Stuff(String infoLine) {
        String[] tokens = infoLine.split(";");

        this.ID = tokens[0];
        this.type = tokens[1];
        this.name = tokens[2];

        if (tokens.length != 4)
            this.tag = " ";
        else {
            this.tag = tokens[3];
            this.tag = this.tag.replaceAll("#", " #");
            this.tag = tag.trim();
        }
    }

    public String getID() { return this.ID; }
    public String getType() { return this.type; }
    public String getName() { return this.name; }
    public String getTag() { return this.tag; }

    public int getIDAsInt() { return Integer.parseInt(this.ID.trim()); }

    public String getStuffInfo() {
        return String.format(" [%s; %s; %s; %s ]", ID, type, name, tag);
    }
}
