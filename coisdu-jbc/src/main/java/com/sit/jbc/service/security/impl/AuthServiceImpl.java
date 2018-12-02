package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.ChangePassword;
import com.sit.jbc.domain.dto.security.MenuElement;
import com.sit.jbc.domain.dto.security.RolewiseOptionProcPojo;
import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.repository.security.AuthRepository;
import com.sit.jbc.repository.security.OptionProcedureRepository;
import com.sit.jbc.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    OptionProcedureRepository optionProcedureRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User updatePassword(ChangePassword ps, Long userId) throws Exception{
        User user =  authRepository.findByUserId(userId);
        if(user != null) {
            if (bCryptPasswordEncoder.matches(ps.getCurPassword(), user.getPassword())) {
                user.setLastPassword(user.getPassword());
                user.setPassword(bCryptPasswordEncoder.encode(ps.getNewPassword()));
                authRepository.save(user);
                return user;
            }
            else {
                throw new Exception("Wrong password provided");
            }
        }
        else {
            throw new Exception("User not found");
        }
    }

    @Override
    public User getUserByUsernameAndOfficeCategory(String username, Long officeCategory) {
        return authRepository.findByUsernameAndOfficeCategory(username, officeCategory);
    }
    @Override
    public String getMenuElementsHtml(List<RolewiseOptionProcPojo> optionList) {
        List<MenuElement> elems = getMenuElements(optionList);

        String s = "<ul class=\"nav side-menu\">";
        s += "<li><a href=\"/security/dashboard\">Dashboard</a></li>";
        int curLevel = 1;
        String ulEnd = "</ul></li>";
        int ulCount = 0;

        if(elems.size() > 0) {
            for (int i = 0; i < elems.size(); i++) {
                MenuElement curElem = elems.get(i);
                MenuElement nextElem = null;

                if (i < (elems.size() - 1)) {
                    nextElem = elems.get(i + 1);
                }

                curLevel = curElem.getMenuLevel();

                if (curLevel == 3)
                    s += "<li class=\"sub_menu\"><a href=\"" + curElem.getMenuAccessUrl() + "\">" + curElem.getMenuName() + "</a>";
                else
                    s += "<li><a>" + curElem.getMenuName() + " <span class=\"fa fa-chevron-down\"></span></a>";

                if (nextElem != null) {
                    if (nextElem.getMenuLevel() > curLevel) {
                        s += "<ul class=\"nav child_menu\">";
                        ulCount++;
                    }
                    if (nextElem.getMenuLevel() < curLevel) {
                        s += "</li>";

                        int defLevel = curLevel - nextElem.getMenuLevel();
                        for (int j = 0; j < defLevel; j++) {
                            s += ulEnd;
                        }
                        ulCount -= defLevel;
                    }
                    if (nextElem.getMenuLevel() == curLevel) {
                        s += "</li>";
                    }
                }
            }

            s += "</li>";
            for (int j = 0; j < ulCount; j++) {
                s += ulEnd;
            }
        }

        s += "</ul>";

        return s;
    }

    @Override
    public List<RolewiseOptionProcPojo> getRolewiseOptionList(Long roleId) {
        return optionProcedureRepository.getRolewiseOptionList(roleId);
    }

    private List<MenuElement> getMenuElements(List<RolewiseOptionProcPojo> elems) {
        Long curModuleId = 0L;
        Long curSubModuleId = 0L;
        Long curOptionId = 0L;
        List<MenuElement> menuElements = new ArrayList<>();

        //List<RolewiseOptionProcPojo> elems = optionProcedureRepository.getRolewiseOptionList(roleId);

        for (RolewiseOptionProcPojo elem: elems) {
            if(elem.getSecModuleId() != curModuleId){
                MenuElement m = new MenuElement();
                curModuleId = elem.getSecModuleId();
                m.setMenuLevel(1);
                m.setMenuId(elem.getSecModuleId());
                m.setMenuName(elem.getSecModuleName());
                menuElements.add(m);
            }
            if(elem.getSecSubModuleId() != curSubModuleId && elem.getSecSubModuleId() != null){
                MenuElement m = new MenuElement();
                curSubModuleId = elem.getSecSubModuleId();
                m.setMenuLevel(2);
                m.setMenuId(elem.getSecSubModuleId());
                m.setMenuName(elem.getSecSubModuleName());
                menuElements.add(m);
            }
            if(elem.getSecOptionId() != curOptionId && elem.getSecOptionId() != null){
                MenuElement m = new MenuElement();
                curOptionId = elem.getSecOptionId();
                m.setMenuLevel(3);
                m.setMenuId(elem.getSecOptionId());
                m.setMenuName(elem.getSecOptionName());
                m.setMenuAccessUrl(elem.getSecOptionAccessUrl());
                menuElements.add(m);
            }
        }

        return menuElements;
    }
}