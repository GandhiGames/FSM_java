package States.TeacherOwned;
import States.State;
import Characters.Teacher;
import common.Messaging.Telegram;

import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
public class VisitBathroom extends State<Teacher> {

    static final VisitBathroom instance = new VisitBathroom();

    private VisitBathroom() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static VisitBathroom getInstance() {
        return instance;
    }

    @Override
    public void enter(Teacher teacher) {
        System.out.println(getNameOfEntity(teacher.getIdNo())
                + ": Walking to the bathroom. Need to pee!");
    }

    @Override
    public void execute(Teacher teacher) {
        System.out.println(getNameOfEntity(teacher.getIdNo())
                + ": Ahhhhhh! Sweet relief!");
        teacher.getFSM().revertToPreviousState();
    }

    @Override
    public void exit(Teacher teacher) {
        System.out.println(getNameOfEntity(teacher.getIdNo())
                + ": Time to get back to work I guess");
    }

    @Override
    public boolean OnMessage(Teacher teacher, Telegram msg) {
        return false;
    }

}
