package com.slashmobility.kotlinbasebackend.dto.response

import com.slashmobility.kotlinbasebackend.database.entity.Task

class EmployeeResponse(
    var id: Long?,
    var email: String?,
    var firstName: String?,
    var selfValue: Int?,
    var value: Int?,
    var salary: Float?,
    var task: List<Task>?
) {
}