package com.pramoj.driver.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pramoj.driver.model.DriverEntity;
import com.pramoj.driver.exception.RecordNotFoundException;
import com.pramoj.driver.service.DriverService;

@Controller
@RequestMapping("/")
public class DriverMvcController {
    @Autowired
    DriverService service;

    @RequestMapping
    public String getAllDrivers(Model model) {
        List<DriverEntity> list = service.getAllDrivers();

        model.addAttribute("drivers", list);
        return "list-drivers";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editDriverById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            DriverEntity entity = service.getDriverById(id.get());
            model.addAttribute("driver", entity);
        } else {
            model.addAttribute("driver", new DriverEntity());
        }
        return "add-edit-driver";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteDriverById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteDriverById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createDriver", method = RequestMethod.POST)
    public String createOrUpdateDriver(DriverEntity driver) {
        service.createOrUpdateDriver(driver);
        return "redirect:/";
    }
}
