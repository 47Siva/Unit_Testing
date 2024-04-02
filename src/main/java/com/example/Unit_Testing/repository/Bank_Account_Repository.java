package com.example.Unit_Testing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Unit_Testing.dto.BlanaceCheck;
import com.example.Unit_Testing.entity.Bank_AC;

@Repository
public interface Bank_Account_Repository extends JpaRepository<Bank_AC, Integer> {

	@Query(value = "select balance as Balance from bank_accuont_details where id=:id",nativeQuery = true)
	BlanaceCheck findBybalance(int id);

	@Query(value = "select balance as balance from bank_accuont_details where id=:id",nativeQuery = true)
	Optional<Object> findbalance(int id);

	
}
