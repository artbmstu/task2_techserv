package ru.artemiev.task2;

import javafx.util.Pair;
import java.util.*;

public class DataStorage {
    private List<Triple<Integer, String, String>> resultAL, resultLL;
    private Map<Integer, String> resultHM;

    public static void main(String[] args) {
        new DataStorage().addToStorage(args);
    }

    private void addToStorage(String[] args){
        DataReader reader = new DataReader();
        List<Pair<Integer, String>> dataA = reader.getInfo(args[0]);
        List<Pair<Integer, String>> dataB = reader.getInfo(args[1]);


        resultAL = nestedLoopsJoinAL(dataA, dataB);
        resultLL = nestedLoopsJoinLL(dataA, dataB);
        resultHM = nestedLoopsJoinHM(dataA, dataB);

        System.out.println(resultAL);
        System.out.println(resultLL);
        System.out.println(resultHM);
    }

    private List<Triple<Integer, String, String>> nestedLoopsJoinAL(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB) {
        resultAL = new ArrayList<>();
        try{
            for (Pair<Integer, String> leftPair: dataA) {
                for (Pair<Integer, String> rightPair: dataB) {
                    if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                        resultAL.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));
                    }
                }
            }
        } catch (NullPointerException e) {
        System.out.println("Ошибка при работе с ArrayList");
        }
        return resultAL;
    }

    private List<Triple<Integer, String, String>> nestedLoopsJoinLL(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB){
        try {
            resultLL = new LinkedList<>();
            for (Pair<Integer, String> leftPair: dataA) {
                for (Pair<Integer, String> rightPair: dataB) {
                    if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                        resultLL.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка при работе с LinkedList");
        }
        resultLL.sort(Comparator.comparing(Triple::getI));
        return resultLL;
    }

    private  Map<Integer, String> nestedLoopsJoinHM(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB){
        resultHM = new HashMap<>();
        try {
            for (Pair<Integer, String> leftPair: dataA) {
                for (Pair<Integer, String> rightPair: dataB) {
                    if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                        if (resultHM.containsKey(leftPair.getKey())) {
                            String res = resultHM.get(leftPair.getKey());
                            resultHM.put(leftPair.getKey(), res + " | "
                                    + leftPair.getValue() + "-" + rightPair.getValue());
                        }   else resultHM.put(leftPair.getKey(), " " + leftPair.getValue() + "-" + rightPair.getValue());
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка при работе с HashMap");
        }
        return resultHM;
    }
}
