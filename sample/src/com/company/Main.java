package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String folder = "D:\\JavaNangCao\\abc";
        String filename = "D:\\JavaNangCao\\abc\\test.txt";

        ConsumerProducer cp = new ConsumerProducer();

        Cleaner cleaner01 = new Cleaner(filename, cp, "Cl1");
        cleaner01.excute();

        Scanner scanner = new Scanner(folder,filename,cp,"sc00");
        scanner.excute();
    }
}
