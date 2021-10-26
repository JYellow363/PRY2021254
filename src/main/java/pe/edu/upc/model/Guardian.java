package pe.edu.upc.model;

import java.util.Date;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGuardian;
	
	@Column(length = 80, nullable = false)
	private String names;
	
	@Column(length = 80, nullable = false)
	private String lastNames;
	
	@Column(nullable = false)
	private Date birthday;
	
	@Column(length = 80, nullable = false)
	private String email;
	
	@OneToOne(mappedBy="guardian")
	private UserLogin userLogin;
	
}
