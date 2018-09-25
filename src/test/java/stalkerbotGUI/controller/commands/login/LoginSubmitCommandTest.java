package stalkerbotGUI.controller.commands.login;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.verification.VerificationMode;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.service.GeneralUserService;

import java.util.Optional;

public class LoginSubmitCommandTest {

    GeneralUserService generalUserService = mock(GeneralUserService.class);
    LoginSubmitCommand loginSubmitCommand = mock(LoginSubmitCommand.class);

    @Test
    public void performExecute() {

        User user = new User.Builder().setRole(RoleType.USER).getInstance();
        Optional<User> optionalUser = Optional.of(user);

        when(generalUserService.login("mishav@ukr.net", "1111")).thenReturn(optionalUser);

        //Optional<User> optionalUser = generalUserService.login("mishav@ukr.net", "1111");

        System.out.println(generalUserService.login("mishav@ukr.net", "1111"));

    }
}