package com.mmodal.cdi.demographicsgenerator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by patrice.gerard on 7/17/2017.
 */
public class ClientCreator {
    public static void main(String[] args){
        URL narrativeDir = ClassLoader.getSystemResource("demographicFiles");
        try {
            System.out.println(narrativeDir.toURI().toString());
            System.out.println(narrativeDir.toExternalForm());
            System.out.println(narrativeDir.toURI().toURL());
            System.out.println(narrativeDir.getPath().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
