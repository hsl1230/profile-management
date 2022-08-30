package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.config.BusinessConfig;
import com.telus.dl.profilemanagement.document.PrimaryUserProfile;
import com.telus.dl.profilemanagement.dto.CreatePrimaryUserProfileRequest;
import com.telus.dl.profilemanagement.dto.HomeAddressDto;
import com.telus.dl.profilemanagement.repository.PrimaryUserProfileRepository;
import com.telus.dl.profilemanagement.repository.SubUserProfileRepository;
import com.telus.dl.profilemanagement.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTest {
	@Mock
	private UserProfileRepository userProfileRepository;
	@Mock
	private PrimaryUserProfileRepository primaryUserProfileRepository;
	@Mock
	private SubUserProfileRepository subUserProfileRepository;
	@Mock
	private MongoTemplate mongoTemplate;

	private ModelMapper modelMapper;

	private UserProfileService userProfileService;

	@BeforeEach
	public void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		this.modelMapper = new BusinessConfig().modelMapper();
		this.userProfileService = new UserProfileService(
				userProfileRepository,
				primaryUserProfileRepository,
				subUserProfileRepository,
				mongoTemplate,
				modelMapper);
		when(primaryUserProfileRepository.save(any(PrimaryUserProfile.class))).thenReturn(
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
				.homeAddress(new HomeAddressDto()
						.name("home1")
						.address("191 mt reliant pl")
						.description("primary home")
				);

		assertNotNull(this.userProfileService.createPrimaryUserProfile(createPrimaryUserProfileRequest));
}

}
