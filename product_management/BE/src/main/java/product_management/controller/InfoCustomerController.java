package product_management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product_management.model.Customer;
import product_management.service.customer.ICustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
public class InfoCustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("info/{username}")
    public ResponseEntity<Customer> showInfoCustomer(@PathVariable String username) {
        Customer viewInfo = customerService.viewInfo(username);
        if (viewInfo == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(viewInfo, HttpStatus.OK);
    }
}
