package worldofzuul;

import java.util.HashMap;
import java.util.Map;

public class NPC {
    private String name;
    private String iceBreaker;
    private Map<Double, String> dialogMap;

    public NPC(String name) {
        this.name = name;
        dialogMap = new HashMap<>();
    }

    public void addDialog(String dialog, Double num) {
        dialogMap.put(num, dialog);
    }

    public String getDialog(Double num) {
        String dialog = dialogMap.get(num);
        return dialog;
    }
}
