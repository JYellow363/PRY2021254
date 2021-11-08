package pe.edu.upc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idChild;

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

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Symptom> symptoms;

	@ManyToOne
	private Guardian guardian;

	@OneToMany
	private List<Level> favoriteLevels;

	@OneToMany
	private List<Topic> favoriteTopics;

	@OneToOne(mappedBy = "child", cascade = CascadeType.ALL)
	private Specialist specialist;
}
