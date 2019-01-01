package com.study.demo.resource;

import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.ui.common.model.GroupRepresentation;
import org.flowable.ui.common.model.RemoteGroup;
import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.model.UserRepresentation;
import org.flowable.ui.common.security.SecurityUtils;
import org.flowable.ui.common.service.exception.NotFoundException;
import org.flowable.ui.common.service.idm.RemoteIdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/demo")
public class DemoRemoteAccountResource {
	
    @Autowired
    private RemoteIdmService remoteIdmService;

    /**
     * GET /rest/account -> get the current user.
     */
    @RequestMapping(value = "/rest/account", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation getAccount() {
        User user=new UserEntityImpl();
        user.setId("admin");
        SecurityUtils.assumeUser(user);
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId("admin");
        userRepresentation.setFirstName("admin");
        List<String> privileges=new ArrayList<>();
        privileges.add("flowable-admin");
        privileges.add("flowable-idm");
        privileges.add("flowable-modeler");
        privileges.add("flowable-rest");
        privileges.add("flowable-task");
        userRepresentation.setPrivileges(privileges);
        return  userRepresentation;

    }
    
    /**
     * GET /rest/account -> get the current user.
     */
    @RequestMapping(value = "/rest/account", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation getAccount1() {
    	 UserRepresentation userRepresentation = null;
         String currentUserId = SecurityUtils.getCurrentUserId();
         if (currentUserId != null) {
             RemoteUser remoteUser = remoteIdmService.getUser(currentUserId);
             if (remoteUser != null) {
                 userRepresentation = new UserRepresentation(remoteUser);

                 if (remoteUser.getGroups() != null && remoteUser.getGroups().size() > 0) {
                     List<GroupRepresentation> groups = new ArrayList<>();
                     for (RemoteGroup remoteGroup : remoteUser.getGroups()) {
                         groups.add(new GroupRepresentation(remoteGroup));
                     }
                     userRepresentation.setGroups(groups);
                 }

                 if (remoteUser.getPrivileges() != null && remoteUser.getPrivileges().size() > 0) {
                     userRepresentation.setPrivileges(remoteUser.getPrivileges());
                 }

             }
         }

         if (userRepresentation != null) {
             return userRepresentation;
         } else {
             throw new NotFoundException();
         }

    }
}
