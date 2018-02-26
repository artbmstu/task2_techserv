package ru.artemiev.task2;

import javafx.util.Pair;
import java.util.*;

public class DataStorage {
    private ArrayList<Triple<Integer, String, String>> resultAL;
    private LinkedList <Triple<Integer, String, String>> resultLL;
    private HashMap resultHM;

    public static void main(String[] args) {
        new DataStorage().addToStorage(args);
    }

    private void addToStorage(String[] args){
        resultLL = new LinkedList<>();
        resultHM = new HashMap();
        DatasReader reader = new DatasReader();
        List<Pair<Integer, String>> dataA = reader.getInfo(args[0]);
        List<Pair<Integer, String>> dataB = reader.getInfo(args[1]);
        resultAL = nestedLoopsJoin(dataA, dataB);
        resultLL.addAll(resultAL);
        Collections.sort(resultLL, Comparator.comparing(o -> o.i));
        int i = 0;
        for (Triple r:
                resultLL) {
//            resultHM.put(i++, r);
            resultHM.put(r.i + " " + r.s1, r.s2);
        }
        System.out.println(resultAL);
        System.out.println(resultLL);
        System.out.println(resultHM);
    }

    private ArrayList<Triple<Integer, String, String>> nestedLoopsJoin(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB) {
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
        System.out.println("Ошибка при работе со списком");
        }
        return resultAL;
    }
}
