package States.TeacherOwned;
import States.State;
import Characters.Teacher;
import common.Messaging.Telegram;

import java.util.Random;

import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class WorkAtHome extends State<Teacher>  {

    static final WorkAtHome instance = new WorkAtHome();
    private Random rand;

    private WorkAtHome() {
        rand = new Random();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static WorkAtHome getInstance() {
        return instance;
    }

    @Override
    public void enter(Teacher teacher) {
    }

    @Override
    public void execute(Teacher teacher) {
        int r = rand.nextInt(2);
        switch (r) {
            case 0:
                System.out.println(getNameOfEntity(teacher.getIdNo())
                        + ": Drinking a coffee!");
                break;
            case 1:
                System.out.println(getNameOfEntity(teacher.getIdNo())
                        + ": Marking my pupils books");
                break;
            case 2:
                System.out.println(getNameOfEntity(teacher.getIdNo())
                        + ": Creating resources for tomorrow!");
                break;
        }
    }

    @Override
    public void exit(Teacher teacher) {

    }

    @Override
    public boolean OnMessage(Teacher teacher, Telegram msg) {
        return false;
    }

}
