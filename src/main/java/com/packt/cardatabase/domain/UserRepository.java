package com.packt.cardatabase.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//pour ajouter les requetes dans la liste des service genere par defaut par Spring Data Rest
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

	// auto mapped query field search (findBy + entity class field)
		User findByUserName(@Param("userName") String userName);
}
