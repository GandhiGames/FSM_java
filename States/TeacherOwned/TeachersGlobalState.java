package States.TeacherOwned;
import States.State;
import Characters.Teacher;
import common.Messaging.Telegram;
import common.Time.*;

import java.util.Random;

import static Characters.CharacterName.getNameOfEntity;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class TeachersGlobalState extends State<Teacher>  {
    static final TeachersGlobalState instance = new TeachersGlobalState();
    private Random rand;

    private TeachersGlobalState() {
        rand = new Random();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static TeachersGlobalState getInstance() {
        return instance;
    }

    @Override
    public void enter(Teacher teacher) {
    }

    @Override
    public void execute(Teacher teacher) {
        if (rand.nextFloat() < 0.1) {
            teacher.getFSM().changeState(VisitBathroom.getInstance());
        }
    }

    @Override
    public void exit(Teacher teacher) {
    }

    @Override
    public boolean OnMessage(Teacher teacher, Telegram msg) {
       switch (msg.Msg) {
            case Msg_HiBabyImHome: {
                System.out.println("Message handled by "
                        + getNameOfEntity(teacher.getIdNo())
                        + " at time: "
                        + CrudeTimer.Clock.GetCurrentTime());

                System.out.println(getNameOfEntity(teacher.getIdNo())
                        + ": Hi baby. Let me make something tasty");

                teacher.getFSM().changeState(CookFood.Instance());
            }

            return true;

        }//end switch

        return false;
    }
}
