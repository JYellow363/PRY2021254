package pe.edu.upc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 60, nullable = false)
	private String username;

	@Column(length = 60, nullable = false)
	private String password;

	private boolean isActive;

	@OneToOne(mappedBy = "user")
	private Guardian guardian;

	@OneToOne(mappedBy = "user")
	private Specialist specialist;
}
