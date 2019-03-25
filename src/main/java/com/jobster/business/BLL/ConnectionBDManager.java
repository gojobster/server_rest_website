package com.jobster.business.BLL;

import com.jobster.business.types.JobsterErrorType;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBDManager {
    private Connection conn;
    public DSLContext create;

    public ConnectionBDManager() throws JobsterException {
        try {
            Class.forName(Constantes.DB_DRIVER).newInstance();
            conn = DriverManager.getConnection(Constantes.DB_URL, Constantes.DB_USER, Constantes.DB_PASS);
            create = DSL.using(conn, SQLDialect.MYSQL);
        } catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException ex) {
            throw new JobsterException(JobsterErrorType.GENERIC_ERROR);
        }
    }

    public void closeConnection() throws JobsterException {
        try {
            conn.close();
            create.close();
        } catch (SQLException e) {
            throw new JobsterException(JobsterErrorType.CLOSE_BD_ERROR);
        }
    }
}
