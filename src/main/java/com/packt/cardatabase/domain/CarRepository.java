package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// pour ajouter les requetes dans la liste des service generer Spring Data Rest
@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {

	// auto mapped query field search (findBy + entity class field)
	List<Car> findByColor(@Param("color") String color);

	List<Car> findByBrand(String brand);

	List<Car> findByYear(@Param("year") int year);

	List<Car> findByBrandAndModel(String brand, String model);

	List<Car> findByBrandAndColor(String brand, String color);

	List<Car> findByBrandOrderByYearAsc(String brand);

	@Query("select c from Car c where c.brand = ?1")
	List<Car> findByBrandSql(String brand);

	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrandEndsWith(String brand);

}
