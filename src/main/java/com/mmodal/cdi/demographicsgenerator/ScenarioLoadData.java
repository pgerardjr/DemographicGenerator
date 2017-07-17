package com.mmodal.cdi.demographicsgenerator;


import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by patrice.gerard on 7/17/2017.
 */
public class ScenarioLoadData {

    private List<String> firstNames;
    private List<String> lastNames;
    private List<String> gender;
    private List<String> race;
    private List<String> zipCodes;

    // Change line for IDE processing
    // private Path narrativeDir = Paths.get(System.getProperty("user.dir"),"src","main","resources","narratives");
    private URL narrativeDir = ClassLoader.getSystemResource("narratives");


    public ScenarioLoadData() throws Exception {
        // readAllLines should point to the proper resources
        this.firstNames = Files.readAllLines(new File(narrativeDir.toString() + "firstnames").toPath(), Charset.defaultCharset() );
        this.lastNames = Files.readAllLines(new File(narrativeDir.toString() + "lastnames").toPath(), Charset.defaultCharset() );
    }

    public List<String> getFirstName() {
        return this.firstNames;
    }

    public List<String> getLastNames() {
        return this.lastNames;
    }



}
