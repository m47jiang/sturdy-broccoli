/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robohacks2017;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import java.io.File;

/**
 *
 * @author GeoYS
 */
public class MyVisualRecognition {
    VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
    
    public MyVisualRecognition() {
        service.setApiKey("d76d0a45307bd1e41fc3c2818eab33e172b24c86");
    }

    public VisualClassification getImageResult(String path) {
        System.out.println("Classify an image");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
            .images(new File(path))
            .build();
        VisualClassification result = service.classify(options).execute();
        System.out.println(result);
        return result;
    }
}
