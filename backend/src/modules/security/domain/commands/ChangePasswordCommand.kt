package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.data.repositories.UserRepository
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.Principal

data class ChangePasswordDto(
    val currentPassword: String, val newPassword: String, val confirmationPassword: String, val connectedUser: Principal
)


@Service
class ChangePasswordCommand(
    private val passwordEncoder: PasswordEncoder, private val repository: UserRepository
) : Command<ChangePasswordDto, Unit> {

    override fun execute(params: ChangePasswordDto) {
        val user: User = (params.connectedUser as UsernamePasswordAuthenticationToken).principal as User


        check(passwordEncoder.matches(params.currentPassword, user.password)) { "Wrong password" }

        check(params.newPassword == params.confirmationPassword) { "Password are not the same" }


        user.pass = passwordEncoder.encode(params.newPassword)

        repository.save(user)

        return
    }
}