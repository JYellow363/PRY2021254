package pe.edu.upc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Level {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLevel;
	
	@Column(length = 60, nullable = false)
	private String description;
	
	@ManyToOne
	private Topic topic;
	
	@Column(nullable = false)
	private String video;
}
