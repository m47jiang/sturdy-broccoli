/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robohacks2017;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;

public class WebCam {
    
    public static String FILENAME = "webcam.png";
    
    private Webcam usbWebCam; 
    
    public WebCam() {
        List<Webcam> webcamList = Webcam.getWebcams();
        usbWebCam = webcamList.get(1);
        usbWebCam.setViewSize(new Dimension(640, 480));
    }

    public void takePic() {
        usbWebCam.open();
        
        BufferedImage firstImage = usbWebCam.getImage();
        usbWebCam.close();
        
        //BufferedImage croppedImage = firstImage;//.getSubimage(0, 0, 100, 100);
        
        try {
            ImageIO.write(firstImage, "png", new File(FILENAME));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}