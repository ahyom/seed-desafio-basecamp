package com.soavedev.seeddesafiobasecamp.domain.mapper

import com.soavedev.seeddesafiobasecamp.domain.dto.UserDTO
import com.soavedev.seeddesafiobasecamp.domain.entity.User
import com.soavedev.seeddesafiobasecamp.domain.enums.UserRoles
import com.soavedev.seeddesafiobasecamp.domain.enums.UserStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class UserMapperTest @Autowired constructor(
        private val userMapper: UserMapper
) {

    private lateinit var userDtoDefault: UserDTO
    private lateinit var userEntityDefault: User

    @BeforeEach
    fun setup() {
        userDtoDefault = buildDefaultUserDTO()
        userEntityDefault = buildDefaultUserEntity()
    }

    @Test
    fun `should map UserDTO to User`() {
        val user = userMapper.toEntity(userDtoDefault)

        assertEquals(userDtoDefault.id, user.id)
        assertEquals(userDtoDefault.name, user.name)
        assertEquals(userDtoDefault.emailAddress, user.emailAddress)
    }

    @Test
    fun `should map User to UserDTO`() {
        val userDto = userMapper.toDTO(userEntityDefault)

        assertEquals(userEntityDefault.id, userDto.id)
        assertEquals(userEntityDefault.name, userDto.name)
        assertEquals(userEntityDefault.emailAddress, userDto.emailAddress)
    }

    @Test
    fun `when ID is null, should set a new UUID for the user`() {
        userDtoDefault.id = null

        val user = userMapper.toEntity(userDtoDefault)

        assert(user.id.toString().isNotBlank())

    }

    @Test
    fun `when some data is invalid, should thrown IllegalArgumentException`() {
        userDtoDefault.name = ""

        assertThrows<IllegalArgumentException> {
            userMapper.toEntity(userDtoDefault)
        }
    }

    private fun buildDefaultUserDTO(): UserDTO {
        return UserDTO(
                id = UUID.randomUUID(),
                name = "John Mayer",
                login = "username",
                userPassword = "some pass",
                emailAddress = "johnmayer@guitar.com",
                role = UserRoles.ADMIN,
                status = UserStatus.ACTIVE,
                location = "Los Angeles, CA",
                shortBio = "Guitarrist and Taylor Swift ex",
                profilePictureUrl = "blablabla"
        )
    }

    private fun buildDefaultUserEntity(): User {
        return User(
                id = UUID.randomUUID(),
                name = "John Mayer",
                login = "username",
                userPassword = "some pass",
                emailAddress = "johnmayer@guitar.com",
                role = UserRoles.ADMIN,
                status = UserStatus.ACTIVE.name,
                location = "Los Angeles, CA",
                shortBio = "Guitarrist and Taylor Swift ex",
                profilePictureUrl = "blablabla"
        )
    }

}