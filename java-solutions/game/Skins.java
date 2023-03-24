package game;

import java.util.HashMap;
import java.util.Map;

public class Skins {
    private final Map<Integer, String> skins = new HashMap<>();

    public boolean addSkin(int id, String skin) {
        if (skin == null || skin.length() > 2)
            return false;
        for (var t : skins.values()) {
            if (t.equals(skin)) {
                return false;
            }
        }
        skins.put(id, skin);

        return true;
    }

    public String getSkin(int id) {
        return skins.get(id);
    }


}
