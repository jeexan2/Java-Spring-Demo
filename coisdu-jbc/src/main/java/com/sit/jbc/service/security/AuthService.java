package com.sit.jbc.service.security;

import com.sit.jbc.domain.dto.security.ChangePassword;
import com.sit.jbc.domain.dto.security.MenuElement;
import com.sit.jbc.domain.dto.security.RolewiseOptionProcPojo;
import com.sit.jbc.domain.entity.security.User;

import java.util.List;

public interface AuthService {
    User getUserByUsernameAndOfficeCategory(String username, Long officeCategory);
    String getMenuElementsHtml(List<RolewiseOptionProcPojo> optionList);
    List<RolewiseOptionProcPojo> getRolewiseOptionList(Long roleId);
    User updatePassword(ChangePassword ps, Long userId) throws Exception;
}