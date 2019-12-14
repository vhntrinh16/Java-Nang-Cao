package com.company;

import java.io.File;

public class Scanner {
    private Thread mthread;
    private String mthreadname;
    private File mFile;
    private File mFolder;
    private int mcount;

    private ConsumerProducer consumerProducer;

    private static boolean mIsFinished = false;

    public Scanner(String folder, String filename, ConsumerProducer cp, String threadname){
        mFolder = new File(folder);
        mFile = new File(filename);
        consumerProducer = cp;
        mthreadname = threadname;
    }

    public void excute(){
        mthread = new Thread(new Runnable() {
            @Override
            public void run() {
                scan(mFolder);
                mIsFinished = true;
                System.out.println(Thread.currentThread().getName() + " FINISHED.Files found: " + mcount);
            }
        }, mthreadname);
        mthread.start();
    }

    private void scan(File folder){
        File[] files = folder.listFiles();
        for (File f: files){
            if(f.isDirectory()){
                scan(f);
            }else {
                if(f.length() == mFile.length() && f.getName().equalsIgnoreCase(mFile.getName()) && !f.equals(mFile)){
                    consumerProducer.produce(f);
                    mcount++;
                    System.out.println(Thread.currentThread().getName() + "found : " + f.getAbsolutePath());

                }
            }
        }
    }

    public static boolean IsFinished(){
        return mIsFinished;
    }
}
