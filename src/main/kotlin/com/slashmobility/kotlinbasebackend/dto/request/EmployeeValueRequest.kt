package com.slashmobility.kotlinbasebackend.dto.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min

class EmployeeValueRequest(@Min(1)@Max(10)
                           var value: Int,
                           var employee: Long) {
}