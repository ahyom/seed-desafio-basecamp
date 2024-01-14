package com.soavedev.seeddesafiobasecamp.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

private const val BASE_ENDPOINT = "/users"

private const val requestJson = """
{
	"name": "John Mayer",
	"email_address": "john@mayer.com",
	"role": "ADMIN",
    "status": "ACTIVE",
	"location": "Los Angeles",
	"short_bio": "Guitarrist and Taylor's Switft ex",
	"profile_picture_url": "blablabla"
}
"""

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest @Autowired constructor(
        private val mockMvc: MockMvc
) {

    @Test
    fun `should return HTTP 201 when create an user with success`() {
        mockMvc.perform(
                post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        )
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id").exists())
    }
}