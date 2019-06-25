package com.sz21c.bootexample.dao;

import com.sz21c.bootexample.domain.ManufactureEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManufactureRepositoryTest {
	
	@Autowired
	ManufactureRepository manufactureRepository;
	
	@Test
	public void test_manufacture2ndLevelCache() {
		System.out.println("아직 2차 캐시에 없음");
		ManufactureEntity boeing = manufactureRepository.findByName("Boeing");
		System.out.println("0. " + boeing.toString());
		System.out.println("이제 한번 읽었으니 2차 캐시에 올라갔음. 단, entity의 id로 cache의 key가 잡히므로 id로 찾을 경우에만 캐시를 탄다. (아직까지는...)");
		Optional<ManufactureEntity> boeing2 = manufactureRepository.findById(1L);
		System.out.println("1. " + boeing2.toString() + " ==> id로 찾았음 (캐시탐)");
		boeing2 = Optional.ofNullable(manufactureRepository.findByName("Boeing"));
		System.out.println("2. " + boeing2.toString() + " ==> 이름으로 찾았음 (캐시타지 않음)");
		boeing2 = manufactureRepository.findById(1L);
		System.out.println("3. " + boeing2.toString() + " ==> id로 찾았음 (캐시탐)");
		boeing2 = manufactureRepository.findById(1L);
		System.out.println("4. " + boeing2.toString() + " ==> id로 찾았음 (캐시탐)");
		boeing2 = Optional.ofNullable(manufactureRepository.findByName("Boeing"));
		System.out.println("5. " + boeing2.toString() + " ==> 이름으로 찾았음 (캐시타지 않음)");
	}
}
