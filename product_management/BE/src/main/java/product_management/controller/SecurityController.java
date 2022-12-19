package product_management.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import product_management.jwt.JwtTokenUtil;
import product_management.model.Customer;
import product_management.model.Role;
import product_management.model.SocialResponse;
import product_management.model.User;
import product_management.payload.request.LoginRequest;
import product_management.payload.request.LoginResponse;
import product_management.service.customer.impl.CustomerService;
import product_management.service.security.IRoleService;
import product_management.service.security.impl.MyUserDetails;
import product_management.service.security.impl.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/security")
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private IRoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername());
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new LoginResponse(
                        jwt,
                        myUserDetails.getUsername(),
                        roles));
    }

    @PostMapping("/oauth/google")
    public ResponseEntity<?> google(@RequestBody SocialResponse jwtResponseSocial) throws IOException {
        final NetHttpTransport netHttpTransport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder builder =
                new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory)
                        .setAudience(Collections.singletonList("612774287153-uthnsrl25on17doe8413il68ebv9c969.apps.googleusercontent.com"));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(builder.getJsonFactory(), jwtResponseSocial.getToken());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        String email = payload.getEmail();
        Customer customer = customerService.findFakeMail(email);
        LoginResponse loginResponse = new LoginResponse();

        if (customer == null || customer.getUser() == null) {
            customer = new Customer();
            customer.setEmail(payload.getEmail());
            customer.setName((String) payload.get("name"));
            customer.setUser(new User(customer.getEmail(), null));

            userService.saveCreateGmail(customer.getUser());
            customerService.saveCreateGmail(customer);
            roleService.saveCreateGmail(customer.getEmail());

            loginResponse.setUsername(customer.getEmail());
            loginResponse.setAccessToken(jwtTokenUtil.generateJwtToken(customer.getEmail()));
            List<Role> roles = roleService.getRoleByUsername(customer.getEmail());
            List<String> nameRole = new ArrayList<>();
            for (Role role : roles) {
                nameRole.add(role.getName());
            }
            loginResponse.setRoles(nameRole);
            return ResponseEntity.ok(loginResponse);
        }

        User user = customer.getUser();
        if (user != null) {
            loginResponse.setAccessToken(jwtTokenUtil.generateJwtToken(customer.getEmail()));
            loginResponse.setUsername(customer.getEmail());
            List<Role> roles = roleService.getRoleByUsername(customer.getEmail());
            List<String> nameRole = new ArrayList<>();
            for (Role role : roles) {
                nameRole.add(role.getName());
            }
            loginResponse.setRoles(nameRole);
            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.ok(loginResponse);
    }
}
