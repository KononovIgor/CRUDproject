package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseEntityInterface <PK extends Serializable> {
    String getCreateSql();
    String getReadSql();
    String getUpdateSql();
    String getDeleteSql();

    PK getId();
    void setId(PK id);

    void prepareCreateStatement(PreparedStatement preparedStatement) throws SQLException;
    void setDataFromResultSet(ResultSet resultSet) throws SQLException;
    void prepareDeleteStatement(PreparedStatement statement, PK id) throws SQLException;
    void prepareUpdateStatement(PreparedStatement statement, PK id) throws SQLException;
}
