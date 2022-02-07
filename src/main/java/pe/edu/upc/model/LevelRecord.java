package pe.edu.upc.model;

import java.util.Date;

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
public class LevelRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLevelRecord;
	
	@ManyToOne
	private Level level;
	
	@Column(nullable = false)
	private Date date;
	
	private boolean isSuccessful;
	
	@ManyToOne
	private Child child;
}
