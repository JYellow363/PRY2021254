package pe.edu.upc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "children")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 80, nullable = false)
	private String names;

	@Column(length = 80, nullable = false)
	private String lastNames;

	@Column(nullable = false)
	private Date birthday;

	@Column(length = 10, nullable = false)
	private String gender;

	@Column(length = 20, nullable = false)
	private String asdLevel;

	@Column(nullable = false)
	private String avatar;
	
	@Column(nullable = false)
	private String specialCategoryName;
	
	@ManyToMany
	private List<Symptom> symptoms;

	@ManyToOne
	private Guardian guardian;

	@ManyToMany
	private List<Level> favoriteLevels;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CustomLevelList> customLevelLists;

	@OneToOne(mappedBy = "child", cascade = CascadeType.ALL)
	private Specialist specialist;
}
