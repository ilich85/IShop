package com.ilich.services.interfaces;

import com.ilich.model.Admin;

import java.util.List;

public interface AdminService {

    String addAdmin(Admin admin);

    String removeAdmin(String adminName);

    List<Admin> getAdminsList();

    String checkAdmin(String adminName, String password);
}