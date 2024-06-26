package com.example.UnitTesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.UnitTesting.dto.BlanaceCheck;
import com.example.UnitTesting.entity.BankAc;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAc, Integer> {

	@Query(value = "select balance_amount as Balance from bank_accuont_details where id=:id",nativeQuery = true)
	BlanaceCheck findBybalance(int id);

	@Query(value = "select balance_amount as balance from bank_accuont_details where id=:id",nativeQuery = true)
	Optional<Object> findbalance(int id);

	
}
