package com.slashmobility.kotlinbasebackend

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinBaseBackendApplicationTests(@Autowired val client : TestRestTemplate) {

	@Test
	fun healthEndpoint() {
		val hello = client.getForEntity<String>("/api/health")
		assertThat(hello.statusCode).isEqualTo(HttpStatus.OK)
	}

}
