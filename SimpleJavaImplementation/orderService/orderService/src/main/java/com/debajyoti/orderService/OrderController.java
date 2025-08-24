package com.debajyoti.orderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/{id}")
    public ResponseEntity<String> getOrders(@PathVariable String id) {
        HttpURLConnection httpURLConnection = null;
        try{
            String url = "http://localhost:8081/products/" + id;
            URL urlObject = new URL(url);
            //http request object is created and set all the properties
            httpURLConnection = (HttpURLConnection) urlObject.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setConnectTimeout(100);//for connection wait
            httpURLConnection.setReadTimeout(500);//for resource wait
            //Connection using Tcp by httpClient internally
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()
            ));
            StringBuilder response = new StringBuilder();
            String responseline;
            while ((responseline = bufferedReader.readLine()) != null){
                response.append(responseline);
            }
                bufferedReader.close();
            System.out.println("Response :" +response.toString());


        } catch (Exception e) {
           // throw new RuntimeException(e);
        }finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
        }
        return  ResponseEntity.ok("Order Called Successfully");

    }
}
