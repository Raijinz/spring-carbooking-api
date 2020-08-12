package com.carbooking.carbookingapi.service.impl;

import com.carbooking.carbookingapi.dao.CarDao;
import com.carbooking.carbookingapi.domain.Car;
import com.carbooking.carbookingapi.model.rest.response.CarResponse;
import com.carbooking.carbookingapi.service.CarBookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBookingServiceImpl implements CarBookingService {

  @Autowired
  private CarDao carDao;

  @Override
  public CarResponse bookCar(int id) throws Exception {
    CarResponse response = new CarResponse();
    try {
      Car car = carDao.findById(id);
      car.setReserved(true);
      carDao.update(car);

      response.setSuccess(true);
      response.setId(id);
      response.setBrandName(car.getBrandName());
      response.setModelName(car.getModelName());
      response.setPrice(car.getPrice());
      response.setReserved(car.getReserved());
    } catch (Exception e) {
      throw e;
    }
    return response;
  }
  
}
