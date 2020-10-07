package test;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AccountTest {
	
	
	
	@Test
	public void saveTest() {
		given()
		.when().post("http://localhost:8083/account/save")
		.then()
		.body()
	}
	
	@Test
	public void deleteTest() {
		
	}
	
	@Test
	public void updateTest() {
		
	}
	
	@Test
	public void getAllTest() {
		
	}
	
	@Test void getByIdTest() {
		
	}
}
