package com.huajframe.crm.utils;


import com.huajframe.crm.exceptions.NoLoginException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     * @param request
     * @return
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr");
        if (StringUtils.isEmpty(userIdString)) {
            return 0;
        }
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        if(userId == null){
            throw new NoLoginException();
        }
        return userId;
    }
}
