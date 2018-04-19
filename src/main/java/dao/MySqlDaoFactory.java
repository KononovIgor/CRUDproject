package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exceptions.GenericDaoException;
import model.BaseEntity;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MySqlDaoFactory<T extends BaseEntity> extends AbstractDaoFactory {
    private static final String RESOURCES_DB_PROPERTIES =
            "C:\\Users\\kiv\\IdeaProjects\\CRUDproject\\src\\main\\resources\\db.properties";
    private static final String DB_USER         = "db.user";
    private static final String DB_PASSWORD     = "db.password";
    private static final String DB_PORT         = "db.port";
    private static final String DB_HOST         = "db.host";
    private static final String DB_SCHEMA       = "db.schema";

    private Class<T> type;
    protected DataSource dataSource;

    public MySqlDaoFactory(Class<T> type) {
        this.type = type;
        this.dataSource = createDataSourceAndLoadProperties();
    }

    @Override
    public GenericDao getDao() {
        return new GenericDaoImpl(dataSource, type);
    }

    private DataSource createDataSourceAndLoadProperties() {
        Properties properties = new Properties();
        MysqlDataSource dataSource = new MysqlDataSource();
        try (FileInputStream fis = new FileInputStream(
                new File(RESOURCES_DB_PROPERTIES))) {

            properties.load(fis);
            dataSource.setUser(                 properties.getProperty(DB_USER));
            dataSource.setPassword(             properties.getProperty(DB_PASSWORD));
            dataSource.setPort(Integer.parseInt(properties.getProperty(DB_PORT)));
            dataSource.setServerName(           properties.getProperty(DB_HOST));
            dataSource.setDatabaseName(         properties.getProperty(DB_SCHEMA));

        } catch (IOException exception) {
            throw new GenericDaoException("Problem with loading properties", exception);
        }
        return dataSource;
    }
}
