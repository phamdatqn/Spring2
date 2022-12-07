package product_management.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_management.model.Role;
import product_management.repository.IRoleRepository;
import product_management.service.security.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return roleRepository.finAllRole();

    }

    @Override
    public void saveCreateGmail(String email) {
        roleRepository.insertRoleGmail(email);
    }

    @Override
    public List<Role> getRoleByUsername(String email) {
        return roleRepository.findRoleByUsername(email);
    }
}
