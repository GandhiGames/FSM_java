package Characters;
import States.TeacherOwned.*;
import common.Messaging.*;


/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class Teacher extends Character {
    private StateMachine<Teacher> stateMachine;
    private Location location;
    private boolean cooking;

    public Teacher(CharacterName characterNames) {
        super(characterNames);
        location = Location.home;
        stateMachine = new StateMachine<Teacher>(this);
        stateMachine.setCurrentState(WorkAtHome.getInstance());
        stateMachine.setGlobalState(TeachersGlobalState.getInstance());

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        stateMachine = null;
    }

    @Override
    public void update() {
        stateMachine.update();
    }

    public StateMachine<Teacher> getFSM() {
        return stateMachine;
    }

    public Location getLocation() {
        return location;
    }

    public boolean handleMessage(Telegram msg) {
        return stateMachine.HandleMessage(msg);
    }


    public void changeLocation(Location location) {
        this.location = location;
    }

    public boolean isCooking() {
        return cooking;
    }
    public void setCooking(boolean val){
        cooking = val;
    }

}
