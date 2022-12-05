package de.samply.DreDatatransfer;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DreDataTransferApplication {

  public static void main(String[] args) {
    SpringApplication.run(DreDataTransferApplication.class, args);

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
