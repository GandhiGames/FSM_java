package States.StudentOwned;
import Characters.*;
import States.State;
import common.Messaging.Telegram;

import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class GoToUniAndGainCredits extends State<Student> {
    static final GoToUniAndGainCredits instance = new GoToUniAndGainCredits();

    private GoToUniAndGainCredits() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static GoToUniAndGainCredits Instance() {
        return instance;
    }

    @Override
    public void enter(Student student) {
        if (student.getLocation() != Location.university) {

            System.out.println(getNameOfEntity(student.getIdNo())
                    + ": " + "Getting on the train to university");

            student.changeLocation(Location.university);
        }
    }

    @Override
    public void execute(Student student) {
        student.addToCurrentCredits(1);

        student.increaseFatigue();

        System.out.println(getNameOfEntity(student.getIdNo()) +
                ": " + "Studying to gain credits");

        if (student.isEnoughCreditsForToday()) {
            student.getFSM().changeState(GoOnlineAndCheckCredits.Instance());
        }

        if (student.isThirsty()) {
            student.getFSM().changeState(GoToPubAndQuenchThirst.Instance());
        }
    }

    @Override
    public void exit(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                + "I am leaving uni having gained enough credits for today");
    }

    @Override
    public boolean OnMessage(Student student, Telegram msg) {
        return false;
    }
}
