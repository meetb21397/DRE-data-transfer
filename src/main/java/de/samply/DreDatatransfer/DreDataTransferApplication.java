package de.samply.DreDatatransfer;

import ch.qos.logback.classic.BasicConfigurator;
import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DreDataTransferApplication {

  private static Logger s_logger = Logger.getLogger(DreDataTransferApplication.class);

  private static Properties s_properties = new Properties();

  private static final RestApiUtils s_restApiUtils = RestApiUtils.getInstance();

  static {
    // Configures the logger to log to stdout
    BasicConfigurator.configure();

    // Loads the values from configuration file into the Properties instance
    try {
      s_properties.load(new FileInputStream("res/config.properties"));
    } catch (IOException e) {
      s_logger.error("Failed to load configuration files.");
    }
  }


  public static void main(String[] args) {
    SpringApplication.run(DreDataTransferApplication.class, args);

    // Sets the username, password, and content URL, which are all required
    // in the payload of a Sign In request
    String username = s_properties.getProperty("user.admin.name");
    String password = s_properties.getProperty("user.admin.password");
    String contentUrl = s_properties.getProperty("site.default.contentUrl");

    //Add your USER and PASSWORD details in this Sardine Factory section
    Sardine sardine = SardineFactory.begin("m997t", "Heidelberg2022!");
    InputStream is;
    OutputStream out = null;

    try {
      is = sardine.get("https://hub.dkfz.de/apps/onlyoffice/46932282?filePath=%2FDocuments%2FCohort_Export_1.xlsx");

      out = new FileOutputStream("D:\\_NextData\\Cohort_Export_1.xlsx");

      int read = 0;
      byte[] bytes = new byte[1024];

      while ((read = is.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }

      System.out.println("Done!");

/*      System.out.println(Arrays.toString(is.readAllBytes()));
        List<DavResource> resources = sardine.list("http://localhost:8080/MeetData/");
        List<DavResource> resources = sardine.list("http://localhost:8080/remote.php/dav/files/admin/MeetData/");

      for (DavResource res : resources)
     {
      System.out.println(res); // calls the .toString() method.
     }
*/

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
