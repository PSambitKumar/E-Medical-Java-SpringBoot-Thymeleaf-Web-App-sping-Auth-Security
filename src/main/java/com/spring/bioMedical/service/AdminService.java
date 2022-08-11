package com.spring.bioMedical.service;

import java.util.List;
import com.spring.bioMedical.entity.Admin;
import com.spring.bioMedical.entity.User;

public interface AdminService {

	List<Admin> findByRole(String user);
	Admin findByEmail(String user);
	List<Admin> findAll();
	void save(Admin admin);
	
}
