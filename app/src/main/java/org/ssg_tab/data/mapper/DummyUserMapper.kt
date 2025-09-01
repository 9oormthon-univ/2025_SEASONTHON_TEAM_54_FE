package org.ssg_tab.data.mapper

import org.ssg_tab.data.dto.response.DummyResponseDto
import org.ssg_tab.domain.model.entity.DummyUser
import javax.inject.Inject

class DummyUserMapper @Inject constructor() {

    fun mapDtoToEntity(dto : DummyResponseDto) : DummyUser {
        return DummyUser(
            profile = dto.avatar,
            firstName = dto.firstName,
            id = dto.id,
            lastName = dto.lastName,
        )
    }


    fun mapEntityToDto(entity : DummyUser) : DummyResponseDto {
        return DummyResponseDto(
            avatar = entity.profile,
            firstName = entity.firstName,
            id = entity.id,
            lastName = entity.lastName,
            email = ""
        )
    }

}