package ru.artemiev.task2;

import javafx.util.Pair;
import java.util.*;

public class DataStorage {

    public static void main(String[] args) {
        new DataStorage().addToStorage(args);
    }

    private void addToStorage(String[] args){
        DataReader reader = new DataReader();
        List<Pair<Integer, String>> dataA = reader.getInfo(args[0]);
        List<Pair<Integer, String>> dataB = reader.getInfo(args[1]);
        List<Triple<Integer, String, String>> resultAL = nestedLoopsJoinAL(dataA, dataB);

        List<Pair<Integer, String>> dataA_LL = convertToLL(dataA);
        List<Pair<Integer, String>> dataB_LL = convertToLL(dataB);
        List<Triple<Integer, String, String>> resultLL = nestedLoopsJoinLL(dataA_LL, dataB_LL);

        Map<Integer, List<String>> dataA_HM = convertToHM(dataA);
        Map<Integer, List<String>> dataB_HM = convertToHM(dataB);
        Map<Integer, List<String>> resultHM = nestedLoopsJoinHM(dataA_HM, dataB_HM);

        System.out.println(resultAL);
        System.out.println(resultLL);
        System.out.println(resultHM);
    }

    private List<Pair<Integer, String>> convertToLL(List<Pair<Integer, String>> data) {
        List<Pair<Integer, String>> data_LL = new LinkedList<>();
        data_LL.addAll(data);
        try {
            data_LL.sort(Comparator.comparing(Pair::getKey));
        } catch (NullPointerException e) {
            System.out.println("Ошибка работы с ArrayList при конвертации в LinkedList");
        }
        return data_LL;
    }

    private Map<Integer, List<String>> convertToHM(List<Pair<Integer, String>> data) {
        Map<Integer, List<String>> data_HM = new HashMap<>();
        try {
            for (Pair<Integer, String> p :
                    data) {
                List<String> values = new ArrayList<>();

                if (data_HM.containsKey(p.getKey())) {
                    data_HM.get(p.getKey()).add(p.getValue());
                } else {
                    values.add(p.getValue());
                    data_HM.put(p.getKey(), values);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка работы с ArrayList при конвертации в Hashmap");
        }
        return data_HM;
    }

    private List<Triple<Integer, String, String>> nestedLoopsJoinAL(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB) {
        List<Triple<Integer, String, String>> localResultAL = new ArrayList<>();
        try{
            for (Pair<Integer, String> leftPair: dataA) {
                for (Pair<Integer, String> rightPair: dataB) {
                    if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                        localResultAL.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));
                    }
                }
            }
        } catch (NullPointerException e) {
        System.out.println("Ошибка при работе с ArrayList");
        }
        return localResultAL;
    }

    private List<Triple<Integer, String, String>> nestedLoopsJoinLL(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB){
        List<Triple<Integer, String, String>> localResultLL = new LinkedList<>();
        try {
            ListIterator<Pair<Integer, String>> iteratorA = dataA.listIterator();
            ListIterator<Pair<Integer, String>> iteratorB = dataB.listIterator();
            Pair<Integer, String> leftPair, rightPair;

            label:
            while (iteratorA.hasNext()) {
                int count = 1;
                leftPair = iteratorA.next();
                rightPair = iteratorB.next();
                while (!leftPair.getKey().equals(rightPair.getKey()) && iteratorA.hasNext() && iteratorB.hasNext()) {
                    if (leftPair.getKey() < rightPair.getKey()) {
                        leftPair = iteratorA.next();
                    } else if (rightPair.getKey() < leftPair.getKey()) {
                        rightPair = iteratorA.next();
                    } else break label;
                }

                while (true) {
                    if (rightPair.getKey().equals(leftPair.getKey())) {
                        count++;
                        localResultLL.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));
                        if (iteratorB.hasNext()) rightPair = iteratorB.next();
                        else break;
                    } else {
                        for (int i = 0; i < count; i++) {
                            iteratorB.previous();
                        }
                        break;
                    }
                }

            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка при работе с LinkedList");
        }
        return localResultLL;
    }

    private Map<Integer, List<String>> nestedLoopsJoinHM(Map<Integer, List<String>> dataA, Map<Integer, List<String>> dataB) {
        Map<Integer, List<String>> localResultHM = new HashMap<>();
        try {
            for (Map.Entry<Integer, List<String>> entry :
                    dataA.entrySet()) {
                if (dataB.containsKey(entry.getKey())) {
                    List<String> values = new ArrayList<>();
                    for (int i = 0; i < dataA.get(entry.getKey()).size(); i++) {
                        for (int j = 0; j < dataB.get(entry.getKey()).size(); j++) {
                            values.add(dataA.get(entry.getKey()).get(i) + ":" + dataB.get(entry.getKey()).get(j));
                        }
                    }
                    localResultHM.put(entry.getKey(), values);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка при работе с HashMap");
        }
        return localResultHM;
    }
}
