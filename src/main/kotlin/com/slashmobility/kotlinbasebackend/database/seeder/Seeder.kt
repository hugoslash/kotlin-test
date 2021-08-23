package com.slashmobility.kotlinbasebackend.database.seeder

import com.slashmobility.kotlinbasebackend.constants.RoleName
import com.slashmobility.kotlinbasebackend.database.entity.Department
import com.slashmobility.kotlinbasebackend.database.entity.Employee
import com.slashmobility.kotlinbasebackend.database.entity.Role
import com.slashmobility.kotlinbasebackend.database.repository.DepartmentRepository
import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.database.repository.RoleRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class Seeder(private val employeeRepository: EmployeeRepository,
             private val departmentRepository: DepartmentRepository,
             private val roleRepository: RoleRepository,
             private val bCryptPasswordEncoder: BCryptPasswordEncoder) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {

        val roles = mutableSetOf<Role>()
        RoleName.values().forEach {
            if (!roleRepository.existsByName(it.name)){
                roleRepository.save(Role(it.name))
                roles.add(Role(it.name))
            }
        }

        val departments = setOf(Department("sales"), Department("Develop"))
        departments.forEach {
            if(!departmentRepository.existsByName(it.name)){ departmentRepository.save(it)}
        }

        val newUserName = "hugo@slash.com"
        if(!employeeRepository.existsByEmail(newUserName)){
            val newEmployeeAdmin = Employee( "hugo", "slash", newUserName, bCryptPasswordEncoder.encode("Spring1234."))
            newEmployeeAdmin.roles = roles
            employeeRepository.save(newEmployeeAdmin)

        }

    }

}