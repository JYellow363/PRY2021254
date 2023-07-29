package pe.edu.upc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "level_records")
public class LevelRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JsonIgnore
	private Level level;
	
	@Column(nullable = false)
	private Date date;
	
	private boolean isSuccessful;
	
	@ManyToOne
	@JsonIgnore
	private Child child;
}
