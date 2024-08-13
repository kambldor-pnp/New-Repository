package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {



        private static Properties properties;


        static {
            String path = "src/test/resources/configuration.properties";

            try{

                FileInputStream file = new FileInputStream(path);
                properties = new Properties();
                properties.load(file);
                file.close();
            }catch ( IOException e){
                System.out.println("We could not read that file" + path);
                e.printStackTrace();//prints the error message
            }


        }

        public static String getProperty (String key){
            return  properties.getProperty(key);
        }




    }
