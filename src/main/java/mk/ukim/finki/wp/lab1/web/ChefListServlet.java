/*package mk.ukim.finki.wp.lab1.web;

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

@Component
@WebServlet(name = "ChefListServlet", urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;

    public ChefListServlet(SpringTemplateEngine templateEngine, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chefs", chefService.listChefs()); // Access is now safe

        templateEngine.process("listChefs.html", context, response.getWriter());
    }
} */