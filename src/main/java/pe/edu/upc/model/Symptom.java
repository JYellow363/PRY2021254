package pe.edu.upc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Symptom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSymptom;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Child> children;
}
