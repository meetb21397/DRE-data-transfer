package de.samply.dredatatransfer;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class datatransferapplication {

  public static void main(String[] args) {
    SpringApplication.run(datatransferapplication.class, args);

    // Sets the username, password, and content URL, which are all required
    //Add your USER and PASSWORD details in this Sardine Factory section
    Sardine sardine = SardineFactory.begin("admin", "admin");
    InputStream is;
    OutputStream out = null;

    try {
      is = sardine.get("http://127.0.0.1:8091/remote.php/dav/files/admin/MeetData/Cohort_Export_1.xlsx");

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
