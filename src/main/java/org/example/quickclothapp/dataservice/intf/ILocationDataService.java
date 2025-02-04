package org.example.quickclothapp.dataservice.intf;

import org.example.quickclothapp.exception.DataServiceException;
import org.example.quickclothapp.model.City;
import org.example.quickclothapp.model.Department;

import java.util.List;
import java.util.UUID;

public interface ILocationDataService {
    List<Department> getAllDepartments() throws DataServiceException;
    Department findDepartmentByUuid(UUID uuid);
    List<City> getAllCitiesByDepartment(UUID departmentUuid) throws DataServiceException;
    City findCityByUuid(UUID cityUuid) throws DataServiceException;
}
