package ru.artemiev.task2;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class DatasReader {

    List<Pair<Integer, String>> getInfo(String fileName){
        List<Pair<Integer, String>> datas = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
            try{
                datas = read(in);
            } catch (IOException ioe){
                System.out.println("Ошибка");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка");
        }
        return datas;
    }

    List<Pair<Integer,String>> read(BufferedReader in) throws IOException{
        String string;
        int i = 0;
        List<Pair<Integer, String>> data = new ArrayList<Pair<Integer, String>>() {
        };
        while ((string = in.readLine()) != null) {
            String[] datas = (string.split(" ", 2));
            Pair<Integer, String> pair = new Pair<Integer, String>(Integer.parseInt(datas[0]), datas[1]);
            data.add(i++, pair);
        }
        return data;
    }
}

//    void getInfoFromFile(Company company){
//        try {
//            File file = new File("employees.txt");
//            java.io.DatasReader reader = new java.io.DatasReader(file);
//            BufferedReader in = new BufferedReader(reader);
//            HashSet<String> departments = new HashSet<>();
//            String string;
//            int index = -1;
//            while ((string = in.readLine()) != null) {
//                List<String> datas = Arrays.asList(string.split(" ", 4));
//                int tempDepSize = departments.size();
//                departments.add(datas.get(1));
//                if (tempDepSize < departments.size()){
//                    company.addDepartment(datas.get(1));
//                    index++;
//                }
//                for (String dep:
//                        departments) {
//                    if (datas.get(1).equals(dep)) {
//                        company.addEmployee(index, new Employee(Integer.parseInt(datas.get(0)), checkNumericDatas(company, datas.get(2)), datas.get(3)));
//                    }
//                }
//            }
//            in.close();
//        } catch (IOException e) {
//            System.out.println("Ошибка чтения файла");
//        }