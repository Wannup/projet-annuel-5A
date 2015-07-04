package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class config.
 * @author Mike FIARI
 * @version 1.0
 */
public class Config {
    
    public static String getPropertie (String property) {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(propFileName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (inputStream == null) {
                return "";    
            }
            prop.load(inputStream);
            return prop.getProperty(property);
        } catch (IOException exception) {
            return "";
        }
    }
    
    public static boolean modifyProperties (String property, String value) {
    	try {
	    	String propFileName = "config.properties";
	    	FileInputStream in = new FileInputStream(propFileName);
	    	Properties props = new Properties();
	    	props.load(in);
	    	in.close();
	
	    	FileOutputStream out = new FileOutputStream(propFileName);
	    	props.setProperty(property, value);
	    	props.store(out, null);
	    	out.close();
        } catch (IOException exception) {
            
        }
    	return true;
    }

}
