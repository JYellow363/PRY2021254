package pe.edu.upc.model;

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
public class Specialist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSpecialist;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Child child;
	
	@Column(length = 80, nullable = false)
	private String names;
	
	@Column(length = 80, nullable = false)
	private String lastNames;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserLogin userLogin;
}
