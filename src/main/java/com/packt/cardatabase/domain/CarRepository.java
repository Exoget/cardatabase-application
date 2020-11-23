package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long>{
	
	// auto mapped query field search (findBy + entity class field)
	List<Car> findByColor(String color);
	List<Car> findByBrand(String brand);
	List<Car> findByYear(int year);

	
	List<Car> findByBrandAndModel(String brand, String model);
	List<Car> findByBrandAndColor(String brand, String color);
	
	List<Car> findByBrandOrderByYearAsc(String brand);
	
	@Query("select c from Car c where c.brand = ?1")
	List<Car> findByBrandSql(String brand);
	
	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrandEndsWith(String brand);
}
