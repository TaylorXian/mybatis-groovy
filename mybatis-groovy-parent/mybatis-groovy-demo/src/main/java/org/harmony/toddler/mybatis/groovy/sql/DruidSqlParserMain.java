package org.harmony.toddler.mybatis.demo;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcConstants;

public class DruidSqlParserMain {
    public static void main(String[] args) {
        String sql = "SELECT t.id, t.name FROM user u";
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcConstants.MYSQL);
        SQLStatement statement = parser.parseSelect();
        System.out.println(statement.getChildren());
    }
}
