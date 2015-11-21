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
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class GoToPubAndQuenchThirst extends State<Student> {
    static final GoToPubAndQuenchThirst instance = new GoToPubAndQuenchThirst();

    private GoToPubAndQuenchThirst() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    static public GoToPubAndQuenchThirst Instance() {
        return instance;
    }

    @Override
    public void enter(Student student) {
        if (student.getLocation() != Location.pub) {
            student.changeLocation(Location.pub);

            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "I think it's time for a break. I'm off to the pub!");
        }

    }

    @Override
    public void execute(Student student) {
        if (student.isThirsty()) {
            student.buyDrink();

            System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                    + "mmmmm alcohol");

            student.getFSM().changeState(GoToUniAndGainCredits.Instance());
        } else {
            System.out.println("\nERROR!\nERROR!\nERROR!");
        }
    }

    @Override
    public void exit(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo()) + ": "
                + "Leaving the pub. Feeling good!");
    }

    @Override
    public boolean OnMessage(Student student, Telegram msg) {
        return false;
    }
}
