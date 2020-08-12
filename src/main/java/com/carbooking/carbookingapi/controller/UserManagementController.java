package com.carbooking.carbookingapi.controller;

import java.util.List;

import com.carbooking.carbookingapi.model.rest.request.LoginRequest;
import com.carbooking.carbookingapi.model.rest.request.UserRequest;
import com.carbooking.carbookingapi.model.rest.response.LoginResponse;
import com.carbooking.carbookingapi.model.rest.response.UserResponse;
import com.carbooking.carbookingapi.service.LoginService;
import com.carbooking.carbookingapi.service.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/users")
public class UserManagementController extends UserManagementControllerValidator {

  @Autowired
  private UserManagementService userManagementService;

  @Autowired
  private LoginService loginService;

  @GetMapping(path = "")
  public List<UserResponse> getAllUsers() throws Exception {
    List<UserResponse> response = userManagementService.retrieveUser();
    return response;
  }

  @GetMapping(path = "/{id}")
  public Object getUser(@PathVariable int id) throws Exception {
    UserResponse response = userManagementService.retrieveUser(id);
    return response;
  }

  @PostMapping(path = "")
  public Object postUser(@RequestBody UserRequest request) throws Exception {
    postUserValidation(request);
    UserResponse response = userManagementService.createUser(request);
    return response;
  }

  @PostMapping(path = "/login")
  public Object loginUser(@RequestBody LoginRequest request) throws Exception {
    loginUserValidation(request);
    LoginResponse response = loginService.login(request);
    return response;
  }

  @PutMapping(path = "/{id}")
  public Object putUser(@PathVariable int id, @RequestBody UserRequest request) throws Exception {
    putUserValidation(request);
    UserResponse response = userManagementService.updateUser(id, request);
    return response;
  }

  @DeleteMapping(path = "/{id}")
  public Object deleteUser(@PathVariable int id) throws Exception {
    UserResponse response = userManagementService.removeUser(id);
    return response;
  }
  
}
