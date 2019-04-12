package com.Mijnqien;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface Gebruiker {

	User getUser() ;
	void setUser(User user);
	
}
