package stalkerbotGUI.controller.commands;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommandTest {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private HttpSession session = mock(HttpSession.class);
    private Command command = new LogoutCommand();

    @Test
    public void execute() throws ServletException, IOException {
        when(request.getSession())
            .thenReturn(session);
        command.execute(request,response);
        verify(session,times(1)).invalidate();
    }
}