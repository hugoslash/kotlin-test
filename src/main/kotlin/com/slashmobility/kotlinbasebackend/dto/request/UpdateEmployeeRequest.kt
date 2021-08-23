package com.slashmobility.kotlinbasebackend.dto.request

class UpdateEmployeeRequest(var name: String,
                            var lastName: String,
                            var email: String,
                            var password: String,
                            var id: Long) {
}