package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.config.BusinessConfig;
import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import com.telus.dl.profilemanagement.dto.AddressDto;
import com.telus.dl.profilemanagement.dto.CreatePrimaryUserProfileRequest;
import com.telus.dl.profilemanagement.dto.PropertyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTest {
	@Mock
	private MongoTemplate mongoTemplate;
	@Spy
	private ModelMapper modelMapper = new ModelMapper();

	@InjectMocks
	private UserProfileService userProfileService;

	public UserProfileServiceTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
	}

	@BeforeEach
	public void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		when(mongoTemplate.insert(any(PrimaryUserProfile.class))).thenReturn(
				new PrimaryUserProfile()
		);
	}

	@Test
	public void testCreatePrimaryUserProfile() {
		CreatePrimaryUserProfileRequest createPrimaryUserProfileRequest = new CreatePrimaryUserProfileRequest()
				.firstName("henry")
				.lastName("huang")
				.email("henry.huang@telus.com")
				.phoneNumber("4039266729")
				.myTelusId("T982127")
				.property(new PropertyDto()
						.name("home1")
						.address(
								new AddressDto()
										.street("191 mt reliant")
										.city("calgary")
										.province("alberta")
										.country("canada")
										.postCode("T2Z 2G2")
						)
						.description("primary home")
				);

		assertNotNull(this.userProfileService.createPrimaryUserProfile(createPrimaryUserProfileRequest));
	}
}
