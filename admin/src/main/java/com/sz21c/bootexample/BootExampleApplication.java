package com.sz21c.bootexample;

import com.sz21c.bootexample.dao.AirplaneRepository;
import com.sz21c.bootexample.dao.ManufactureRepository;
import com.sz21c.bootexample.domain.AirplaneEntity;
import com.sz21c.bootexample.domain.ManufactureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@SpringBootApplication
@EnableCaching
@EntityScan(basePackages = "com.sz21c.bootexample.domain")
public class BootExampleApplication implements CommandLineRunner {

	@Autowired
	AirplaneRepository airplaneRepository;

	@Autowired
	ManufactureRepository manufactureRepository;

	public static void main(String[] args) {
		SpringApplication.run(BootExampleApplication.class, args);
	}

	@Override
	public void run(String... args) {
		manufactureRepository.save(new ManufactureEntity("Boeing", "US"));
		manufactureRepository.save(new ManufactureEntity("Airbus", "EU"));

		List<ManufactureEntity> manufactureEntitieList = manufactureRepository.findAll();
		for(ManufactureEntity manufactureEntity : manufactureEntitieList) {
			System.out.println("[" + manufactureEntity.getId() + "] " + manufactureEntity.getName() + " is in " + manufactureEntity.getCountry());
		}
		
		airplaneRepository.save(new AirplaneEntity("B777", 2, manufactureRepository.findByName("Boeing")));
		airplaneRepository.save(new AirplaneEntity("B747", 4, manufactureRepository.findByName("Airbus")));
		
		List<AirplaneEntity> airplaneEntityList = airplaneRepository.findAll();
		for(AirplaneEntity airplaneEntity : airplaneEntityList) {
			System.out.println("[" + airplaneEntity.getId() + "] " + airplaneEntity.getName() + ", has " + airplaneEntity.getNumberOfEngine() + " engines.");
		}
	}
}
