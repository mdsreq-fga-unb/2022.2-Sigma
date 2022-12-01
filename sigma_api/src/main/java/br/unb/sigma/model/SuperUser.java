package br.unb.sigma.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "super_user")
public class SuperUser {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false, length = 100)
	private String password;

	public SuperUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		SuperUser superUser = (SuperUser) o;
		return id != null && Objects.equals(id, superUser.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, password);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"email = " + email + ", " +
				"password = " + password + ")";
	}
}
