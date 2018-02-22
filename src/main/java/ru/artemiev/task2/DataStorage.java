package ru.artemiev.task2;

import javafx.util.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataStorage {

    public static void main(String[] args) {
        DatasReader reader = new DatasReader();
        List<Pair<Integer, String>> dataA = reader.getInfo(args[0]);
//        List<Pair<Integer, String>> dataB = reader.getInfo(args[1]);

    }

    public static  <Integer, String, String> List<Triple<Integer, String, String>> nestedLoopsJoin(List<Pair<Integer, String>> dataA, List<Pair<Integer, String>> dataB) {
        List<Triple<Integer, String, String>> result = new ArrayList<>();
        for (Pair<Integer, String> leftPair: dataA) {
            for (Pair<Integer, String> rightPair: dataB) {
                if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                    result.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));
                }
            }
        }
        return result;
    }
}
