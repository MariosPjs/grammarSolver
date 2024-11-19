import java.util.*;
/*
The grammar solver is a class that utilizes
 */
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
        System.out.println(Arrays.toString(result));
        return result;
    }
    public String getSymbols(){
        return map.keySet().toString();
    }
    private String generateSentence(String symbol){
        String sentence = "";
        Random rand = new Random();
        System.out.println("Symbol: " + symbol);

        String[] value = map.get(symbol);
        System.out.println("current symbol array: " + Arrays.toString(value));
        String rule = value[rand.nextInt(value.length)];
        rule = rule.trim();
        System.out.println("rule that was picked: " + rule);
        String[] check = rule.split("\\(|\\)|-|\\s+");
        System.out.println("check array: " + Arrays.toString(check));
        String word = check[rand.nextInt(check.length)];
        System.out.println("word that was picked: " + word);


        if(map.containsKey(word)){
            System.out.println("    Picking: " + (check[0]));

            sentence = sentence  + generateSentence(check[0]);
            for(int i = 1; i < check.length; i++){
                sentence = sentence + " " + generateSentence(check[i]) ;
            }
            System.out.println(sentence);
            return sentence;
        }
            return word;
    }





}
