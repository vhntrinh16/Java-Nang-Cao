package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Del {
    public static void main(String[] args) throws IOException {
        listFile("D:\\Java-Nang-Cao");
        removeDuplicatedfile("D:\\Java-Nang-Cao", "test.txt");
    }

    public static ArrayList<String> listPath = new ArrayList<>();
    public static List<File> listText = new ArrayList<>();
    public static void listFile(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if(files.length == 0){
            listPath.add(folder.getCanonicalPath());
            //System.out.println(folder.getCanonicalPath());
        }
        for (File f: files){
            if(f.isFile()){
                listPath.add(f.getCanonicalPath());
                listText.add(f);
                //System.out.println(f.getCanonicalPath());
            }else {
                listFile(f.getPath());
            }
        }
    }

    public static String getContentFile(String filename) throws IOException {
        String str = "";
        Reader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null){
            str += line + '\n';
        }
        reader.close();
        return str;
    }

    public static boolean sametext(String file1, String file2) throws IOException {
        boolean isEqual = true;
        String f1 = getContentFile(file1);
        String f2 = getContentFile(file2);
        if(f1.equals(f2)){
            isEqual = true;
        }else isEqual = false;
        return isEqual;
    }


    public static void removeDuplicatedfile(String folderPath, String filename) throws IOException {
        listFile(folderPath);
        String vContent = "";
        String vPath = folderPath + "\\" + filename;
        System.out.println(vPath);
        for (int i = 1; i < listText.size(); i++){
            vContent = listText.get(i).getName();
            if (vContent.equals(filename) || sametext(vPath, listText.get(i).toString())){
                System.out.println("I have deleted: " + listText.get(i));
                listText.get(i).delete();
            }
        }
        System.out.println("\n\n");
    }
}
