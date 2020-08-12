package com.carbooking.carbookingapi.dao;

import java.util.List;
import com.carbooking.carbookingapi.domain.Car;

/**
 * 
 * @author Kittitad S.
 */
public interface CarDao {

  public List<Car> find() throws Exception;

  public Car findById(int id) throws Exception;

  public void insert(Car car) throws Exception;

  public void update(Car car) throws Exception;

  public void deleteById(int id) throws Exception;
  
}
