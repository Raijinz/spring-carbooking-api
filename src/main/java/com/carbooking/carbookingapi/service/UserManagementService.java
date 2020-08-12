package com.carbooking.carbookingapi.service;

import java.util.List;

import com.carbooking.carbookingapi.model.rest.request.UserRequest;
import com.carbooking.carbookingapi.model.rest.response.UserResponse;

public interface UserManagementService {
  
  public List<UserResponse> retrieveUser() throws Exception;

  public UserResponse retrieveUser(int id) throws Exception;

  public UserResponse createUser(UserRequest request) throws Exception;

  public UserResponse updateUser(int id, UserRequest request) throws Exception;

  public UserResponse changePassword(int id) throws Exception;

  public UserResponse removeUser(int id) throws Exception;

}
