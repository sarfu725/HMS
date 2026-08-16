package com.example.hospitalManagementSystem.service;

import com.example.hospitalManagementSystem.entity.Department;
import com.example.hospitalManagementSystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment =
                departmentRepository.findById(id).orElseThrow();

        existingDepartment.setName(department.getName());
        existingDepartment.setHeadDoctor(department.getHeadDoctor());
        existingDepartment.setDoctors(department.getDoctors());

        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}