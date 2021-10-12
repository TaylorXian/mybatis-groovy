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
}
