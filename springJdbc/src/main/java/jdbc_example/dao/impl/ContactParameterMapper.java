package jdbc_example.dao.impl;

import jdbc_example.domain.Contact;

import java.util.HashMap;
import java.util.Map;

public class ContactParameterMapper {
    private static final ContactParameterMapper INSTANCE = new ContactParameterMapper();

    private ContactParameterMapper() {
    }

    public synchronized static ContactParameterMapper getInstance(){
        return INSTANCE;
    }


    Map<String, ?> getMap(Contact contact) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", contact.getId());
        map.put("firstName", contact.getFirstName());
        map.put("lastName", contact.getLastName());
        map.put("birthDate", contact.getBirthdate());
        return map;
    }
}
