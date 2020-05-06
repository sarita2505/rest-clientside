package com.spring.clientside;

import com.spring.controller.CountryController;
import com.spring.model.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Client implements CommandLineRunner {
    static RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(String... args) throws Exception {
         //callRestServicesById();
        //addRestService();
        updateService();
        //deleteService();
        //callRestServices();
    }

//    public static void callRestServices() {
//        String url = "http://localhost:9091/countries";
//       List<Country> list=new ArrayList<>();
//        restTemplate.getForObject(url,Country.class,list);
//        System.out.println("data is"+list);
//    }

    public static void callRestServicesById() {
        //RestTemplate restTemplate=new RestTemplate();
        String url = "http://localhost:9091/countries/1";
        Country country = restTemplate.getForObject(url, Country.class);
        System.out.println("name of the country is:" + country.getCountryName());
    }

    public static void addRestService() {
        final String url = "http://localhost:9091/countries";
        Country country = new Country();
        country.setId(11);
        country.setCountryName("china");
        country.setPopulation(3000000000000l);
        Country country1 = restTemplate.postForObject(url, country, Country.class);
        System.out.println("country added:" + country1);
        //if u use country1.getCountryName then it will give u error
        //bcz the rest post () gives u boolean type value in response
        //and u r trying to extract the countryName from that response which is useless
    }

    public static void updateService() {
        final String url = "http://localhost:9091/countries/11";
        Country country = new Country();
        country.setId(6);
        country.setCountryName("china");
        country.setPopulation(2500000000l);
        restTemplate.put(url, country);
        System.out.println("updated:" + country);
    }

    public static void deleteService() {
        final String url = "http://localhost:9091/countries/11";
        Country country = new Country();
        restTemplate.delete(url, country);
        System.out.println("deleted.." + country);
    }
}
