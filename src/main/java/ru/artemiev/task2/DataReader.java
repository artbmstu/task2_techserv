package ru.artemiev.task2;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

class DataReader {

    List<Pair<Integer, String>> getInfo(String fileName){
        List<Pair<Integer, String>> data = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
            try{
                data = read(in);
            } catch (IOException ioe){
                System.out.println("Ошибка чтения исходного файла");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Отсутствует исходный файл: " + fileName);
        }
        return data;
    }

    private List<Pair<Integer,String>> read(BufferedReader in) throws IOException{
        String string;
        int i = 0;
        List<Pair<Integer, String>> dataList = new ArrayList<Pair<Integer, String>>() {
        };
        while ((string = in.readLine()) != null) {
            String[] data = (string.split(" ", 2));
            Pair<Integer, String> pair = null;
            try {
                pair = new Pair<>(Integer.parseInt(data[0]), data[1]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка формата входных данных");
            }
            dataList.add(i++, pair);
        }
        return dataList;
    }
}