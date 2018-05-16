package com.ilich.controllers.user.account;

import com.ilich.model.User;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.user.account.ValidateNewUserController.validate;

@Controller
@RequestMapping(value = "/user-create")
public class CreateUserController {

    private final UserService userService;

    @Autowired
    public CreateUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registerUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newUser", new User());
        modelAndView.addObject("createUserResult", request.getParameter("createUserResult"));
        modelAndView.setViewName("public/user_register");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(User user, final RedirectAttributes redirectAttributes) {
        String result = "redirect:/user-create";
        String val = validate(user);
        if (val.equals("username")) {
            redirectAttributes.addAttribute("createUserResult", "Имя пользователя должно быть" +
                    " от 5 до 25 символов");
            return result;
        } else if (val.equals("password")) {
            redirectAttributes.addAttribute("createUserResult", "Пароль должен быть" +
                    " от 5 до 25 символов");
            return result;
        } else if (val.equals("mail")) {
            redirectAttributes.addAttribute("createUserResult", "Email введен неверно");
            return result;
        }
        String createResult = userService.addUser(user);
        switch (createResult) {
            case "userExists":
                redirectAttributes.addAttribute("createUserResult", "Пользователь с таким именем существует");
                break;
            case "emailExists":
                redirectAttributes.addAttribute("createUserResult", "Email уже используется");
                break;
            case "error":
                redirectAttributes.addAttribute("createUserResult", "Что-то пошло не так, попробуйте позже");
                break;
            default:
                redirectAttributes.addAttribute("createUserResult", "Пользователь успешно зарегистрирован");
                result = "redirect:/index";
                break;
        }
        return result;
    }
}