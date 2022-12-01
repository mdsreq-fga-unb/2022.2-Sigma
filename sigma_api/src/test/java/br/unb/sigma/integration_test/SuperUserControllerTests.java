package br.unb.sigma.integration_test;

import br.unb.sigma.model.SuperUser;
import br.unb.sigma.model.dto.request.SuperUserPartialUpdateDTO;
import br.unb.sigma.model.repository.SuperUserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class SuperUserControllerTests {
	@Autowired
	private SuperUserRepository superUserRepository;

	@BeforeAll
	public static void setup() {
		RestAssured.basePath = "/api/superuser";
		RestAssured.port = 8888;
	}

	@AfterEach
	public void cleanUp() {
		superUserRepository.deleteAll();
	}

	@Test
	void updateSuperUser__withValidData__shouldReturnNoContent() {
		// Arrange
		var originalSuperUser = superUserRepository.save(new SuperUser(
				"email@example.com",
				"password"
		));

		var id = originalSuperUser.getId();

		var dto = new SuperUserPartialUpdateDTO(
				"email2@example.com",
				"password2"
		);

		// Act
		var response = given()
				.body(dto).contentType(APPLICATION_JSON_VALUE)
				.when()
				.patch("/{id}", id)
				.then()
				.extract().response();

		// Assert
		assertThat(response.statusCode()).isEqualTo(204);

		var updatedSuperUser = superUserRepository.findById(id).orElseThrow();

		assertThat(updatedSuperUser.getEmail()).isEqualTo(dto.email());
		assertThat(updatedSuperUser.getPassword()).isEqualTo(dto.password());
	}
}
