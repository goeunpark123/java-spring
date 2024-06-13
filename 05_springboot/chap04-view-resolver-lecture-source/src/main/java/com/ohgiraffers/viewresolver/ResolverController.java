package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    /*설명. 서블릿과 마찬가지로 포워딩을 통해 값 전달 가능*/
    @GetMapping("string")
    public String stringReturning(Model model) {
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환");

        return "result";
    }

    /*설명. 서블릿과 마찬가지로 파라미터를 활용하지 않고서는 리다이렉트를 통해 값 전달 가능(스프링은 해결 가능)*/
    @GetMapping("string-redirect")
    public String stringRedirect(Model model) {
        model.addAttribute("message1", "문자열로 뷰 이름 반환하며 리다이렉트");

        return "redirect:/?message2=1";
    }

    /*설명. 스프링의 RedirectAttributes라는 객체에 attr을 담으면 리다이렉트 시에도 값(전달 상태)이 유지*/
    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");

        return "redirect:/";
    }

    /*설명. 기존에 핸들러 메소드가 ModelAndView를 반환*/
    @GetMapping("modelandview")
    public ModelAndView modelAndView(ModelAndView mv) {
        mv.addObject("message2", "ModelAndView를 이용한 forward");
        mv.setViewName("result");

        return mv;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {

        /*설명. ModelAndView를 통한 리다이렉트 시에는 addObject한 것이 parameter로 넘어간다.(?가 있는 쿼리스트링 형태로 넘어간다.)*/
        mv.addObject("message2", "ModelAndView를 이용한 redirect");
        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttribute(ModelAndView mv, RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 redirect attr");
        mv.setViewName("redirect:/");

        return mv;
    }
}
