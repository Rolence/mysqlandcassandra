/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.ara.remittance.security.service;


import com.google.gson.Gson;
import io.ara.remittance.security.JwtUtils;
import io.ara.remittance.security.UserDetailsImpl;
import io.ara.remittance.security.domain.*;
import io.ara.remittance.security.model.*;
import jakarta.ws.rs.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserSecurityRepository userSecurityRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public LoginResponse auth(LoginRequest loginRequest) {
        try {
            if ( StringUtils.isBlank(loginRequest.getUsername())) {
                throw new BadRequestException("warning.lnddo.auth Username is required");
            }

            if ( StringUtils.isBlank(loginRequest.getPassword())) {
                throw new BadRequestException("warning.lnddo.auth Password is required");
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            if (roles.size() == 0) {
            }
            List<String> privilege = new ArrayList<>();
            roles.stream().forEach(role ->{
                Optional<Role> item = roleRepository.findByName(role);
                if (item.isPresent()){
                    item.get().getPrivileges().stream().forEach(pri->{
                        privilege.add(pri.getName());
                    });
                }else {
                    throw new BadRequestException("Role not found for " + role);
                }

            });
            final LoginResponse auth = new LoginResponse();
            auth.setStatus( "success");
            auth.setToken("Bearer "+ jwt);
            auth.setRole(roles);
            auth.setPrivilege(privilege);
            auth.setMerchant(userDetails.getMerchant());
            return auth;
        } catch ( AuthenticationException e) {
            LOG.info("auth Error: {}", e);
            throw new AuthenticationException(
                    e.getLocalizedMessage()) {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }

    @Override
    public SignupResponse signUp(SignupRequest signupRequest) {
        try {
            if ( StringUtils.isBlank(signupRequest.getUsername())) {
                throw new BadRequestException("warning.lnddo.auth Username is required");
            }

            if ( StringUtils.isBlank(signupRequest.getPassword())) {
                throw new BadRequestException("warning.lnddo.auth Password is required");
            }

            if (userSecurityRepository.existsByUsername(signupRequest.getUsername())) {
                throw new BadCredentialsException( "Username is already taken");
            }

            // Create new user's account
            User user = new User();
            user.setPassword(encoder.encode(signupRequest.getPassword()));
            user.setUsername(signupRequest.getUsername());

            Set<String> strRoles = Collections.singleton(signupRequest.getRole());
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.USER.name())
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ADMIN.name())
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            user.setRoles(Arrays.asList(this.roleRepository.findByName(role).get()));
                            roles.add(adminRole);
                            break;
                        case "mod":
                            Role modRole = roleRepository.findByName(ERole.MOD.name())
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;
                        case "sender":
                            Role senderRole = roleRepository.findByName(ERole.SENDER.name())
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(senderRole);
                            break;
                        case "super":
                            Role superRole = roleRepository.findByName(ERole.SUPER.name())
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(superRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.USER.name())
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);
            System.out.println("Roles are: "+new Gson().toJson(user));
            user.setMerchant(signupRequest.getMerchant());
            user.setEmail(signupRequest.getEmail());
            userSecurityRepository.save(user);

            final SignupResponse auth = new SignupResponse();
            auth.setStatus("success");
            auth.setMessage( "User registered successfully");
            return auth;
        } catch ( AuthenticationException e) {
            LOG.info("signUp Error: {}", e);
            throw new BadRequestException(
                    "error.lnddo.signup"+
                    e.getLocalizedMessage() +"\n" +e.getMessage());
        }
    }

}
