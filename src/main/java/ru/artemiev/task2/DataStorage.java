package ru.artemiev.task2;

import javafx.util.Pair;
import java.util.List;

public class DataStorage {
    public static void main(String[] args) {
        DatasReader reader = new DatasReader();
        List<Pair<Integer, String>> dataA = reader.getInfo(args[0]);
        for (Pair p:
             dataA) {
            System.out.println(p);
        }
    }
}
