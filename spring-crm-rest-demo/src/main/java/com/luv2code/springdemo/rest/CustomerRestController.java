package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
		@Autowired
		private CustomerService customerService;
		
		@GetMapping("/customer")
		public List<Customer> getCustomer(){
			return customerService.getCustomers();
		}
		

		@GetMapping("/customer/{customerId}")
		public Customer getCustomerId(@PathVariable int customerId){
			Customer theCustomer = customerService.getCustomer(customerId);
			if(theCustomer==null) {
				throw new CustomerNotFoundException("Customer Id not found:" +customerId);
			}
			return theCustomer;
		}
		
		@PostMapping("/customer")
		public Customer saveCustomer(@RequestBody Customer theCustomer) {
			//also just in case  pass an idin json set to 0
			//this is a force a save of new item.. instead of update
			theCustomer.setId(0);
			customerService.saveCustomer(theCustomer);
			return theCustomer;
		}
		@PutMapping("/customer")
		public Customer updateCustomer(@RequestBody Customer theCustomer) {
			customerService.saveCustomer(theCustomer);
			return theCustomer;
		}
		@DeleteMapping("/customer/{customerId}")
		public String deleteCustomerId(@PathVariable int customerId){
			Customer theCustomer = customerService.getCustomer(customerId);
			if(theCustomer==null) {
				throw new CustomerNotFoundException("Customer Id not found:" +customerId);
			}
			customerService.deleteCustomer(customerId);
			return "Deleted with customer id: "+theCustomer.getId();
		}
}
