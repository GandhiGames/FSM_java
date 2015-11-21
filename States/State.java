package States;

import Characters.Student;
import common.Messaging.*;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class State<entity_type> {

    abstract public void enter(entity_type e);
    abstract public void execute(entity_type e);
    abstract public void exit(entity_type e);
    abstract public boolean OnMessage(entity_type e, Telegram t);
}
