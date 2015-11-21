package States.TeacherOwned;
import Characters.*;
import MessagePassing.MessageTypes;
import States.State;
import common.Messaging.Telegram;

import static Characters.CharacterName.getNameOfEntity;
import static Characters.CharacterName.robTheStudent;
import static MessagePassing.MessageDispatcher.*;
import common.Time.*;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class CookFood extends State<Teacher> {
    static final CookFood instance = new CookFood();

    private CookFood() {
    }

    private CookFood(CookFood cf) {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static CookFood Instance() {
        return instance;
    }

    @Override
    public void enter(Teacher teacher) {
        if (!teacher.isCooking()) {
            System.out.println(getNameOfEntity(teacher.getIdNo())
                    + ": Putting the food in the oven");

            Dispatch.DispatchMessage(1.5,
                    teacher.getIdNo(),
                    teacher.getIdNo(),
                    MessageTypes.Msg_FoodReady,
                    NO_ADDITIONAL_INFO);

            teacher.setCooking(true);
        }
    }

    @Override
    public void execute(Teacher teacher) {
        System.out.println(getNameOfEntity(teacher.getIdNo())
                + ": I do love cooking");
    }

    @Override
    public void exit(Teacher teacher) {
        System.out.println(getNameOfEntity(teacher.getIdNo())
                + ": Putting the food on the table");
    }

    @Override
    public boolean OnMessage(Teacher teacher, Telegram msg) {
         switch (msg.Msg) {
            case Msg_FoodReady: {
                System.out.println("Message received by "
                        + getNameOfEntity(teacher.getIdNo())
                        + " at time: " + CrudeTimer.Clock.GetCurrentTime());

                System.out.println(getNameOfEntity(teacher.getIdNo())
                        + ": Foods Ready! Lets eat");

                Dispatch.DispatchMessage(SEND_MSG_IMMEDIATELY,
                        teacher.getIdNo(),
                        robTheStudent.idNo,
                        MessageTypes.Msg_FoodReady,
                        NO_ADDITIONAL_INFO);

                teacher.setCooking(false);

                teacher.getFSM().changeState(WorkAtHome.getInstance());
            }

            return true;

        }

        return false;
    }
}
