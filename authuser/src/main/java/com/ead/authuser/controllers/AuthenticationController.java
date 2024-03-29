package com.ead.authuser.controllers;

import com.ead.authuser.configs.security.JwtProvider;
import com.ead.authuser.dtos.JwtDto;
import com.ead.authuser.dtos.LoginDto;
import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.RoleModel;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.RoleService;
import com.ead.authuser.services.UserService;
import com.ead.authuser.unums.RoleType;
import com.ead.authuser.unums.UserStatus;
import com.ead.authuser.unums.UserType;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/auth")
public class AuthenticationController {

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtProvider jwtProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping(value = "/signup")
  public ResponseEntity<Object> registerUser(@RequestBody
  @Validated(UserDto.UserView.RegistrationPost.class)
  @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {

    log.debug("POST registerUser userDto received {} ", userDto.toString());
    if (userService.existsByusername(userDto.getUsername())) {
      log.warn("UserName {} is Already Taken! ", userDto.getUsername());
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: UserName is Already Taken!");
    }
    if (userService.existsByEmail(userDto.getEmail())) {
      log.warn("Email {} is Already Taken! ", userDto.getEmail());
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
    }
    RoleModel roleModel = roleService.findByRoleName(RoleType.ROLE_STUDENT)
        .orElseThrow(() -> new RuntimeException("Error: Role is Not Found"));

    userDto.setPassword(passwordEncoder.encode(userDto.getPassword())); //encoder
    var userModel = new UserModel();
    BeanUtils.copyProperties(userDto, userModel);
    userModel.setUserStatus(UserStatus.ACTIVE);
    userModel.setUserType(UserType.STUDENT);
    userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
    userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
    userModel.getRoles().add(roleModel);
    userService.saveUser(userModel);
    log.debug("POST registerUser userId saved {} ", userModel.getUserId());
    log.info("User saved successfully userId {} ", userModel.getUserId());
    return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateJwt(authentication);
    return ResponseEntity.ok(new JwtDto(jwt));
  }

  @GetMapping("/")
  public String index() {
    log.trace("TRACE");
    log.debug("DEBUG");
    log.info("INFO");
    log.warn("WARN");
    log.error("ERROR");
    return "Logging Spring boot";
  }


}
