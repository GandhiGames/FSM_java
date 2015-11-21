package Characters;

import States.StudentOwned.GoHomeAndRest;
import common.Messaging.*;


/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class Student extends Characters.Character {
    final public static int CreditsForDay = 5;
    final public static int MaxCredits = 16;
    final public static int ThirstLevel = 20;
    final public static int TirednessThreshold = 5;


    private StateMachine<Student> stateMachine;
    //private State currentState;
    private Location location;
    private int currentCredits;
    private int totalCredits;
    private int currentThirst;
    private int currentFatigue;

    public Student(CharacterName characterName){
        super(characterName);
        location = Location.home;
        currentCredits = 0;
        totalCredits = 0;
        currentThirst = 0;
        currentFatigue = 0;
        stateMachine = new StateMachine<Student>(this);
        stateMachine.setCurrentState(GoHomeAndRest.Instance());
    }

    /*public void changeState(State newState) {

        assert currentState != null : newState;

        currentState.exit(this);

        currentState = newState;

        currentState.enter(this);
    } */

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.stateMachine = null;
    }

    public StateMachine<Student> getFSM() {
        return stateMachine;
    }

    @Override
    public void update() {
       currentThirst += 1;

       stateMachine.update();
    }

    public void addToCurrentCredits(int credits) {
        currentCredits += credits;

        if (currentCredits < 0) {
            currentCredits = 0;
        }

        addToTotalCredits(credits);

    }

    public void addToTotalCredits(int credits) {
        totalCredits += credits;

        if (totalCredits < 0) {
            totalCredits = 0;
        }
    }

    public boolean isThirsty() {
        return currentThirst >= ThirstLevel;
    }

    public boolean isFatigued() {
        return currentFatigue > TirednessThreshold;
    }

    public void changeLocation(Location location) {
        this.location = location;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public void setCurrentCredits(int currentCredits) {
        this.currentCredits = currentCredits;
    }

    public boolean isEnoughCreditsForToday() {
        return currentCredits >= CreditsForDay;
    }

    public void decreaseFatigue() {
        currentFatigue -= 1;
    }

    public void increaseFatigue() {
        currentFatigue += 1;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public void buyDrink() {
        currentThirst = 0;
    }

    public Location getLocation() {
        return location;
    }

    public int getMaxCredits() {
        return MaxCredits;
    }

    @Override
    public boolean handleMessage(Telegram msg)
    {
        return stateMachine.HandleMessage(msg);
    }

}

