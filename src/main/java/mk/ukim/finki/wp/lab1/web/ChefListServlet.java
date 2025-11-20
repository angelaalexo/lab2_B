package mk.ukim.finki.wp.lab1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.springframework.stereotype.Component; // <-- Add this import

import java.io.IOException;

// Add @Component to allow Spring to manage this servlet as a bean
@Component
@WebServlet(name = "ChefListServlet", urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;

    // Use constructor injection instead of manual init() lookup
    public ChefListServlet(SpringTemplateEngine templateEngine, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
    }

    // Remove the init() method entirely since dependencies are injected

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // The templateEngine and chefService fields are now correctly injected and not null.
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chefs", chefService.listChefs()); // Access is now safe

        templateEngine.process("listChefs.html", context, response.getWriter());
    }
}