/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robohacks2017;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author GeoYS
 */
public class AudioRecorder {
    
    public static String FILENAME = "lastAudioRecording.wav";
    public static volatile boolean isRecording;
    
    private AudioFormat format;
    private ByteArrayOutputStream out;
    private TargetDataLine line;
    private DataLine.Info info;
    
    public AudioRecorder() {
        float sampleRate = 64000;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        format = new AudioFormat(sampleRate, 
          sampleSizeInBits, channels, signed, bigEndian);
        info = new DataLine.Info(
                TargetDataLine.class, format);
    }
    
    public void init() throws LineUnavailableException {
        line = (TargetDataLine)AudioSystem.getLine(info);
    }
    
    public void start() {
        
        try {
            isRecording = true;
            System.out.println("Starting");

            line.open(format);
            line.start();

            AudioInputStream ais = new AudioInputStream(line);
            File fileOut = new File(FILENAME);

            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

            if (AudioSystem.isFileTypeSupported(fileType, 
                    ais)) {
                AudioSystem.write(ais, fileType, fileOut);
            }
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            isRecording = false;
        }
    }
    
    public void finish() {
        line.stop();
        line.close();
        isRecording = false;
        System.out.println("Finished");
    }
}
