package pe.edu.upc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUserLogin;

	@Column(length = 60, nullable = false)
	private String username;

	@Column(length = 60, nullable = false)
	private String password;

	private boolean isActive;

	@OneToOne(mappedBy = "userLogin")
	private Guardian guardian;

	@OneToOne(mappedBy = "userLogin")
	private Specialist specialist;
}
