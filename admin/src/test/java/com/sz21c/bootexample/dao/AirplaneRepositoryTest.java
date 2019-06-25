package com.sz21c.bootexample.dao;

import com.sz21c.bootexample.domain.AirplaneEntity;
import com.sz21c.bootexample.domain.ManufactureEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirplaneRepositoryTest {

	@Autowired
	AirplaneRepository airplaneRepository;

	@Autowired
	ManufactureRepository manufactureRepository;
	
	@Test
	@Transactional
	public void test_saveAndRead() {
		int preSize = airplaneRepository.findAll().size();
		ManufactureEntity boeing = manufactureRepository.findByName("Boeing");
		
		airplaneRepository.save(new AirplaneEntity("B737", 2, boeing != null ? boeing : createTestManufactureBoeing()));
		airplaneRepository.save(new AirplaneEntity("B787", 2, boeing != null ? boeing : createTestManufactureBoeing()));

		List<AirplaneEntity> airplaneEntityList = airplaneRepository.findAll();

		Assert.assertEquals(airplaneEntityList.size(), 2 + preSize);
		
		System.out.println("manufacture :: " + airplaneEntityList.get(0).getManufactureEntity().getName());
	}
	
	private ManufactureEntity createTestManufactureBoeing() {
		ManufactureEntity boeing = new ManufactureEntity("Boeing", "US");
		manufactureRepository.save(boeing);
		return boeing;
	}
}