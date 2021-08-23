package com.slashmobility.kotlinbasebackend

import com.slashmobility.kotlinbasebackend.dto.request.LoginRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinBaseBackendApplicationTests(@Autowired val client : TestRestTemplate) {

	@Test
	fun healthEndpoint() {
		val response = client.getForEntity<String>("/api/health")
		assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(response.body).isEqualTo("I'm ALIVE")
	}

	@Test
	fun loginEndpoint() {

		val login = LoginRequest("hugo@slash.com", "Sping1234.")
		val response = client.postForEntity<Any>("/api/login", login)
		assertThat(response.body).isNotNull
	}

}
