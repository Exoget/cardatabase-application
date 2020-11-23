package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication {

	private final static Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);
	
	// Using The SimpleJpaRepository Class Default implementation
	@Autowired
	private CarRepository carRep;
	
	@Autowired
	private OwnerRepository ownerRep;
	
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("SpringApplication Is Started ...");	
		
	}
	
	
	@Bean
	CommandLineRunner run() {
		return (args) ->{
			logger.info("Debut CallBack ...");
			Owner fares = new Owner("FERCHICHI", "Fares");
			ownerRep.save(fares);
			carRep.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, fares));
			carRep.save(new Car("Pego", "306", "Black", "ADF-1122", 2017, 60000, fares));
			carRep.save(new Car("Nissan", "Leaf", "white", "SSJ-3002", 2014, 29000, ownerRep.save(new Owner("MESOUDI", "Rayen"))));
			logger.info("Fin CallBack ...");
		};
	}

	
}
