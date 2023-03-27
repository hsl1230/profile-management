package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.userprofile.UserProfile;
import com.telus.dl.profilemanagement.document.userprofile.UserProfileType;
import com.telus.dl.profilemanagement.dto.userprofile.CreateUserProfileRequest;
import com.telus.dl.profilemanagement.util.MockModelMapper;
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
	private ModelMapper modelMapper = new MockModelMapper();

	@InjectMocks
	private UserProfileService userProfileService;

	@BeforeEach
	public void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		when(mongoTemplate.insert(any(UserProfile.class))).thenReturn(
				new UserProfile()
		);
	}

	@Test
	public void testCreatePrimaryUserProfile() {
		CreateUserProfileRequest createPrimaryUserProfileRequest = new CreateUserProfileRequest()
				.userName("henry")
				.email("henry.huang@telus.com")
				.phoneNumber("4039266729")
				.userProfileType(UserProfileType.PRIMARY)
				.myTelusId("T982127");

		assertNotNull(this.userProfileService.createUserProfile(createPrimaryUserProfileRequest));
	}
}
