package Characters;
import States.State;
import common.Messaging.*;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class StateMachine<entity_type> {

    private entity_type owner;
    private State<entity_type> currentState;
    private State<entity_type> previousState;
    private State<entity_type> globalState;

    public StateMachine(entity_type owner) {
        this.owner = owner;
        currentState = null;
        previousState = null;
        globalState = null;

    }

    public void setCurrentState(State<entity_type> s) {
        currentState = s;
    }

    public void setGlobalState(State<entity_type> s) {
        globalState = s;
    }

    public void setPreviousState(State<entity_type> s) {
        previousState = s;
    }

    public void update() {
        if (globalState != null) {
            globalState.execute(owner);
        }

        if (currentState != null) {
            currentState.execute(owner);
        }
    }

    public void changeState(State<entity_type> newState) {
        assert newState != null : "<StateMachine::ChangeState>: trying to change to NULL state";

        previousState = currentState;

        currentState.exit(owner);

        currentState = newState;

        currentState.enter(owner);
    }

    public void revertToPreviousState() {
        changeState(previousState);
    }

    public boolean isInState(State<entity_type> st) {
        System.out.println(currentState.getClass());
        return currentState.getClass() == st.getClass();
    }

    public State<entity_type> getCurrentState() {
        return currentState;
    }

    public State<entity_type> getGlobalState() {
        return globalState;
    }

    public State<entity_type> getPreviousState() {
        return previousState;
    }

    public boolean HandleMessage(Telegram msg) {
        //first see if the current state is valid and that it can handle
        //the message
        if (currentState != null && currentState.OnMessage(owner, msg)) {
            return true;
        }

        //if not, and if a global state has been implemented, send
        //the message to the global state
        if (globalState != null && globalState.OnMessage(owner, msg)) {
            return true;
        }

        return false;
    }


}
