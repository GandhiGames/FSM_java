package States.StudentOwned;

import Characters.Location;
import Characters.Student;
import Characters.CharacterName;
import MessagePassing.MessageTypes;
import States.State;
import common.Messaging.Telegram;
import common.Time.*;
import static MessagePassing.MessageDispatcher.*;

import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public class GoHomeAndRest extends State<Student> {

    static final GoHomeAndRest instance = new GoHomeAndRest();

    private GoHomeAndRest() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    static public GoHomeAndRest Instance() {
        return instance;
    }

    @Override
    public void enter(Student student) {
        if (student.getLocation() != Location.home) {
            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "Getting on the train to go home");
            student.changeLocation(Location.home);

            Dispatch.DispatchMessage(SEND_MSG_IMMEDIATELY,
                    student.getIdNo(),
                    CharacterName.catTheTeacher.idNo,
                    MessageTypes.Msg_HiBabyImHome,
                    NO_ADDITIONAL_INFO);

        }
    }

    @Override
    public void execute(Student student) {
        if (!student.isFatigued()) {
            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "That was a refreshing nap, time to study some more!");

            student.getFSM().changeState(GoToUniAndGainCredits.Instance());
        } else {
            student.decreaseFatigue();
            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "ZZZZ... ");
        }
    }

    @Override
    public void exit(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                + "Leaving the house");
    }

    @Override
    public boolean OnMessage(Student student, Telegram msg) {
        switch (msg.Msg) {
            case Msg_FoodReady:

                System.out.println("Message handled by "
                        + getNameOfEntity(student.getIdNo())
                        + " at time: " + CrudeTimer.Clock.GetCurrentTime());



                System.out.println(getNameOfEntity(student.getIdNo())
                        + ": Okay baby, nom nom nom!'!");

                student.getFSM().changeState(EatFood.Instance());

                return true;

        }
        return false;
    }
}
