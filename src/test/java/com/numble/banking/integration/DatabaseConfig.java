package com.numble.banking.integration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig implements InitializingBean {

	@PersistenceContext
	private EntityManager em;
	private List<String> tableNames;

	@Override
	public void afterPropertiesSet() throws Exception {
		em.unwrap(Session.class).doWork(this::getTableNames);
	}

	private void getTableNames(Connection connection) throws SQLException {
		this.tableNames = new ArrayList<>();

		ResultSet resultSet = connection.getMetaData()
			.getTables(null, null, null, new String[]{"TABLE"});

		try (resultSet){
			while (resultSet.next()) {
				System.out.println("======== " + resultSet.getString("table_name"));
				tableNames.add(resultSet.getString("table_name"));
			}
		}
	}

	public void clear() {
		em.unwrap(Session.class).doWork(this::clearDb);
	}

	private void clearDb(Connection connection) throws SQLException {
		try(Statement statement = connection.createStatement()) {
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");

			for (String tableName : tableNames) {
				statement.executeUpdate("TRUNCATE TABLE " + tableName);
			}

			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
		}
	}

	public void initDb() {

	}
}
