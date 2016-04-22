package com.pikaso.rest.context;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DistrictDao;
import com.pikaso.rest.dao.RegionDao;

public class InitDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDB.class);
    
    public static void okTables() {
        
        DatabaseMetaData meta;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            meta = connection.getMetaData();
            ResultSet rs = meta.getTables(null, null, Constants.TABLE_NAME_CITY, null);
            if (!rs.next()) {
                new CityDao().createTable();
            }
            rs.close();
            rs = meta.getTables(null, null, Constants.TABLE_NAME_DISTRICT, null);
            if (!rs.next()) {
                new DistrictDao().createTable();
            }
            rs.close();
            rs = meta.getTables(null, null, Constants.TABLE_NAME_REGION, null);
            if (!rs.next()) {
                new RegionDao().createTable();
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Can't create datatables",e);
            throw new RuntimeException(e);
        }
    }
}
