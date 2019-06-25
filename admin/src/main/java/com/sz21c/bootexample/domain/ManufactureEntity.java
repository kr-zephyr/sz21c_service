package com.sz21c.bootexample.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY, region="manufacture")
public class ManufactureEntity implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String country;

	public ManufactureEntity(String name, String country) {
		this.name = name;
		this.country = country;
	}
}
