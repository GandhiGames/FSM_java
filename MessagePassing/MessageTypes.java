package MessagePassing;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public enum MessageTypes {

    Msg_HiBabyImHome(0),
    Msg_FoodReady(1);

    final public int id;

    MessageTypes(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return MsgToStr(this.id);
    }
    public static String MsgToStr(MessageTypes msg) {
        return MsgToStr(msg.id);

    }
    public static String MsgToStr(int msg) {
        switch (msg) {
            case 0:
                return "HiBabyImHome";
            case 1:
                return "FoodReady";
            default:
                return "Not recognized!";
        }
    }
}