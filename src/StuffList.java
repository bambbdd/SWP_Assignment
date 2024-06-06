import java.lang.String;
import java.util.Arrays;
public class StuffList {
    private static Stuff[] stuffArr = new Stuff[20];
    private static int size = 0;

    public StuffList() { }

    public static void addStuff(String stuff) {
        Stuff newStuff = new Stuff(stuff);
        if(!isStuffHere(newStuff)) {
            stuffArr[size++] = newStuff;
        }
    }

    public static void print() {
        String line = "<";
        for (int i = 0; i < size; i++) {
            String stuffInfo = String.format("[%s; %s; %s; %s]",
                    stuffArr[i].getID(), stuffArr[i].getType(), stuffArr[i].getName(), stuffArr[i].getTag());
            line = line + stuffInfo;
        }
        line = line + ">";
        System.out.println(line);
    }

    public static String allocateID() {
        int newID = 1;
        int[] IDs = new int[size];
        for(int i = 0 ; i < size; i++) {
            IDs[i] = stuffArr[i].getIDAsInt();
        }

        Arrays.sort(IDs);

        for(int number : IDs) {
            if (number == newID)
                newID++;
            else if (number > newID)
                break;
        }

        return String.format("%08d", newID);
    }

    public static boolean isStuffHere(Stuff stuff_value) {
        for (int i = 0 ; i < size; i++) {
            if(stuffArr[i].getID().equals(stuff_value.getID())) {
                return true;
            }
        }
        return false;
    }

    public static String setStuffID(String name_value) {
        for(int i = 0 ; i < size; i++) {
            if(stuffArr[i].getName().equals(name_value))
                return stuffArr[i].getID();
        }
        return allocateID();
    }

    public static void refreshStuff(Stuff stuff_value) {
        for(int i = 0 ; i < size; i++) {
            if(stuffArr[i].getID().equals(stuff_value.getID())) {
                stuffArr[i] = stuff_value;
            }
        }
    }
}
