package org.slotegrator.tests;

import org.slotegrator.client.PlayersClient;
import org.slotegrator.dto.UserInfo;
import org.slotegrator.dto.login.builder.UserLoginBuilder;
import org.slotegrator.dto.registration.RegistrationResponseDto;
import org.slotegrator.dto.registration.UserRegistrationBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApiTests {

    private final PlayersClient client = new PlayersClient();

    private String accessToken;

    @BeforeClass
    public void prepare() {
        accessToken = client.login(new UserLoginBuilder().buildUserData()).getAccessToken();
    }

    @Test
    public void loginTest() {
        Assert.assertNotNull(accessToken, "Access token is empty");
    }

    @Test
    public void userRegistrationTest() {
        List<RegistrationResponseDto> registerUsers = registerUsers();

        boolean isIdGeneratedForNewUsers = registerUsers.stream().noneMatch(x -> x.getId().isEmpty());
        Assert.assertTrue(isIdGeneratedForNewUsers);
    }

    @Test
    public void getRegisteredOneUserTest() {
        SoftAssert softAssert = new SoftAssert();

        RegistrationResponseDto registeredUser = registerUsers().get(0);
        String email = registeredUser.getEmail();
        String id = registeredUser.getId();

        UserInfo oneUserByEmail = client.getOneUserByEmail(email, accessToken);
        //TODO FIND BUG: endpoint 'api/automationTask/getOne' work not correct, got user data without id

        softAssert.assertEquals(oneUserByEmail.getId(), id, "User id did not match");
        softAssert.assertEquals(oneUserByEmail.getEmail(), email, "User email did not match");
        softAssert.assertAll();
    }

    @Test
    public void sortedRegisteredUsersTest() {
        List<UserInfo> allUsers = client.getAllUsers(accessToken);
        //TODO FIND BUG: endpoint 'api/automationTask/getAll' work not correct, got users data without id
        List<UserInfo> sortedByNameUsers = allUsers.stream().sorted(Comparator.comparing(UserInfo::getName)).toList();

        boolean isUsersSorted = isUsersSortedByName(sortedByNameUsers);
        Assert.assertTrue(isUsersSorted, "Users not sorted by name");
    }

    @Test
    public void deleteAllTest() {
        List<String> allUsersIds = client.getAllUsers(accessToken).stream().map(UserInfo::getId).collect(Collectors.toList());
        //TODO FIND BUG: endpoint 'api/automationTask/getAll' work not correct, got users data without id

        for (String id : allUsersIds) {
            client.deleteUserById(accessToken, Integer.parseInt(id));
        }
        List<UserInfo> allUsers = client.getAllUsers(accessToken);
        Assert.assertFalse(allUsers.isEmpty());
    }

    private List<RegistrationResponseDto> registerUsers() {
        List<RegistrationResponseDto> users = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            RegistrationResponseDto registerUser = client.register(new UserRegistrationBuilder().buildUserRegistrationData(), accessToken);
            users.add(registerUser);
        }
        Assert.assertFalse(users.isEmpty(), "Users not registered");
        return users;
    }

    private boolean isUsersSortedByName(List<UserInfo> users) {
        boolean isSorted = true;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getName().compareTo(users.get(i - 1).getName()) < 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}
