package com.example.test.phone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.test.phone.model.PhoneMnemonics;
import com.example.test.phone.model.PhoneMnemonicsReq;


public interface TelephoneService {
	
	public Optional<Page<PhoneMnemonics>> getPhoneMnemonics(String phoneNumber, Pageable pageable) throws Exception;
	
	public Optional<List<PhoneMnemonics>> getPhoneMnemonics(String phoneNumber) throws Exception;
	
	
	public List<PhoneMnemonics>  createPhoneMnemonics(PhoneMnemonicsReq mnemonics)  throws Exception;
	
	
}
