package States.StudentOwned;

import Characters.Student;
import States.State;
import common.Messaging.Telegram;
import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class EatFood extends State<Student> {
    static final EatFood instance = new EatFood();

    private EatFood() {
    }

    private EatFood(EatFood e) {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static EatFood Instance() {
        return instance;
    }

    @Override
    public void enter(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo())
                + ": " + "Smells awesome!");
    }

    @Override
    public void execute(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo())
                + ": " + "Tastes awesome too!");

        student.getFSM().revertToPreviousState();
    }

    @Override
    public void exit(Student student) {
        System.out.println(getNameOfEntity(student.getIdNo())
                + ": "
                + "Ate it all up! I better get back to whatever I was doing");
    }

    @Override
    public boolean OnMessage(Student student, Telegram msg) {
        return false;
    }
}
