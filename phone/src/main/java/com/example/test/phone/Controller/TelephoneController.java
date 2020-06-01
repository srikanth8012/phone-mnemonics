package com.example.test.phone.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.phone.exception.ResourceNotFoundException;
import com.example.test.phone.model.PhoneMnemonics;
import com.example.test.phone.model.PhoneMnemonicsReq;
import com.example.test.phone.service.TelephoneService;

@RestController
@RequestMapping(path = "/telephone")
public class TelephoneController {

	@Autowired
	private TelephoneService service;

	@PostMapping(path = "/")
	public List<PhoneMnemonics> createPhoneMnemonics(@RequestBody PhoneMnemonicsReq request) throws Exception {
		return service.createPhoneMnemonics(request);
	}

	@GetMapping(path = "/{number}")
	public ResponseEntity<Object> getPhoneMnemonics(@PathVariable String number) throws Exception {
		Optional<List<PhoneMnemonics>> resultList = null;
		resultList = service.getPhoneMnemonics(number);
		if (resultList.isPresent() && !resultList.get().isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(resultList);
		else
			throw new ResourceNotFoundException("No Mnemonics found: " + number);

	}

	@GetMapping(path = "/paginated/{number}")
	public ResponseEntity<Object> getPaginatedPhoneMnemonics(@PathVariable String number, Pageable pageble) throws Exception {
		Optional<Page<PhoneMnemonics>> resultList = null;
		resultList = service.getPhoneMnemonics(number, pageble);
		if (resultList.isPresent() && !resultList.get().isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(resultList);
		else
			throw new ResourceNotFoundException("No Mnemonics found: " + number);
	}

}
