package br.unb.sigma.model.dto.request;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record SuperUserPartialUpdateDTO(
		@Email @Length(max = 100) String email,
		@Length(max = 100) String password
) {
}
