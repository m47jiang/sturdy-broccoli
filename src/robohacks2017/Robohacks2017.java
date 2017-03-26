/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robohacks2017;

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 *
 * @author GeoYS
 */
public class Robohacks2017 {
   
    static private MyVisualRecognition mvr;
    static private MySpeechToText mstt;
    static private AudioRecorder ar;
    static private WebCam wc;
    
    public static void main(String[] args) {
        mvr = new MyVisualRecognition();
        mstt = new MySpeechToText();
        ar = new AudioRecorder();
        wc = new WebCam();
        
        AudioRecorder.isRecording = false;
        
        try {
            ar.init();
        } catch (Exception e) {
            System.out.println("oh no!");
        }
        
        //wc.takePic();
        
        //mvr.getImageResult(WebCam.FILENAME);
        
        try (Scanner keyboard = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                String input = keyboard.nextLine();
                if(input != null) {
                    if ("quit".equals(input)) {
                        System.out.println("Exit programm");
                        exit = true;
                    }
                    
                    if (!AudioRecorder.isRecording) {
                        new Thread(){
                            public void run() {
                                ar.start();
                            }
                        }.start();
                    } else {
                        ar.finish();
                        
                        SpeechResults sresult = mstt.getSpeechResult(AudioRecorder.FILENAME);
                        String transcript = sresult.getResults().get(0).getAlternatives().get(0).getTranscript();                
                        
                        if (transcript.contains("triangle")) {
                            System.out.println("Picking up triangle!");
                            // Webcam and serial port code
                        } else if (transcript.contains("square")) {
                            System.out.println("Picking up square!");
                            // Webcam and serial port code
                        } else if (transcript.contains("circle")) {
                            System.out.println("Picking up circle!");
                            // Webcam and serial port code
                        } else {
                            System.out.println("Not picking up anything!!!");
                        }
                    }
                }
            }
        }
    }
}
