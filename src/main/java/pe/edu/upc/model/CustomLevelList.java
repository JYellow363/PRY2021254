package pe.edu.upc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomLevelList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCustomLevelList;

	@OneToMany
	private List<Level> levels;

}
