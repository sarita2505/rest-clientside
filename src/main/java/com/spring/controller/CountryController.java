package com.spring.controller;

import com.spring.model.Country;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CountryController {
   static List<Country> countries=new ArrayList<>();
    public void countryList(){
        countries.add(new Country(1,"India",45600000000l));
        countries.add(new Country(2,"srilanka",4500000l));
        countries.add(new Country(3,"Bhutan",356000000l));

    }

    public CountryController() {
        System.out.println("default constructor");
        countryList();
    }

    @GetMapping(value = "/countries",headers = "Accept=application/json")
    public List<Country> selectAll(){
        return countries;
    }
    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> selectById(@PathVariable("id") Integer id){
        Country country=new Country();
        for (Country country1:countries) {
            if(id.equals(country1.getId())){
                 country=country1;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(country);
    }
    @PostMapping("/countries")
    public ResponseEntity<Boolean> postData(@RequestBody  Country country){
        countries.add(country);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @PutMapping("/countries/{id}")
    public ResponseEntity<Boolean> puttData(@RequestBody  Country country,@PathVariable ("id") Integer id){
        Boolean flag=false;

        for (Country country1:countries) {
            if (id.equals(country1.getId())){
               country1.setId(country.getId());
               country1.setCountryName(country.getCountryName());
               country1.setPopulation(country.getPopulation());
                flag=true;
                break;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(flag);
    }
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Boolean> deleteData(@PathVariable ("id") Integer id){
        Boolean flag=false;

        for (Country country1:countries) {
            if (id.equals(country1.getId())){
                countries.remove(country1);
                flag=true;
                break;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(flag);
    }
}
