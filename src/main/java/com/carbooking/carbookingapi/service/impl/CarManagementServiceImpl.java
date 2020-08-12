package com.carbooking.carbookingapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.carbooking.carbookingapi.dao.CarDao;
import com.carbooking.carbookingapi.domain.Car;
import com.carbooking.carbookingapi.model.rest.request.CarRequest;
import com.carbooking.carbookingapi.model.rest.response.CarResponse;
import com.carbooking.carbookingapi.service.CarManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagementServiceImpl implements CarManagementService {

  @Autowired
  private CarDao carDao;

  @Override
  public List<CarResponse> retrieveCar() throws Exception {
    List<CarResponse> response = new ArrayList<CarResponse>();
    try {
      List<Car> cars = carDao.find();
      for (Car car : cars) {
        CarResponse modifiedCar = new CarResponse();
        modifiedCar.setSuccess(true);
        modifiedCar.setId(car.getId());
        modifiedCar.setBrandName(car.getBrandName());
        modifiedCar.setModelName(car.getModelName());
        modifiedCar.setPrice(car.getPrice());
        modifiedCar.setReserved(car.getReserved());
        response.add(modifiedCar);
      }
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public CarResponse retrieveCar(int id) throws Exception {
    CarResponse response = new CarResponse();
    try {
      Car car = carDao.findById(id);
      response.setSuccess(true);
      response.setId(car.getId());
      response.setBrandName(car.getBrandName());
      response.setModelName(car.getModelName());
      response.setPrice(car.getPrice());
      response.setReserved(car.getReserved());
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public CarResponse createCar(CarRequest request) throws Exception {
    CarResponse response = new CarResponse();
    try {
      Car car = new Car();
      car.setBrandName(request.getBrandName());
      car.setModelName(request.getModelName());
      car.setPrice(request.getPrice());
      car.setReserved(false);
      carDao.insert(car);
      response.setSuccess(true);
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public CarResponse updateCar(int id, CarRequest request) throws Exception {
    CarResponse response = new CarResponse();
    try {
      Car car = new Car();
      car.setId(id);
      car.setBrandName(request.getBrandName());
      car.setModelName(request.getModelName());
      car.setPrice(request.getPrice());
      car.setReserved(request.getReserved());
      carDao.update(car);
      response.setSuccess(true);
    } catch (Exception e) {
      throw e;
    }
    return response;
  }

  @Override
  public CarResponse removeCar(int id) throws Exception {
    CarResponse response = new CarResponse();
    try {
      carDao.deleteById(id);
      response.setSuccess(true);
    } catch (Exception e) {
      throw e;
    }
    return response;
  }
  
}
