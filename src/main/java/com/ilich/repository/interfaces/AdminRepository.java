package com.ilich.repository.interfaces;

import com.ilich.model.Admin;

import java.util.List;

public interface AdminRepository {

    String addAdmin(Admin admin);

    String removeAdmin(String adminName);

    List<Admin> getAdminsList();

    String checkAdmin(String adminName, String password);
}