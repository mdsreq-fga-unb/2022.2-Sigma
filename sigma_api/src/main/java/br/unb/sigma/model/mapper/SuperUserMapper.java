package br.unb.sigma.model.mapper;

import br.unb.sigma.model.SuperUser;
import br.unb.sigma.model.dto.request.SuperUserPartialUpdateDTO;

public class SuperUserMapper {
	public static SuperUser updateEntity(SuperUser originalEntity, SuperUserPartialUpdateDTO dto) {
		var entity = new SuperUser();

		entity.setId(originalEntity.getId());

		if (dto.email() != null) {
			entity.setEmail(dto.email());
		} else {
			entity.setEmail(originalEntity.getEmail());
		}

		if (dto.password() != null) {
			entity.setPassword(dto.password());
		} else {
			entity.setPassword(originalEntity.getPassword());
		}

		return entity;
	}
}
