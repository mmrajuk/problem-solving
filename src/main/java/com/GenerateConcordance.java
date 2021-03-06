package com;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 Given an arbitrary text document written in English, write a program that will generate a concordance, that is, an alphabetical list of all word occurrences,
 labeled with word counts and sentence numbers in which each occurrence appeared.

 Input:
         2
         Wait a minute. Wait a minute, Doc.
         Are you telling me that you built a time machine out of a DeLorean?

 Output:
         a: {4:1,2,3,3}
         are: {1:3}
         built: {1:3}
         delorean: {1:3}
         doc: {1:2}
         machine: {1:3}
         me: {1:3}
         minute: {2:1,2}
         of: {1:3}
         out: {1:3}
         telling: {1:3}
         that: {1:3}
         time: {1:3}
         wait: {2:1,2}
         you: {2:3,3}

 */
public class GenerateConcordance {

    public enum TOKENIZER_TYPE {WORD, SENTENCE};


    /**
     *
     * @param inputLines
     *
     */
    public static void generateAndPrintConcordance(List<String> inputLines){

        GenerateConcordance test = new GenerateConcordance();

        if(inputLines==null || inputLines.size()==0)
            return; //nothing to do

        ConcordanceTrie trie = ConcordanceTrie.getInstance();


        //first line represents number of lines.
        int linesCount = 0;
        try {
            linesCount = Integer.parseInt(inputLines.get(0));
        } catch (NumberFormatException nfe){
            //Ignore if its not a number
        }

        Tokenizer sentenceTokenizer = test.getTokenizer(TOKENIZER_TYPE.SENTENCE);
        List<String> sentences = new ArrayList<>();
        for(int i=1; i<inputLines.size(); i++) {
            sentences.addAll(sentenceTokenizer.tokenize(inputLines.get(i)));
        }

        Tokenizer wordTokenizer = test.getTokenizer(TOKENIZER_TYPE.WORD);
        for(int i=0; i<sentences.size(); i++){
            List<String> words = wordTokenizer.tokenize(sentences.get(i));
            int j = i+1;
            words.forEach(word -> trie.insert(word.toLowerCase(),j));
        }
        trie.print();
    }

    public Tokenizer getTokenizer(GenerateConcordance.TOKENIZER_TYPE value){
        if(value == GenerateConcordance.TOKENIZER_TYPE.SENTENCE)
            return SentenceTokenizer.getInstance();
        else
            return WordTokenizer.getInstance();
    }
}

/**
 * Purpose of Tokenizer interface here is limited to Concordance test here.
 * If we have to implement Tokenizer for English or other language(s) which are all Unicode supported,
 * we need to define respective grammar rules and process the text which is way too complex.
 * Instead we could leverage open NLP libraries to accomplish this.
 *
 */
interface Tokenizer {
    List<String> tokenize(String input);
}

/**
 * Supported sentence formats for given input.
 *
 */
class SentenceTokenizer implements Tokenizer {

    private static BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
    private static SentenceTokenizer _instance;
    private SentenceTokenizer(){
    }
    @Override
    public List<String> tokenize(String input) {
        iterator.setText(input);
        List<String> sentences = new ArrayList<>();
        int start = iterator.first();
        for ( int end = iterator.next();
              end != BreakIterator.DONE;
              start = end, end = iterator.next()) {
            sentences.add(input.substring(start, end));
        }
        return sentences;
    }

    /**
     * @Singleton
     * @return
     */
    public static Tokenizer getInstance(){
        if(_instance ==  null) {
            synchronized (SentenceTokenizer.class) {
                if(_instance == null){
                    _instance = new SentenceTokenizer();
                }
            }
        }
        return _instance;
    }
}

/**
 * Word tokenizer is a simple whitespace delimiter
 */
class WordTokenizer implements Tokenizer {

    private final Pattern WORD_PATTERN = Pattern.compile("\\b");

    private final Pattern IGNORABLE_CHARS = Pattern.compile("^[\\s.?,\"':()\\d\\?]*\\s*$");

    private static WordTokenizer _instance;

    @Override
    public List<String> tokenize(String input) {
        return WORD_PATTERN.splitAsStream(input).
                parallel().filter(word -> !IGNORABLE_CHARS.matcher(word).matches()
        ).collect(Collectors.toList());
    }

    /**
     * @Singleton
     * @return
     */
    public static Tokenizer getInstance(){
        if(_instance ==  null) {
            synchronized (SentenceTokenizer.class) {
                if(_instance == null){
                    _instance = new WordTokenizer();
                }
            }
        }
        return _instance;
    }
}

/**
 * Trie data structure implementation tailored to concordance test use case
 */
class ConcordanceTrie {

    private ConcordanceNode root;

    private ConcordanceTrie(){
        root = new ConcordanceNode();
    }

    private ConcordanceNode contains(String word){
        ConcordanceNode current = root;
        for (char l: word.toCharArray()) {
            ConcordanceNode node = current.getChildren().get(l);
            if(node == null){
                current = root;
                break; //no need to iterate further
            }
        }
        return current;
    }

    public void insert(String word, int appearedInSentence) {
        ConcordanceNode current = contains(word);
        //TODO: Need to optimize when word contained in the trie.
        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new ConcordanceNode());
        }

        current.setWord(true);
        current.setWordCount(current.getWordCount()+1);
        current.setOccurrences(appearedInSentence);
    }

    public void print(){
        printInPrettyFormat(this.root, "");
    }

    private void printInPrettyFormat(ConcordanceNode node, String word){
        if(node!=null) {
            if (node.isWord()) {
                //a: {4:1,2,3,3}
                StringBuilder builder = new StringBuilder();
                builder.append(word);
                builder.append(": ")
                        .append("{")
                        .append(node.getWordCount())
                        .append(":");
                builder.append(node.getOccurrences().stream()
                        .map(v -> Integer.toString(v)).collect(Collectors.joining(",")));
                builder.append("}");
                System.out.println(builder.toString());
            }
            node.getChildren().entrySet().forEach(e -> {
                printInPrettyFormat(e.getValue(),word+e.getKey());
            });
        }
    }

    public static ConcordanceTrie getInstance(){
        return new ConcordanceTrie();
    }
}

class ConcordanceNode {
    //we need data in alphabetical order in the output.
    private Map<Character, ConcordanceNode> children = new TreeMap<>();
    private boolean isWord;// make true when complete word available
    private int wordCount; //default 0
    private List<Integer> occurrences = new ArrayList<>(); //word appeared in sentences

    public Map<Character, ConcordanceNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, ConcordanceNode> children) {
        this.children = children;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public List<Integer> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrence) {
        this.occurrences.add(occurrence);
    }
}