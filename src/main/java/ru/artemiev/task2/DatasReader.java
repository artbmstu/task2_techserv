package ru.artemiev.task2;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

class DatasReader {

    List<Pair<Integer, String>> getInfo(String fileName){
        List<Pair<Integer, String>> datas = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
            try{
                datas = read(in);
            } catch (IOException ioe){
                System.out.println("Ошибка чтения исходного файла");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Отсутствует исходный файл: " + fileName);
        }
        return datas;
    }

    private List<Pair<Integer,String>> read(BufferedReader in) throws IOException{
        String string;
        int i = 0;
        List<Pair<Integer, String>> data = new ArrayList<Pair<Integer, String>>() {
        };
        while ((string = in.readLine()) != null) {
            String[] datas = (string.split(" ", 2));
            Pair<Integer, String> pair = null;
            try {
                pair = new Pair<>(Integer.parseInt(datas[0]), datas[1]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка формата входных данных");
            }
            data.add(i++, pair);
        }
        return data;
    }
}