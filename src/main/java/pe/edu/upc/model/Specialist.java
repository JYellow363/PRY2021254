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
@Table(name = "specialists")
public class Specialist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Child child;
	
	@Column(length = 80, nullable = false)
	private String names;
	
	@Column(length = 80, nullable = false)
	private String lastNames;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Users user;
}
