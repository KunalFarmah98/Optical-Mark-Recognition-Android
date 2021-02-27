package com.apps.kunalfarmah.omrscanner;


import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.apps.kunalfarmah.omrscanner.MainActivity.file;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class Util {

    public static String SOURCE_FOLDER = System.getProperty("user.dir") + "/sources/";
    public static String TARGET_FOLDER = System.getProperty("user.dir") + "/target/";

    public static String getSource(String name){
        return SOURCE_FOLDER + name;
    }

    public static String getOutput(String name){
        return TARGET_FOLDER + name;
    }

    public static void write2File(Mat source, String name){
        imwrite(getOutput(name), source);
    }

    public static void sout(String str){
        System.out.println(str);
    }

    public static void sortTopLeft2BottomRight(List<MatOfPoint> points){
        // top-left to right-bottom sort
        Collections.sort(points, new Comparator<MatOfPoint>() {
            @Override
            public int compare(MatOfPoint e1, MatOfPoint e2) {

                Point o1 = new Point(e1.get(0, 0));
                Point o2 = new Point(e2.get(0, 0));

                return o1.y > o2.y ? 1 : -1;
            }
        });
    }

    public static void sortLeft2Right(List<MatOfPoint> points){
        // left to right sort
        Collections.sort(points, new Comparator<MatOfPoint>() {
            @Override
            public int compare(MatOfPoint e1, MatOfPoint e2) {

                Point o1 = new Point(e1.get(0, 0));
                Point o2 = new Point(e2.get(0, 0));

                return o1.x > o2.x ? 1 : -1;
            }
        });
    }

     static void writeToFile(String data, File file)
    {


        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            FileOutputStream fOut = new FileOutputStream(file,true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.write(data);
            myOutWriter.write("\n");
            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}