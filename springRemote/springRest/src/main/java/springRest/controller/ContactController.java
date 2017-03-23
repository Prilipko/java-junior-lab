package springRest.controller;

import contactService.domain.Contact;
import contactService.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springRest.domain.Contacts;

import java.util.List;

/**
 * Created by Alexander Prilipko on 22.03.2017.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {


    @Qualifier("contactServiceImpl")
    @Autowired
    ContactService contactService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    Contacts getAll() {
        return new Contacts(contactService.findAll());
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Contact getOne(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    Contact saveOne(@RequestBody Contact contact) {
        return contactService.save(contact);
    }
}
