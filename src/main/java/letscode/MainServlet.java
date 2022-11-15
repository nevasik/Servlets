package letscode;
// 12:31
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/cool-servlet", "/my-cool-servlet/*"}) // если наш URL будет начинаться с указанного слова servlet и дальше неважно что, он будет попадать сюда
public class MainServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("Method init =)");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("method service enter\n");
        super.service(req, resp);
        // напишем сообщение клиенту
        resp.getWriter().write("method service exit\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI(); // здесь хранится та часть урла, которая адресует текущий сервлет(/my-app/my-cool-servlet/more/hello)
        String params = formatParams(req);
        // напишем сообщение клиенту
        resp.getWriter().write("method doGet\nURL: " + url + "\nParams: \n" + params + "\n");
    }

    private static String formatParams(HttpServletRequest req) {
        return req.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> {
                    String param = String.join(" and ", entry.getValue());
                    return entry.getKey() + " => " + param;
                })
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void destroy() {
        log("Method destroy =)");
    }
}
