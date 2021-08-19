package com.slashmobility.kotlinbasebackend.dto.request

import javax.validation.constraints.Max
import javax.validation.constraints.Min

class EmployeeSelfValueRequest(@Min(1)@Max(10)
                               var value: Int) {
}