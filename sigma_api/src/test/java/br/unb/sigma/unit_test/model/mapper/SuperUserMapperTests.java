package br.unb.sigma.unit_test.model.mapper;

import br.unb.sigma.model.SuperUser;
import br.unb.sigma.model.dto.request.SuperUserPartialUpdateDTO;
import br.unb.sigma.model.mapper.SuperUserMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperUserMapperTests {
	@Test
	void mapSuperUserPartialUpdateDTOToSuperUser__withAllDataPresent__shouldSucceed() {
		// Arrange
		var dto = new SuperUserPartialUpdateDTO(
				"email@example.com",
				"password"
		);

		var superUser = new SuperUser();

		// Act
		var updatedSuperUser = SuperUserMapper.updateEntity(superUser, dto);

		// Assert
		assertThat(updatedSuperUser.getEmail()).isEqualTo(dto.email());
		assertThat(updatedSuperUser.getPassword()).isEqualTo(dto.password());
	}

	@Test
	void mapSuperUserPartialUpdateDTOToSuperUser__withNullEmail__shouldSucceed() {
		// Arrange
		var dto = new SuperUserPartialUpdateDTO(
				null,
				"password"
		);

		var superUser = SuperUser.builder()
				.email("email@example.com")
				.build();

		// Act
		var updatedSuperUser = SuperUserMapper.updateEntity(superUser, dto);

		// Assert
		assertThat(updatedSuperUser.getEmail()).isEqualTo(superUser.getEmail());
		assertThat(updatedSuperUser.getPassword()).isEqualTo(dto.password());
	}
}
