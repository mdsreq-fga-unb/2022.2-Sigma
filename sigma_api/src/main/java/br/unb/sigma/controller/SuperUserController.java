package br.unb.sigma.controller;

import br.unb.sigma.controller.service.SuperUserService;
import br.unb.sigma.model.dto.request.SuperUserPartialUpdateDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/superuser")
public class SuperUserController {
	private final SuperUserService superUserService;

	@PatchMapping("{id}")
	public ResponseEntity<Void> updateSuperUser(@PathVariable Integer id, @RequestBody @Valid SuperUserPartialUpdateDTO dto) {
		superUserService.updateSuperUser(id, dto);
		return ResponseEntity.noContent().build();
	}
}
