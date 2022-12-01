package br.unb.sigma.unit_test.model.repository;

import br.unb.sigma.model.SuperUser;
import br.unb.sigma.model.repository.SuperUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SuperUserRepositoryTests {
	@Autowired
	private SuperUserRepository superUserRepository;

	@AfterEach
	void cleanUp() {
		superUserRepository.deleteAll();
	}

	@Test
	void createSuperUser__withValidData__ShouldPersistANewSuperUser() {
		// Arrange
		var superUser = new SuperUser(
				"email@example.com",
				"password"
		);

		// Act
		var createdSuperUser = superUserRepository.save(superUser);

		// Assert
		assertThat(createdSuperUser.getId()).isNotNull();
		assertThat(createdSuperUser.getEmail()).isEqualTo("email@example.com");
		assertThat(createdSuperUser.getPassword()).isEqualTo("password");
	}

	@Test
	void createSuperUser__withInvalidEmail__ShouldThrowAnError() {
		// Arrange
		var superUser = SuperUser.builder()
				.password("password")
				.build();

		// Act and Assert
		assertThatThrownBy(
				() -> superUserRepository.save(superUser)
		).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void createSuperUser__withInvalidPassword__ShouldThrowAnError() {
		// Arrange
		var superUser = SuperUser.builder()
				.email("email@example.com")
				.build();

		// Act and Assert
		assertThatThrownBy(
				() -> superUserRepository.save(superUser)
		).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void createTwoSuperUser__withSameEmail__ShouldThrowAnError() {
		// Arrange
		var superUser1 = SuperUser.builder()
				.email("email@example.com")
				.password("password")
				.build();

		var superUser2 = SuperUser.builder()
				.email("email@example.com")
				.password("password2")
				.build();

		// Act and Assert
		assertThatThrownBy(
				() -> superUserRepository.saveAll(List.of(superUser1, superUser2))
		).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void updateSuperUser__withValidData__ShouldReturnUpdatedSuperUser() {
		// Arrange
		var superUser = SuperUser.builder()
				.email("email@example.com")
				.password("password")
				.build();

		var createdSuperUser = superUserRepository.save(superUser);

		// Act
		createdSuperUser.setEmail("altered.email@example.com");
		createdSuperUser.setPassword("altered.password");

		var updatedSuperUser = superUserRepository.save(createdSuperUser);

		// Assert
		assertThat(updatedSuperUser.getId()).isEqualTo(createdSuperUser.getId());
		assertThat(updatedSuperUser.getEmail()).isEqualTo("altered.email@example.com");
		assertThat(updatedSuperUser.getPassword()).isEqualTo("altered.password");
	}

	@Test
	void deleteSuperUser__withValidData__ShouldDeleteSuperUser() {
		// Arrange
		var superUser = SuperUser.builder()
				.email("email@example.com")
				.password("password")
				.build();

		var createdSuperUser = superUserRepository.save(superUser);

		// Act
		superUserRepository.delete(createdSuperUser);

		// Assert
		assertThat(superUserRepository.findById(createdSuperUser.getId())).isEmpty();
	}

	@Test
	void findSuperUser__withValidData__ShouldReturnSuperUser() {
		// Arrange
		var superUser = SuperUser.builder()
				.email("email@example.com")
				.password("password")
				.build();

		var createdSuperUser = superUserRepository.save(superUser);

		// Act
		var foundSuperUser = superUserRepository.findById(createdSuperUser.getId());

		// Assert
		assertThat(foundSuperUser).isNotEmpty();

		var superUserFound = foundSuperUser.get();

		assertThat(superUserFound.getId()).isEqualTo(createdSuperUser.getId());
		assertThat(superUserFound.getEmail()).isEqualTo("email@example.com");
		assertThat(superUserFound.getPassword()).isEqualTo("password");
	}

	@Test
	void findSuperUser__withInvalidData__ShouldReturnEmpty() {
		// Arrange and Act
		var foundSuperUser = superUserRepository.findById(1);

		// Assert
		assertThat(foundSuperUser).isEmpty();
	}
}
