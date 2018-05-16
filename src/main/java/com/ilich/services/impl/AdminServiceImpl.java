package com.ilich.services.impl;

import com.ilich.model.Admin;
import com.ilich.repository.interfaces.AdminRepository;
import com.ilich.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public String addAdmin(Admin admin) {
        return this.adminRepository.addAdmin(admin);
    }

    @Override
    public String removeAdmin(String adminName) {
        return this.adminRepository.removeAdmin(adminName);
    }

    @Override
    public List<Admin> getAdminsList() {
        return this.adminRepository.getAdminsList();
    }

    @Override
    public String checkAdmin(String adminName, String password) {
        return this.adminRepository.checkAdmin(adminName, password);
    }
}