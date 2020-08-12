package com.carbooking.carbookingapi.dao;

import java.util.List;
import com.carbooking.carbookingapi.domain.User;

/**
 * 
 * @author Kittitad S.
 */
public interface UserDao {
  
  public List<User> find() throws Exception;

  public User findByName(String name) throws Exception;

  public User findById(int id) throws Exception;

  public void insert(User user) throws Exception;

  public void update(User user) throws Exception;

  public void deleteById(int id) throws Exception;

}
