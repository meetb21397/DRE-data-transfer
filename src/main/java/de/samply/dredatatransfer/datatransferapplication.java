package de.samply.dredatatransfer;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


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
//      is = sardine.get("http://127.0.0.1:8091/remote.php/dav/files/admin/MeetData/Cohort_Export_1.xlsx");
      is = sardine.get("https://hub.dkfz.de/remote.php/dav/files/C2A29CB5-0F2E-47FB-9D1B-61F4700146E2/MeetData/Cohort_Export_1.xlsx");

      // For Saving File on Local File System
//      out = new FileOutputStream("D:\\_NextData\\Cohort_Export_1.xlsx");
//
//      int read = 0;
//      byte[] bytes = new byte[1024];
//
//      while ((read = is.read(bytes)) != -1) {
//        out.write(bytes, 0, read);
//      }

      System.out.println("NextCloud : Done!");


    } catch (IOException e) {
      //throw new RuntimeException(e);
      System.out.println(e.getMessage());
    }

    String xmlString = "<tsRequest> <credentials name=\"admin\" password=\"6a4dGP5KSsefYtF\"> <site contentUrl=\"http://10.3.78.18\" /> </credentials> </tsRequest>";

    RestTemplate restTemplate =  new RestTemplate();
    //Create a list for the message converters
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    //Add the String Message converter
    messageConverters.add(new StringHttpMessageConverter());
    //Add the message converters to the restTemplate
    restTemplate.setMessageConverters(messageConverters);


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_XML);
    HttpEntity<String> request = new HttpEntity<String>(xmlString, headers);

    final ResponseEntity<String> response = restTemplate.postForEntity("http://10.3.78.18/api/api-version/auth/signin", request, String.class);

    System.out.println("Tableau : Done Signing!");

    System.out.println("Tableau : " + response);
  }

}
