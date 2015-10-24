package com.example.adam.multi_threadingprogram;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Adam on 10/23/2015.
 */
class WriteTask extends AsyncTask <Object,Integer,Integer>{
    ProgressBar myProgressBar;
    File file;

    protected Integer doInBackground(Object... params){

        myProgressBar = (ProgressBar) params[1];
        file = (File) params[0];

        try {
            FileOutputStream out = new FileOutputStream(file);
            for (int i = 1; i <= 10; i++) {
                String nextPart = Integer.toString(i);
                out.write(nextPart.getBytes());
                nextPart = "\n";
                out.write(nextPart.getBytes());
                publishProgress(i);
                Thread.sleep(250);
            }
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myProgressBar.setProgress(0);
        return 1;
    }

    protected void onProgressUpdate(Integer... progress){
        myProgressBar.setProgress(progress[0]);
    }

}
