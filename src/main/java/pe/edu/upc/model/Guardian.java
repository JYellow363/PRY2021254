package pe.edu.upc.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
public class Guardian {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGuardian;
	
	@Column(nullable = false)
	private boolean premium;
	
	@Column(length = 80, nullable = false)
	private String names;
	
	@Column(length = 80, nullable = false)
	private String lastNames;
	
	@Column(nullable = false)
	private Date birthday;
	
	@Column(length = 80, nullable = false)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserLogin userLogin;
	
}
