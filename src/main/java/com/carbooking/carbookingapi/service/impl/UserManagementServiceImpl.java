package com.carbooking.carbookingapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.carbooking.carbookingapi.dao.UserDao;
import com.carbooking.carbookingapi.domain.User;
import com.carbooking.carbookingapi.model.rest.request.UserRequest;
import com.carbooking.carbookingapi.model.rest.response.UserResponse;
import com.carbooking.carbookingapi.service.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

  @Autowired
  private UserDao userDao;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public List<UserResponse> retrieveUser() throws Exception {
    List<UserResponse> response = new ArrayList<UserResponse>();
    try {
      List<User> users = userDao.find();
      for (User user : users) {
        UserResponse modifiedUser = new UserResponse();

        modifiedUser.setSuccess(true);
        modifiedUser.setId(user.getId());
        modifiedUser.setName(user.getName());
        modifiedUser.setRole(user.getRole());
        response.add(modifiedUser);
      }
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public UserResponse retrieveUser(int id) throws Exception {
    UserResponse response = new UserResponse();
    try {
      User user = userDao.findById(id);

      response.setSuccess(true);
      response.setId(user.getId());
      response.setName(user.getName());
      response.setRole(user.getRole());
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public UserResponse createUser(UserRequest request) throws Exception {
    UserResponse response = new UserResponse();
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
    try {
      User user = new User();
      user.setName(request.getName());
      user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
      user.setRole(request.getRole());
      userDao.insert(user);

      response.setSuccess(true);
      response.setName(user.getName());
      response.setRole(user.getRole());
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public UserResponse updateUser(int id, UserRequest request) throws Exception {
    UserResponse response = new UserResponse();
    try {
      User user = userDao.findById(id);
      if (request.getName() == "" || request.getName() == "null")
        user.setName(request.getName());
      if (request.getRole() == "" || request.getRole() == "null")
        user.setRole(request.getRole());

      userDao.update(user);

      response.setSuccess(true);
      response.setId(id);
      response.setName(user.getName());
      response.setRole(user.getRole());
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public UserResponse changePassword(int id) throws Exception {
    UserResponse response = new UserResponse();
    try {
      // TODO: Change password request
      response.setSuccess(true);
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public UserResponse removeUser(int id) throws Exception {
    UserResponse response = new UserResponse();
    try {
      userDao.deleteById(id);
      response.setSuccess(true);
    } catch (Exception e) {
      throw e;
    }
    return response;
  }
  
}
