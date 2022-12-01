package br.unb.sigma.controller.service;

import br.unb.sigma.controller.exception.DatabaseOperationException;
import br.unb.sigma.controller.exception.RegisterNotFoundException;
import br.unb.sigma.model.dto.request.SuperUserPartialUpdateDTO;
import br.unb.sigma.model.mapper.SuperUserMapper;
import br.unb.sigma.model.repository.SuperUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuperUserService {
	private final SuperUserRepository superUserRepository;

	public void updateSuperUser(Integer id, SuperUserPartialUpdateDTO dto) {
		var superUser = superUserRepository.findById(id).orElseThrow(RegisterNotFoundException::new);

		var updatedSuperUser = SuperUserMapper.updateEntity(superUser, dto);

		try {
			superUserRepository.save(updatedSuperUser);
		} catch (DataIntegrityViolationException ex) {
			throw new DatabaseOperationException(ex);
		}
	}
}
