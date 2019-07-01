package com.galaxia.clima.util;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxia.clima.config.Config;

public class InterfaceDb {

	private static final Logger LOG = LoggerFactory.getLogger(InterfaceDb.class);

	public static Connection getConnection() throws Exception {
		InitialContext context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup(Config.getDatasourceJndiName());
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setSchema(Config.getDatasourceSchemaName());
			con.setAutoCommit(true);
		} catch (Exception e) {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				LOG.error(e2.getMessage(), e2);
			}
			LOG.error(e.getMessage(), e);
			throw e;
		}
		return con;
	}

}