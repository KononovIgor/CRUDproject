package dao;

import exceptions.GenericDaoException;
import model.BaseEntity;
import model.User;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GenericDaoImpl <T extends BaseEntity<PK>, PK extends Serializable>
        implements GenericDao <T, PK> {

    private Class<T> type;
    private DataSource dataSource;

    protected GenericDaoImpl(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
    }

    @Override
    public void create(T objectToCreate) {
        try (Connection connection = dataSource.getConnection()) {
            prepareConnection(connection);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(objectToCreate.getCreateSql());
            objectToCreate.prepareCreateStatement(preparedStatement);
            preparedStatement.execute();
            connection.commit();
        }catch (SQLException exception) {
            throw new GenericDaoException("Error with saving "
                    + objectToCreate.getClass().getSimpleName()
                    + " to data base");
        }
    }

    @Override
    public LinkedList<T> read() {
        LinkedList<T> allEntryLists = new LinkedList<T>();
        T objectToRead = null;
        try (Connection connection = dataSource.getConnection()) {
            prepareConnection(connection);
            objectToRead = type.newInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(objectToRead.getReadSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T objectForList = type.newInstance();
                objectForList.setDataFromResultSet(resultSet);
                allEntryLists.add(objectForList);
            }
            connection.commit();
            return allEntryLists;
        } catch (SQLException | InstantiationException | IllegalAccessException exception) {
            throw new GenericDaoException("Problem with reading", exception);
        }
    }

    @Override
    public void update(T objectToUpdate, PK id) {
        try (Connection connection = dataSource.getConnection()) {
            prepareConnection(connection);
            PreparedStatement preparedStatement = connection.prepareStatement(objectToUpdate.getUpdateSql());
            objectToUpdate.prepareUpdateStatement(preparedStatement, id);
            int countOfUpdateRows = preparedStatement.executeUpdate();
            if (countOfUpdateRows == 1) {
                connection.commit();
            } else {
                connection.rollback();
                throw new GenericDaoException("Problem with update entry");
            }
        } catch (SQLException exception) {
            throw new GenericDaoException("Problem with update entry", exception);
        }
    }

    @Override
    public void delete(PK id) {
        T objectToDelete = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            prepareConnection(connection);
            objectToDelete = type.newInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(objectToDelete.getDeleteSql());
            objectToDelete.prepareDeleteStatement(preparedStatement, id);
            int deleteRows = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException | InstantiationException | IllegalAccessException exception) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollBackException) {
                    rollBackException.printStackTrace();
                }
            }
            throw new GenericDaoException("Problem with deleting", exception);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<PK> getAllId() {
        List<PK> allIdList = new LinkedList<PK>();
        GenericDao<T, PK> objectDao = AbstractDaoFactory.getDaoFactory(this.type).getDao();
        List<T> allEntryList = objectDao.read();
        for (T entry : allEntryList) {
            allIdList.add(entry.getId());
        }
        return allIdList;
    }

    private void prepareConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
}
