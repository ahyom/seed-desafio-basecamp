package com.soavedev.seeddesafiobasecamp.service

import com.soavedev.seeddesafiobasecamp.domain.entity.User
import com.soavedev.seeddesafiobasecamp.domain.exceptions.BadRequestException
import com.soavedev.seeddesafiobasecamp.domain.exceptions.EntityAlreadyExistsException
import com.soavedev.seeddesafiobasecamp.domain.exceptions.NotFoundException
import com.soavedev.seeddesafiobasecamp.domain.repository.UserRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

private val logger = KotlinLogging.logger {}

@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) {

    fun saveUser(user: User): User {
        logger.debug { "Saving User with id [${user.id}]..." }
        validateIfEmailAlreadyExists(user.emailAddress)
        validateIdLoginAlreadyExists(user.login)

        user.userPassword = BCryptPasswordEncoder().encode(user.userPassword)

        return userRepository.save(user)
    }

    fun updateUser(user: User, userId: String): User {
        logger.debug { "Updating User with id [${user.id}]..." }

        if (userId != user.id.toString()) {
            throw BadRequestException("User id does not match informed user")
        }

        val userBefore = getUserById(userId)

        logger.trace { "User before: [$userBefore] | User after: [$user]" }

        return userRepository.save(user)
    }

    fun getAllUsers(): List<User> {
        logger.debug { "Getting all users..." }
        return userRepository.findAll().toList()
    }

    fun getUserById(userId: String): User {
        logger.debug { "Getting user with id [$userId]" }
        return userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow{ NotFoundException("User $userId was not found") }
    }

    fun validateIfEmailAlreadyExists(emailAddress: String) {
        logger.debug { "Searching email address [$emailAddress]..." }

        val user = userRepository.findUserByEmailAddress(emailAddress)

        if (user.isPresent){
            throw EntityAlreadyExistsException("User with  email [$emailAddress] already exists")
        }
    }

    fun validateIdLoginAlreadyExists(login: String) {
        logger.debug { "Searching user login [$login]..." }
        try {
            userRepository.findByLogin(login)
        } catch (ex: EmptyResultDataAccessException){
            logger.debug { "Login [$login] is available" }
        } catch (ex: Exception){
            logger.error { "Login [$login] is unavailable" }
            throw EntityAlreadyExistsException("Login $login is not available, please choose another")
        }
    }
}
