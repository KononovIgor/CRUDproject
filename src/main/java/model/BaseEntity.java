package model;

import exceptions.GenericDaoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public abstract class BaseEntity <PK extends Serializable> implements BaseEntityInterface<PK> {
    protected PK id;
    protected final String CREATE_SQL;
    protected final String READ_SQL;
    protected final String UPDATE_SQL;
    protected final String DELETE_SQL;

    private static final String RESOURCES_DB_PROPERTIES =
            "C:\\Users\\kiv\\IdeaProjects\\CRUDproject\\src\\main\\resources\\db.properties";
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(new File(RESOURCES_DB_PROPERTIES))) {
            properties.load(fis);

            CREATE_SQL = properties.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".create");
            READ_SQL = properties.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".read");
            UPDATE_SQL = properties.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".update");
            DELETE_SQL = properties.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".delete");
        } catch (IOException exception) {
            throw new GenericDaoException("Error loading SQL definitions", exception);
        }
    }

    @Override
    public String getCreateSql() {
        return CREATE_SQL;
    }

    @Override
    public String getReadSql() {
        return READ_SQL;
    }

    @Override
    public String getUpdateSql() {
        return UPDATE_SQL;
    }

    @Override
    public String getDeleteSql() {
        return DELETE_SQL;
    }

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}
