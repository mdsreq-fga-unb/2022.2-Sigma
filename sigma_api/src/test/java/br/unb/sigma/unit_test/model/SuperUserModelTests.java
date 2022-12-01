package br.unb.sigma.unit_test.model;

import br.unb.sigma.model.SuperUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperUserModelTests {
	@Test
	void createSuperUser__usingConstructor__shouldSucceed() {
		// Arrange
		String email = "email@email.com";
		String password = "password";

		// Act
		var superUser = new SuperUser(
				email,
				password
		);

		// Assert
		assertThat(superUser.getId()).isNull();
		assertThat(superUser.getEmail()).isEqualTo(email);
		assertThat(superUser.getPassword()).isEqualTo(password);
	}

	@Test
	void createSuperUser__usingBuilder__shouldSucceed() {
		// Arrange
		String email = "email@email.com";
		String password = "password";

		// Act
		var superUser = SuperUser.builder()
				.email(email)
				.password(password)
				.build();

		// Assert
		assertThat(superUser.getId()).isNull();
		assertThat(superUser.getEmail()).isEqualTo(email);
		assertThat(superUser.getPassword()).isEqualTo(password);
	}

	@Test
	void toString__withValidSuperUser__shouldSucceed() {
		// Arrange
		var superUser = SuperUser.builder()
				.id(1)
				.email("email@email.com")
				.password("password")
				.build();

		// Act
		var superUserString = superUser.toString();

		// Assert
		assertThat(superUserString).isEqualTo(
				"SuperUser(id = 1, email = email@email.com, password = password)"
		);
	}

	@Test
	void equals__withSameSuperUser__shouldReturnTrue() {
		// Arrange
		var superUser1 = SuperUser.builder()
				.id(1)
				.email("email@email.com")
				.password("password")
				.build();

		var superUser2 = SuperUser.builder()
				.id(1)
				.email("email2@email.com")
				.password("password2")
				.build();

		// Act and Assert
		assertThat(superUser1).isEqualTo(superUser2);
	}

	@Test
	void equals__withDifferentSuperUser__shouldReturnFalse() {
		// Arrange
		var superUser1 = SuperUser.builder()
				.id(1)
				.email("email@email.com")
				.password("password")
				.build();

		var superUser2 = SuperUser.builder()
				.id(2)
				.email("email@email.com")
				.password("password")
				.build();

		// Act and Assert
		assertThat(superUser1).isNotEqualTo(superUser2);
	}

	@Test
	void hashCode__withSameSuperUser__shouldReturnSameHashCode() {
		// Arrange
		var superUser1 = SuperUser.builder()
				.id(1)
				.email("email@email.com")
				.password("password")
				.build();

		var superUser2 = SuperUser.builder()
				.id(1)
				.email("email@email.com")
				.password("password")
				.build();

		// Act and Assert
		assertThat(superUser1.hashCode()).isEqualTo(superUser2.hashCode());
	}

	@Test
	void hashCode__withDifferentSuperUser__shouldReturnDifferentHashCode() {
		// Arrange
		var superUser1 = SuperUser.builder()
				.id(1)
				.email("email@email.com")
				.password("password")
				.build();

		var superUser2 = SuperUser.builder()
				.id(2)
				.email("email@email.com")
				.password("password")
				.build();

		// Act and Assert
		assertThat(superUser1.hashCode()).isNotEqualTo(superUser2.hashCode());
	}
}
