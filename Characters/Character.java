package Characters;
import common.Messaging.*;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class Character {
    private int idNo;
    private int nextValidId;

    public Character(CharacterName characterNames) {
        setIdNo(characterNames.idNo);

    }

    public void setIdNo(int idNo){
        assert (idNo >= nextValidId) : "Invalid Characters.Character ID";
        this.idNo = idNo;
        nextValidId = this.idNo + 1;

    }

    public int getIdNo() {
        return idNo;
    }

    abstract public void update();

    abstract public boolean handleMessage(Telegram msg);



}
