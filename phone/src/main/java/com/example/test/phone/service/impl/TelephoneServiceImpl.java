package com.example.test.phone.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.test.phone.model.PhoneMnemonics;
import com.example.test.phone.model.PhoneMnemonicsReq;
import com.example.test.phone.repository.PhoneMnemonicsRepository;
import com.example.test.phone.service.TelephoneService;
import com.example.test.phone.utill.TelephoneUtill;

@Service
public class TelephoneServiceImpl implements TelephoneService {

	@Autowired
	TelephoneUtill utill;

	@Autowired
	PhoneMnemonicsRepository repository;

	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public Optional<Page<PhoneMnemonics>> getPhoneMnemonics(String phoneNumber, Pageable pageable) throws Exception {
		if (!StringUtils.isEmpty(phoneNumber) &&
						utill.isValidPhoneNumber(phoneNumber)) {
			return Optional.ofNullable((Page<PhoneMnemonics>) repository.findAllByPhoneNumber(phoneNumber, pageable));
		} else 
			throw new Exception("Invalid Phone Number :" + phoneNumber);
	}

	
	/**
	 * 
	 */
	@Override
	public List<PhoneMnemonics> createPhoneMnemonics(PhoneMnemonicsReq request) throws Exception {
		if ( !StringUtils.isEmpty(request.getPhoneNumber()) &&
						utill.isValidPhoneNumber(request.getPhoneNumber())) {
			long count = repository.countByPhoneNumber(request.getPhoneNumber());
			if (count > 0)
				return repository.findAllByPhoneNumber(request.getPhoneNumber());
			else {
				return saveAllPhoneMnemonics(request.getPhoneNumber());
			}
		} else {
			throw new Exception("Invalid Phone Number : " + request.getPhoneNumber());
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public Optional<List<PhoneMnemonics>> getPhoneMnemonics(String phoneNumber) throws Exception {
		if (!StringUtils.isEmpty(phoneNumber) &&
						utill.isValidPhoneNumber(phoneNumber)) {
			return Optional.ofNullable(repository.findAllByPhoneNumber(phoneNumber));
		} else {
			throw new Exception("Invalid Phone Number : " + phoneNumber);
		}
		
	}

	/**
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	private List<PhoneMnemonics> saveAllPhoneMnemonics(String phoneNumber) throws Exception {
		int[] numberList = utill.convertStringToIntegerArray(phoneNumber);
		List<PhoneMnemonics> result = utill.prepareList(phoneNumber, numberList);
		if (null != result && !result.isEmpty())
			return (List<PhoneMnemonics>) repository.saveAll(result);
		else
			return result;
	}

}
