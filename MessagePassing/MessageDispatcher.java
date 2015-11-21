package MessagePassing;

import java.util.TreeSet;
import Characters.Character;
import static Characters.CharacterName.*;
import static MessagePassing.MessageTypes.*;
import static Characters.CharacterManager.*;
import common.Messaging.*;
import common.Time.*;


/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class MessageDispatcher {
    final public static double SEND_MSG_IMMEDIATELY = 0.0f;
    final public static Object NO_ADDITIONAL_INFO = null;

    final public static MessageDispatcher Dispatch = new MessageDispatcher();

    private TreeSet<Telegram> PriorityQ = new TreeSet<Telegram>();

    /**
     * this method is utilized by DispatchMessage or DispatchDelayedMessages.
     * This method calls the message handling member function of the receiving
     * entity, pReceiver, with the newly created telegram
     */
    private void Discharge(Character receiver, Telegram msg) {
        if (!receiver.handleMessage(msg)) {
            System.out.println("Message not handled");
        }
    }

    private MessageDispatcher() {
    }

    private MessageDispatcher(MessageDispatcher d) {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static MessageDispatcher Instance() {
        return Dispatch;
    }


    public void DispatchMessage(double delay,
                                int sender,
                                int receiver,
                                MessageTypes msg,
                                Object ExtraInfo) {

        Character pSender = EntityMgr.getEntityFromID(sender);
        Character pReceiver = EntityMgr.getEntityFromID(receiver);

        if (pReceiver == null) {
            System.out.println("Warning! No Receiver with ID of " + receiver + " found");
            return;
        }
        //create the telegram
        Telegram telegram = new Telegram(0, sender, receiver, msg, ExtraInfo);

        if (delay <= 0.0f) {
            System.out.println("Instant telegram dispatched at time: "
                    + CrudeTimer.Clock.GetCurrentTime()
                    + " by " + getNameOfEntity(pSender.getIdNo())
                    + " for "
                    + getNameOfEntity(pReceiver.getIdNo())
                    + ". Msg is "
                    + MsgToStr(msg));

            Discharge(pReceiver, telegram);
        }
        else {
            double CurrentTime = CrudeTimer.Clock.GetCurrentTime();

            telegram.DispatchTime = CurrentTime + delay;

            //and put it in the queue
            PriorityQ.add(telegram);

            System.out.println("Delayed telegram from "
                    + getNameOfEntity(pSender.getIdNo())
                    + " recorded at time "
                    + CrudeTimer.Clock.GetCurrentTime() + " for "
                    + getNameOfEntity(pReceiver.getIdNo()) + ". Msg is "
                    + MsgToStr(msg));
        }
    }


    public void DispatchDelayedMessages() {

        double CurrentTime = CrudeTimer.Clock.GetCurrentTime();

        while (!PriorityQ.isEmpty()
                && (PriorityQ.last().DispatchTime < CurrentTime)
                && (PriorityQ.last().DispatchTime > 0)) {


            final Telegram telegram = PriorityQ.last();

            Character pReceiver = EntityMgr.getEntityFromID(telegram.Receiver);

            System.out.println("Queued telegram ready for dispatch: Sent to "
                    + getNameOfEntity(pReceiver.getIdNo())
                    + ". Msg is " + MsgToStr(telegram.Msg));

            //send the telegram to the recipient
            Discharge(pReceiver, telegram);

            //remove it from the queue
            PriorityQ.remove(PriorityQ.last());
        }
    }
}
