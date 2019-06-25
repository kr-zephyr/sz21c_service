package com.sz21c.bootexample.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AirplaneEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private Integer numberOfEngine;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacture_id")
	private ManufactureEntity manufactureEntity;

	public AirplaneEntity(String name, Integer numberOfEngine, ManufactureEntity manufactureEntity) {
		this.name = name;
		this.numberOfEngine = numberOfEngine;
		this.manufactureEntity = manufactureEntity;
	}
}
