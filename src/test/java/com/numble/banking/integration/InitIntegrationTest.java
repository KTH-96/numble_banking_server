package com.numble.banking.integration;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class InitIntegrationTest {

	@Autowired
	protected DatabaseConfig db;

	@BeforeEach
	void setUpDb() {
		db.clear();
		db.initDb();
	}
}
