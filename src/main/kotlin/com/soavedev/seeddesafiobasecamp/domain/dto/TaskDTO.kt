package com.soavedev.seeddesafiobasecamp.domain.dto

import com.soavedev.seeddesafiobasecamp.domain.enums.TaskStatus
import jakarta.validation.Validation
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDateTime
import java.util.*

data class TaskDTO(
        var id: UUID?,

        @field:NotEmpty(message = "name must be informed")
        var name: String,

        var startDate: LocalDateTime?,

        var finishDate: LocalDateTime?,

        @field:NotEmpty(message = "Status must be informed")
        var status: TaskStatus,

        var notes: String?,

        @field:NotEmpty(message = "Task must be in a Bucket")
        var bucketId: UUID,

        var groupId: UUID?,

        var userAssignId: UUID?,

        var userNotifyId: UUID?,
    ){

    fun validate(): List<String> {
        val validator = Validation.buildDefaultValidatorFactory().validator
        return validator.validate(this).map { it.message }
    }
}
