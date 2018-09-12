package stalkerbotGUI.controller.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Ignore;
import org.junit.Test;
import stalkerbotGUI.model.entity.enums.RoleType;
import stalkerbotGUI.utils.constants.Attributes;
import stalkerbotGUI.utils.constants.PagesPath;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeCommandTest {

    private HomeCommand homeCommand = mock(HomeCommand.class);
    private HttpSession session = mock(HttpSession.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    @Ignore
    @Test
    public void performExecute() throws ServletException, IOException {

        session.setAttribute(Attributes.USER_ROLE, RoleType.ADMIN);
        String answer = homeCommand.performExecute(request, response);

        assertEquals(PagesPath.ADMIN_HOME, answer);

        session.setAttribute(Attributes.USER_ROLE, RoleType.USER);
        answer = homeCommand.performExecute(request, response);

        assertEquals(PagesPath.USER_HOME, answer);
    }
}