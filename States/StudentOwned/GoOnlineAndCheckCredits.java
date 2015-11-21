package States.StudentOwned;

import Characters.Location;
import Characters.Student;
import States.State;
import common.Messaging.Telegram;

import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class GoOnlineAndCheckCredits extends State<Student> {

    static final GoOnlineAndCheckCredits instance = new GoOnlineAndCheckCredits();

    private GoOnlineAndCheckCredits() {
    }

    //copy ctor and assignment should be private
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    //this is a singleton
    static public GoOnlineAndCheckCredits Instance() {
        return instance;
    }


    @Override
    public void enter(Student student) {
        if (student.getLocation() != Location.online) {
            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "Logging into the university website to check my credits");

            student.changeLocation(Location.online);
        }
    }

    @Override
    public void execute(Student student) {
        student.setCurrentCredits(0);


        System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                + "Total credits now: " + student.getTotalCredits());

        if (student.getTotalCredits() >= student.getMaxCredits()) {
            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "WooHoo! That's enough credits for today. Time to go back home to the girlfriend");

            student.getFSM().changeState(GoHomeAndRest.Instance());
        }
        else {
            student.getFSM().changeState(GoToUniAndGainCredits.Instance());
        }

    }

    @Override
    public void exit(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                + "Logging out of the university website");
    }

    @Override
    public boolean OnMessage(Student student, Telegram msg) {
        return false;
    }
}
