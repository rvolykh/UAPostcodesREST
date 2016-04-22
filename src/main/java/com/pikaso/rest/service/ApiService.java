package com.pikaso.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.entity.City;
import com.pikaso.entity.District;
import com.pikaso.entity.Region;
import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DistrictDao;
import com.pikaso.rest.dao.RegionDao;

public class ApiService implements IApiService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
    
    private CityDao cityDao = new CityDao();
    private DistrictDao districtDao = new DistrictDao();
    private RegionDao regiontDao = new RegionDao();

    @Override
    public List<City> getCities(String districtId) {
        List<City> cities = cityDao.getByFieldName("districtID", districtId);
        LOGGER.info("{} cities returned from DistrictID={}", cities.size(), districtId);
        return cities;
    }

    @Override
    public List<District> getDistricts(String regionId) {
        List<District> districts = districtDao.getByFieldName("regionID", regionId);
        LOGGER.info("{} districts returned from RegionID={}", districts.size(), regionId);
        return districts;
    }

    @Override
    public List<Region> getRegions() {
        return regiontDao.getAll();
    }

    @Override
    public City getCity(String id) {
        City city = cityDao.getById(id);
        LOGGER.info("By CityID={} returned {}", id, city.getName());
        return city;
    }
    
    @Override
    public Region getRegion(String id) {
        Region region = regiontDao.getById(id);
        LOGGER.info("By RegionID={} returned {}", id, region.getName());
        return region;
    }
    
    @Override
    public District getDistrict(String id) {
        District district = districtDao.getById(id);
        LOGGER.info("By DistrictID={} returned {}", id, district.getName());
        return district;
    }


}
