package repository;

import entity.QuantityMeasurementEntity;
import exception.DatabaseException;
import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql =
                "INSERT INTO quantity_measurement_entity(operation,this_quantity,that_quantity,result) VALUES(?,?,?,?)";

        Connection connection = null;

        try {

            connection = ConnectionPool.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, entity.getOperation());
            statement.setString(2, entity.getThisQuantity());
            statement.setString(3, entity.getThatQuantity());
            statement.setString(4, entity.getResult());

            statement.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException("Database error", e);

        } finally {

            ConnectionPool.release(connection);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        return new ArrayList<>();
    }
}