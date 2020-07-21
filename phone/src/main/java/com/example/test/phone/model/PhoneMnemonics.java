package com.example.test.phone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PhoneMnemonics {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String phoneNumber;
	private String mnemonic;

	public PhoneMnemonics(Long id, String phoneNumber, String mnemonic) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.mnemonic = mnemonic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mnemonic == null) ? 0 : mnemonic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneMnemonics other = (PhoneMnemonics) obj;
		if (mnemonic == null) {
			if (other.mnemonic != null)
				return false;
		} else if (!mnemonic.equals(other.mnemonic))
			return false;
		return true;
	}

	public PhoneMnemonics() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

}
