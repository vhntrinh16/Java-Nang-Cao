package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cleaner {
    private Thread mthread;
    private String mthreadname;
    private File mFile;

    private int mcount;

    private ConsumerProducer consumerProducer;
    

    public Cleaner(String filename, ConsumerProducer cp, String threadname){
        mFile = new File(filename);
        consumerProducer = cp;
        mthreadname = threadname;
    }



    public void excute(){
        mthread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Scanner.IsFinished() || !consumerProducer.isEmpty()){
                    File f = consumerProducer.consume();
                    if(compareContent(f,mFile)){
                        System.out.println(Thread.currentThread().getName() + " deleting " + f.getAbsolutePath());
                        f.delete();
                        mcount++;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " FINISH - delete: " + mcount);
            }
        }, mthreadname);
        mthread.start();
    }

    private boolean compareContent(File f1, File f2) {

        FileInputStream fis1 = null;
        int count1;
        byte[] buffer1 = new byte[100];

        FileInputStream fis2 = null;
        int count2;
        byte[] buffer2 = new byte[100];

        try {
            fis1 = new FileInputStream(f1);
            fis2 = new FileInputStream(f2);

            while ((count1 = fis1.read(buffer1)) != -1
                    && (count2 = fis2.read(buffer2)) != -1) {

                if (count1 == count2) {
                    for (int i = 0; i < count1; i++) {
                        if (buffer1[i] != buffer2[i]) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis1 != null) {
                    fis1.close();
                }
                if (fis2 != null) {
                    fis2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
