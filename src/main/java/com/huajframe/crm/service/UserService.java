package com.huajframe.crm.service;

import com.huajframe.base.BaseService;
import com.huajframe.crm.dao.UserMapper;
import com.huajframe.crm.model.UserModel;
import com.huajframe.crm.utils.AssertUtil;
import com.huajframe.crm.utils.Md5Util;
import com.huajframe.crm.utils.UserIDBase64;
import com.huajframe.crm.vo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 用户业务层
 * @author Hua JFarmer
 */
@Service
public class UserService extends BaseService<User, Integer> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param userName 用户名
     * @param userPwd 密码
     * @return
     */
    public UserModel login(String userName, String userPwd){
        //检查用户名和密码是否为空
        checkLoginParams(userName,userPwd);
        User user = userMapper.queryByUserName(userName);

        AssertUtil.isTrue(user == null || user.getIsValid() == 0, "用户不存在或已注销！");
        String realPwd = Md5Util.encode(userPwd);
        String targetPwd = user.getUserPwd();
        AssertUtil.isTrue(!(realPwd.equals(targetPwd)), "密码错误!");

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        return userModel;
    }

    /**
     * 检查用户登录参数是否合法
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isEmpty(userName), "用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isEmpty(userPwd), "用户密码不能为空!");
    }

    /**
     * 修改密码
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     */
    public void updateUserPassword(Integer userId, String oldPassword,
                                   String newPassword, String confirmPassword){
        checkUpdatePasswordParams(userId, oldPassword, newPassword, confirmPassword);
        User user = new User();
        user.setUserPwd(Md5Util.encode(newPassword));
        user.setId(userId);
        Integer count = updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(count < 1, "修改密码失败");
    }

    /**
     * 修改密码参数校验
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkUpdatePasswordParams(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(null == userId || user == null, "用户未登录或不存在");
        AssertUtil.isTrue(StringUtils.isEmpty(oldPassword), "请输入旧密码");
        AssertUtil.isTrue(StringUtils.isEmpty(newPassword), "请输入新密码");
        AssertUtil.isTrue(StringUtils.isEmpty(confirmPassword), "请输入确认密码");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword), "新密码不一致");
        AssertUtil.isTrue(oldPassword.equals(newPassword), "新密码不能和旧密码相同");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()), "旧密码不正确");
    }

    /**
     * 查询所有的销售人员
     * @return
     */
    public List<Map<String, Object>> queryAllSales(){
        return userMapper.queryAllSales();
    }
}
