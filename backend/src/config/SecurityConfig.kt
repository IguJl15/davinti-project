package com.davintiproject.backend.config

import com.davintiproject.backend.modules.security.requestsFilters.JwtSecurityFilter
import com.davintiproject.backend.modules.security.services.AuthorizationService
import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfig(
    val authorizationService: AuthorizationService,
    val jwtSecurityFilter: JwtSecurityFilter
) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return authorizationService
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder
            .userDetailsService(authorizationService)
            .passwordEncoder(passwordEncoder())
        return authenticationManagerBuilder.build()
    }


    @Bean
    @kotlin.Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors { corsConfigurationSource(it) }
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorize ->
                authorize.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                authorize.requestMatchers( HttpMethod.GET ,"/public", "/courses").permitAll()
                authorize.requestMatchers(
                    HttpMethod.POST,
                    "/session",
                    "/session/refresh",
                    "/students",
                    "/users"
                )
                    .anonymous()

                authorize.anyRequest().authenticated()
            }
            .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun corsConfigurationSource(corsConfigurer: CorsConfigurer<HttpSecurity>) {
        val all = listOf("*")
        val configuration = CorsConfiguration().apply {
            allowedOrigins = all
            allowedHeaders = all
            allowedMethods = all
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        corsConfigurer.configurationSource(source)
    }
}