package Characters;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: robertwells
 * Date: 10/07/2013
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class CharacterManager {

    public static CharacterManager EntityMgr = new CharacterManager();

    private final class EntityMap extends HashMap<Integer, Character> {
    }

    private EntityMap entityMap = new EntityMap();

    private CharacterManager() {
    }

    private CharacterManager(CharacterManager characterManager) {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    public static CharacterManager Instance() {
        return EntityMgr;
    }


    public void registerEntity(Character character) {
        entityMap.put(character.getIdNo(), character);
    }


    public Character getEntityFromID(int id) {

        Character character = entityMap.get(id);

        assert (character != null) : "<EntityManager::GetEntityFromID>: invalid ID";

        return character;
    }


    public void removeEntity(Character character) {
        entityMap.remove(character.getIdNo());
    }

}
