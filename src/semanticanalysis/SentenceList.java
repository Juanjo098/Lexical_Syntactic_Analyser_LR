/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semanticanalysis;

import java.util.LinkedList;

/**
 *
 * @author Juanjo
 */
public class SentenceList extends LinkedList<Sentence> {

    private int ifCount, whileCount;

    public SentenceList() {
        ifCount = 0;
        whileCount = 0;
    }

    public void addSentence(String sentence) {
        if (sentence.equals("if")) {
            ifCount++;
            add(new Sentence(false, ifCount, sentence));
        }
        if (sentence.equals("while")) {
            whileCount++;
            add(new Sentence(false, whileCount, sentence));
        }
    }

    public void closeSetence(String sentence) {
        for (int i = size() - 1; i > -1; i--) {
            Sentence sen = get(i);
            if (sen.getType().equals(sentence) && !sen.isClosed()) {
                remove(i);
                return;
            }
        }
    }

    public String getNextSentenseToClose(String sentence) {
        for (int i = size() - 1; i > -1; i--) {
            Sentence sen = get(i);
            if (sen.getType().equals(sentence) && !sen.isClosed()) {
                return sen.getType() + sen.getNumber();
            }
        }
        return null;
    }
    
    public void showContent(){
        for (Sentence sen : this) {
            System.out.println(sen.getType() + sen.getNumber() + " " + sen.isClosed());
        }
    }
    
    public static void main(String[] args) {
        SentenceList list = new SentenceList();
        list.addSentence("if");
        list.addSentence("while");
        list.showContent();
        System.out.println(list.getNextSentenseToClose("if"));
        list.closeSetence("if");
        list.showContent();
    }
}
