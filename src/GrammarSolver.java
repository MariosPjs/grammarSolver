import java.util.*;

public class GrammarSolver {
    private SortedMap<String, String[]> map = new TreeMap<>();

    public GrammarSolver(List<String> grammar) {
        for (String s : grammar) {
            String[] split = s.split("::=");
            String key = split[0];
            String sequenceOfRules= "";
            for(int i = 1; i < split.length; i++) {
                sequenceOfRules += split[i];
            }
            String[] values = sequenceOfRules.split("[ \t]+");
            map.put(key,values);
        }

    }
}

}
