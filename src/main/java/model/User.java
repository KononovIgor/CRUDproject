package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User extends BaseEntity<Integer> {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
    }

    @Override
    public void setDataFromResultSet(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        name = resultSet.getString(2);
        age = resultSet.getInt(3);
    }

    @Override
    public void prepareDeleteStatement(PreparedStatement preparedStatement, Integer id) throws SQLException {
        preparedStatement.setInt(1, id);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setInt(3, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
