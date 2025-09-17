package com.tnsif.customer.controller;


//Necessary imports would be here for List, ResponseEntity, annotations, etc.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CustomerController {

 @Autowired
 private CustomerService service;

 @GetMapping("/customerservice")
 public List<Customer> list() {
     return service.listAll();
 }

 @PostMapping("/customerservice")
 public void add(@RequestBody Customer c1) {
     service.save(c1);
 }

 @GetMapping("/customerservice/{id}")
 public ResponseEntity<Customer> get(@PathVariable Integer id) {
     try {
         Customer c2 = service.get(id);
         return new ResponseEntity<Customer>(c2, HttpStatus.OK);
     } catch (NoSuchElementException e) {
         return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
     }
 }

 @DeleteMapping("/customerservice/{id}")
 public void delete(@PathVariable Integer id) {
     service.delete(id);
 }

 @PutMapping("/customerservice/{id}")
 public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody Customer update_c) {
     try {
         Customer exist_c = service.get(id);
         exist_c.setC_name(update_c.getC_name());
         exist_c.setAddress(update_c.getAddress());
         service.update(exist_c);
         return new ResponseEntity<Customer>(exist_c, HttpStatus.OK);
     } catch (NoSuchElementException e) {
         return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
     }
 }
}