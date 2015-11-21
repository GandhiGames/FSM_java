import Characters.CharacterName;
import Characters.Student;
import Characters.Teacher;
import static Characters.CharacterManager.*;
import static MessagePassing.MessageDispatcher.*;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */


public class Run {

    public static void main(String[] args) {

        Characters.Character student = new Student(CharacterName.robTheStudent);
        Characters.Character teacher = new Teacher(CharacterName.catTheTeacher);
        EntityMgr.registerEntity(student);
        EntityMgr.registerEntity(teacher);


        for (int i = 0; i < 2000; ++i) {
            student.update();
            teacher.update();
            Dispatch.DispatchDelayedMessages();
            try{
                Thread.sleep(2000);
            }catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }
}
