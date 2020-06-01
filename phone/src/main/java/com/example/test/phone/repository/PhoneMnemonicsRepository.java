package com.example.test.phone.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.test.phone.model.PhoneMnemonics;

public interface PhoneMnemonicsRepository extends JpaRepository<PhoneMnemonics, Long> , PagingAndSortingRepository<PhoneMnemonics, Long>{
	
	Page<PhoneMnemonics> findAllByPhoneNumber(String phoneNumbere, Pageable pageable);
	 
	 long countByPhoneNumber(String phoneNumbere);
	 
	 List<PhoneMnemonics> findAllByPhoneNumber(String phoneNumbere);

}
