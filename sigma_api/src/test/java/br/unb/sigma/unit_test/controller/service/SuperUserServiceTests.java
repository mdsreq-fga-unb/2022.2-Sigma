package br.unb.sigma.unit_test.controller.service;

import br.unb.sigma.controller.exception.DatabaseOperationException;
import br.unb.sigma.controller.exception.RegisterNotFoundException;
import br.unb.sigma.controller.service.SuperUserService;
import br.unb.sigma.model.SuperUser;
import br.unb.sigma.model.dto.request.SuperUserPartialUpdateDTO;
import br.unb.sigma.model.mapper.SuperUserMapper;
import br.unb.sigma.model.repository.SuperUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SuperUserServiceTests {
	@Mock
	private SuperUserRepository superUserRepository;

	@InjectMocks
	private SuperUserService superUserService;

	@Test
	void updateSuperUser__withExistingSuperUserAndValidData__shouldSucceed() {
		// Arrange
		var originalSuperUser = SuperUser.builder()
				.id(1)
				.email("email@example.com")
				.password("password")
				.build();

		var dto = new SuperUserPartialUpdateDTO(
				"email2@example.com",
				"password2"
		);

		var updatedSuperUser = SuperUserMapper.updateEntity(originalSuperUser, dto);

		when(superUserRepository.findById(1)).thenReturn(Optional.of(originalSuperUser));

		// Act
		superUserService.updateSuperUser(1, dto);

		// Assert
		verify(superUserRepository).save(updatedSuperUser);
	}

	@Test
	void updateSuperUser__withNonExistingSuperUser__shouldThrowRegisterNotFoundException() {
		// Arrange
		var dto = new SuperUserPartialUpdateDTO(
				"email2@example.com",
				"password2"
		);

		when(superUserRepository.findById(1)).thenReturn(Optional.empty());

		// Act and Assert
		assertThatThrownBy(
				() -> superUserService.updateSuperUser(1, dto)
		).isInstanceOf(RegisterNotFoundException.class);
	}

	@Test
	void updateSuperUser__withExistingSuperUserAndInvalidData__shouldThrowDatabaseOperationException() {
		// Arrange
		var originalSuperUser = SuperUser.builder()
				.id(1)
				.email("email2@example.com")
				.password("password2")
				.build();

		var dto = new SuperUserPartialUpdateDTO(
				"invalid email",
				null
		);

		var updatedSuperUser = SuperUserMapper.updateEntity(originalSuperUser, dto);

		when(superUserRepository.findById(1)).thenReturn(Optional.of(originalSuperUser));
		doThrow(new DataIntegrityViolationException("mock", new Exception("mock")))
				.when(superUserRepository).save(updatedSuperUser);

		// Act and Assert
		assertThatThrownBy(
				() -> superUserService.updateSuperUser(1, dto)
		).isInstanceOf(DatabaseOperationException.class);
	}
}
