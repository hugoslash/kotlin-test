package com.slashmobility.kotlinbasebackend.database.seeder

import com.slashmobility.kotlinbasebackend.config.RoleName
import com.slashmobility.kotlinbasebackend.database.entity.Departament
import com.slashmobility.kotlinbasebackend.database.entity.Employee
import com.slashmobility.kotlinbasebackend.database.entity.Role
import com.slashmobility.kotlinbasebackend.database.repository.DepartamentRepository
import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.database.repository.RoleRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

@Component
class Seeder(private val employeeRepository: EmployeeRepository,
             private val departamentRepository: DepartamentRepository,
            private val roleRepository: RoleRepository,
            private val bCryptPasswordEncoder: BCryptPasswordEncoder) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {

        RoleName.values().forEach {
            if (!roleRepository.existsByName(it.name)){
                roleRepository.save(Role(it.name))
            }
        }

        if(!departamentRepository.existsByName("Sales")){ departamentRepository.save(Departament("Sales"))}
        if(!departamentRepository.existsByName("Develop")){ departamentRepository.save(Departament("Develop"))}

        if(!employeeRepository.existsByEmail("hugo@yopmail.com")){
            var newEmployee = Employee( "hugo", "slash", "hugo@yopmail.com", bCryptPasswordEncoder!!.encode("Spring1234."))
            employeeRepository.save(newEmployee)
        }

    }

}