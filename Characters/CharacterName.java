package Characters;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 09/07/2013
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public enum CharacterName {
    robTheStudent(0),
    catTheTeacher(1);

    final public int idNo;

    private CharacterName(int idNo) {
        this.idNo = idNo;
    }

    /*public static String getCharacterName(Characters.CharacterName characterNames) {
        return characterNames.toString();

    } */

    @Override
    public String toString() {
        return CharacterName.getNameOfEntity(this.idNo);
    }

    static public String getNameOfEntity(int n) {
        switch (n) {
            case 0:
                return "Student R";
            case 1:
                return "Teacher C";
            default:
                return "Unknown Characters.Character!";
        }
    }


}
