package com.telus.dl.profilemanagement.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.telus.dl.profilemanagement.ProfileManagementApplication;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		ProfileManagementApplication.class,
		UserProfileController.class })
public class UserProfileControllerApiTest {
//	private static final String CONTENT_TYPE = "application/json";
//	@Value("${local.server.port}")
//	private int port;
//
//	@Autowired
//	@InjectMocks
//	private DowJonesIndexService dowJonesIndexService;
//
//	@Mock
//	private DowJonesIndexRepository dowJonesIndexRepository;
//
//	@BeforeEach
//	public void setup() {
//		RestAssured.port = port;
//		MockitoAnnotations.openMocks(this);
//
//	}
//
//	@Test
//	public void findDowJonesIndicesSuccess() {
//		List<DowJonesIndex> mockListOfDowJonesIndex = JsonLoaderUtil.getObjectFrom(
//				"list-of-dow-jones-index.json",
//				new TypeReference<List<DowJonesIndex>>() {
//				});
//		when(dowJonesIndexRepository.findByStock(anyString())).thenReturn(mockListOfDowJonesIndex);
//
//		given()
//				.contentType(CONTENT_TYPE)
//				.queryParam("stock", "AA")
//				.when().get("/api/dow-jones-indices")
//				.then().log().all().statusCode(HttpStatus.SC_OK);
//	}
}
