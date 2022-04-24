package ox.mox.Rest.Controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ox.mox.Rest.Models.Role;


@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping()
    public String goToLogin() {
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("roleRadioButton", new Role());
        return "/login";
    }

}