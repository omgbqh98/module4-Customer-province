package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CustomerService;
import service.ProvinceService;

import java.util.Optional;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/province")
    public ModelAndView listProvince(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5)Pageable pageable) {
        Page<Province> provinces;
        if (s.isPresent()) {
            provinces = provinceService.findAllByNameContaining(s.get(), pageable);
        } else {
            provinces = provinceService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView create(Province province, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        redirectAttributes.addFlashAttribute("success", "create success");
        provinceService.save(province);
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView modelAndView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        Province province = provinceService.finById(id);
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    @PostMapping("/edit-province/")
    public ModelAndView update(Province province, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        provinceService.save(province);
        modelAndView.addObject("province", province);
        redirectAttributes.addFlashAttribute("success", "edit success");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/delete");
        Province province = provinceService.finById(id);
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    @PostMapping("/delete-province")
    public ModelAndView delete(Province province, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "delete success");
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        provinceService.remove(province.getId());
        return modelAndView;
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Province province = provinceService.finById(id);
        if(province == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Customer> customers = customerService.findAllByProvince(province);

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
