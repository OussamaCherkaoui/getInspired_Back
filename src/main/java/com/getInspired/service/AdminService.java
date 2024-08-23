package com.getInspired.service;

import com.getInspired.model.Admin;
import com.getInspired.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin)
    {
        return adminRepository.save(admin);
    }
}
