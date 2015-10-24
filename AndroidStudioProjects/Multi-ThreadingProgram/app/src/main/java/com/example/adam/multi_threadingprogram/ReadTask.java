package com.example.adam.multi_threadingprogram;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Adam on 10/23/2015.
 */
public class ReadTask extends AsyncTask<Object,String,Integer> {
    ProgressBar myProgressBar;
    File file;
    ArrayAdapter listArray;

    protected Integer doInBackground(Object... params){

        listArray = (ArrayAdapter) params[2];
        myProgressBar = (ProgressBar) params[1];
        file = (File) params[0];

        try {
            String nextLine;
            BufferedReader in = new BufferedReader(new FileReader(file));
            nextLine = in.readLine();

            while (nextLine != null) {
                publishProgress(nextLine);

                Thread.sleep(250);
                nextLine = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myProgressBar.setProgress(0);
        return 1;
    }

    protected void onProgressUpdate(String... progress){
        listArray.add(progress[0]);
        myProgressBar.setProgress(myProgressBar.getProgress()+1);
    }
}
