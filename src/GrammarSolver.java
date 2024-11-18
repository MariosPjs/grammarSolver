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
            String[] values = sequenceOfRules.split("[|]");
            map.put(key,values);
        }
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + Arrays.toString(map.get(key)));
        }
    }
    public boolean grammarContains(String symbol){
        return map.containsKey(symbol);
    }

    public String[] generate(String symbol, int times){
        if(!map.containsKey(symbol)){
            throw new IllegalArgumentException("Symbol not found");
        }
        if(times < 0 ){
            throw new IllegalArgumentException();
        }
        String[] result = new String[times];
        for(int i = 0; i < times; i++){
            result[i] = generateSentence(symbol);
        }
        return result;
    }
    public String getSymbols(){
        return map.keySet().toString();
    }
    private String generateSentence(String symbol){
        String sentence = "";
        Random rand = new Random();
        String[] value = map.get(symbol);
        String rule = value[rand.nextInt(value.length)];
        String[] check = rule.split("[ \t]+");
        String word = check[rand.nextInt(check.length)];

        if(map.containsKey(check[0])){
            for(int i = 0; i < check.length; i++){
                sentence = sentence + " " + generateSentence(check[i]);
            }
            return sentence;


        }
            return word;
    }





}
