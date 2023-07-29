package pe.edu.upc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guardians")
public class Guardian {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
	private Users user;
	
}
