package org.harmony.toddler.mybatis.spring.demo.controller;

import org.harmony.toddler.mybatis.spring.demo.service.UserService;
import org.harmony.toddler.mybatis.spring.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Query Users
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Map<String, Object> query(UserVO userVO) {
        List<UserVO> list = userService.listByCondition(userVO);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return map;
    }
}
