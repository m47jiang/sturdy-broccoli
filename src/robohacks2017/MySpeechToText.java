/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robohacks2017;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import java.io.File;

/**
 *
 * @author GeoYS
 */
public class MySpeechToText {
    SpeechToText service = new SpeechToText();
    
    public MySpeechToText() {
        service.setUsernameAndPassword("c39d453d-2241-4477-8494-b5cac479ade8", "3DhT8ZwEcMvT");    
    }

    public SpeechResults getSpeechResult(String path) {
        RecognizeOptions options = new RecognizeOptions.Builder()
            .contentType("audio/wav")
            .model("en-US_NarrowbandModel")
            .build();
        
        SpeechResults results = service.recognize(new File(path), options).execute();
        System.out.println(results);
        
        return results;
    }
}
