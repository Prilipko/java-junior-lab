package jdbc_example.dao.impl;

import jdbc_example.domain.ContactTelDetail;

import java.util.HashMap;
import java.util.Map;

public class ContactTelDetailParameterMapper {
    private static final ContactTelDetailParameterMapper INSTANCE = new ContactTelDetailParameterMapper();

    private ContactTelDetailParameterMapper() {
    }

    public synchronized static ContactTelDetailParameterMapper getInstance(){
        return INSTANCE;
    }


    Map<String, ?> getMap(ContactTelDetail contactTelDetail) {
        Map<String, Object> map = new HashMap<>();
//        map.put("id", contactTelDetail.getId());
        map.put("contactId", contactTelDetail.getContactId());
        map.put("telType", contactTelDetail.getTelType());
        map.put("telNumber", contactTelDetail.getTelNumber());
        return map;
    }
}
