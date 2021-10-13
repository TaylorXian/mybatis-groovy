package org.harmony.toddler.mybatis.groovy.demo.mapper;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.harmony.toddler.mybatis.groovy.GroovyLangDriver;
import org.harmony.toddler.mybatis.groovy.demo.domain.User;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectByPrimaryKey(int id);

    User findByIdXml(int id);

    @Select("UserMapperSql#selectById")
    @Lang(GroovyLangDriver.class)
    User selectById(int id);
}