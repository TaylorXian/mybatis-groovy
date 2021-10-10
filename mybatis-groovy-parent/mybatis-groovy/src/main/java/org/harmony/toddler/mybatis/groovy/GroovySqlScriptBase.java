package org.harmony.toddler.mybatis.groovy;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import static java.util.stream.Collectors.joining;
import static org.apache.ibatis.reflection.ParamNameResolver.GENERIC_NAME_PREFIX;

@Slf4j
public abstract class GroovySqlScriptBase extends Script {

    protected Object param(String name) {
        Binding binding = getBinding();
        if (binding.hasVariable(GroovySqlSource.PARAMETER_OBJECT_KEY)) {
            Object paramMap = binding.getVariable(GroovySqlSource.PARAMETER_OBJECT_KEY);
            if (Objects.nonNull(paramMap)) {
                return InvokerHelper.getProperty(paramMap, name);
            }
        }
        return null;
    }

    /**
     * begin with 1
     *
     * Reference:
     * {@link org.apache.ibatis.reflection.ParamNameResolver#getNamedParams(Object[])}
     *
     * @param i param index begin with 1
     * @return
     */
    protected Object param(Integer i) {
        Binding binding = getBinding();
        if (binding.hasVariable(GroovySqlSource.PARAMETER_OBJECT_KEY)) {
            Object paramMap = binding.getVariable(GroovySqlSource.PARAMETER_OBJECT_KEY);
            if (Objects.nonNull(paramMap)) {
                return InvokerHelper.getProperty(paramMap, GENERIC_NAME_PREFIX + i);
            }
        }
        return null;
    }

    public <V> GroovySqlScriptBase addParam(String name, V value) {
        getBinding().setProperty(name, value);
        return this;
    }

    public <V> GroovySqlScriptBase join(Object entity, Closure<V> on, Map<Object, V> params) {
        return this;
    }


    public GroovySqlScriptBase eq(boolean condition, Object column, Object val) {
        return null;
    }


    public GroovySqlScriptBase ne(boolean condition, Object column, Object val) {
        return null;
    }


    public GroovySqlScriptBase gt(boolean condition, Object column, Object val) {
        return null;
    }


    public GroovySqlScriptBase ge(boolean condition, Object column, Object val) {
        return null;
    }


    public GroovySqlScriptBase lt(boolean condition, Object column, Object val) {
        return null;
    }


    public GroovySqlScriptBase le(boolean condition, Object column, Object val) {
        return null;
    }


    public GroovySqlScriptBase like(boolean condition, Object column, Object val) {
        return this;
    }


    public GroovySqlScriptBase notLike(boolean condition, Object column, Object val) {
        return not(condition).like(condition, column, val);
    }


    public GroovySqlScriptBase likeLeft(boolean condition, Object column, Object val) {
        return this;
    }


    public GroovySqlScriptBase likeRight(boolean condition, Object column, Object val) {
        return this;
    }


    public GroovySqlScriptBase between(boolean condition, Object column, Object val1, Object val2) {
        return this;
    }


    public GroovySqlScriptBase notBetween(boolean condition, Object column, Object val1, Object val2) {
        return not(condition).between(condition, column, val1, val2);
    }


    public GroovySqlScriptBase and(boolean condition, Consumer<GroovySqlScriptBase> consumer) {
        return and(condition).addNestedCondition(condition, consumer);
    }


    public GroovySqlScriptBase or(boolean condition, Consumer<GroovySqlScriptBase> consumer) {
        return or(condition).addNestedCondition(condition, consumer);
    }


    public GroovySqlScriptBase nested(boolean condition, Consumer<GroovySqlScriptBase> consumer) {
        return addNestedCondition(condition, consumer);
    }


    public GroovySqlScriptBase or(boolean condition) {
        return this;
    }


    public GroovySqlScriptBase apply(boolean condition, String applySql, Object... value) {
        return this;
    }


    public GroovySqlScriptBase last(boolean condition, String lastSql) {
        return this;
    }


    public GroovySqlScriptBase comment(boolean condition, String comment) {
        return this;
    }


    public GroovySqlScriptBase first(boolean condition, String firstSql) {
        return this;
    }


    public GroovySqlScriptBase exists(boolean condition, String existsSql) {
        return this;
    }


    public GroovySqlScriptBase notExists(boolean condition, String notExistsSql) {
        return this;
    }


    public GroovySqlScriptBase isNull(boolean condition, Object column) {
        return this;
    }


    public GroovySqlScriptBase isNotNull(boolean condition, Object column) {
        return this;
    }


    public GroovySqlScriptBase in(boolean condition, Object column, Collection<?> coll) {
        return this;
    }


    public GroovySqlScriptBase notIn(boolean condition, Object column, Collection<?> coll) {
        return not(condition).in(condition, column, coll);
    }


    public GroovySqlScriptBase inSql(boolean condition, Object column, String inValue) {
        return this;
    }


    public GroovySqlScriptBase notInSql(boolean condition, Object column, String inValue) {
        return not(condition).inSql(condition, column, inValue);
    }


    public GroovySqlScriptBase groupBy(boolean condition, Object... columns) {
        return this;
    }


    public GroovySqlScriptBase orderBy(boolean condition, boolean isAsc, Object... columns) {
        return this;
    }


    public GroovySqlScriptBase having(boolean condition, String sqlHaving, Object... params) {
        return this;
    }


    public GroovySqlScriptBase func(boolean condition, Consumer<GroovySqlScriptBase> consumer) {
        if (condition) {
            consumer.accept(this);
        }
        return this;
    }

    /**
     * 内部自用
     * <p>NOT 关键词</p>
     */
    protected GroovySqlScriptBase not(boolean condition) {
        return this;
    }

    /**
     * 内部自用
     * <p>拼接 AND</p>
     */
    protected GroovySqlScriptBase and(boolean condition) {
        return this;
    }

    /**
     * 多重嵌套查询条件
     *
     * @param condition 查询条件值
     */
    protected GroovySqlScriptBase addNestedCondition(boolean condition, Consumer<GroovySqlScriptBase> consumer) {
        return this;
    }

    /**
     * 对sql片段进行组装
     *
     * @param condition   是否执行
     * @param sqlSegments sql片段数组
     * @return GroovySqlScriptBase
     */
    protected GroovySqlScriptBase doIt(boolean condition, Object... sqlSegments) {
        return this;
    }


    /**
     * 获取 columnName
     */
    protected String columnToString(Object column) {
        return (String) column;
    }

    /**
     * 多字段转换为逗号 "," 分割字符串
     *
     * @param columns 多字段
     */
    protected String columnsToString(Object... columns) {
        return Arrays.stream(columns).map(this::columnToString).collect(joining(","));
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }
}
