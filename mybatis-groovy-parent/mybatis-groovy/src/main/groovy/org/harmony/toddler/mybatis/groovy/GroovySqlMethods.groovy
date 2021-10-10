package org.harmony.toddler.mybatis.groovy

import org.apache.commons.lang3.RegExUtils
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateFormatUtils

import java.util.regex.Pattern

import static org.apache.commons.lang3.StringUtils.EMPTY

class GroovySqlMethods {
    static Pattern REGEX_AND_OR = Pattern.compile("(?i)^and|^or")
    static String SELECT = ' SELECT '
    static String FROM = ' FROM '
    static String WHERE = ' WHERE '
    static String GROUP_BY = ' GROUP BY '
    static String ORDER_BY = ' ORDER BY '
    static String LIMIT = ' LIMIT '
    static String OFFSET = ' OFFSET '
    static String COMMA = ','

    def static select(String fields) {
        trim(fields, SELECT, EMPTY, EMPTY, COMMA)
    }

    def static select(Closure<String> fields) {
        select(fields.call(EMPTY))
    }

    def static from(String tables) {
        trim(tables, FROM, EMPTY, EMPTY, EMPTY)
    }

    def static from(Closure<String> tables) {
        from(tables.call(EMPTY))
    }

    def static where(String cond) {
        String c = trimOrEmpty(cond)
        if (c) {
            return WHERE + RegExUtils.replaceFirst(c, REGEX_AND_OR, EMPTY)
        }
        return EMPTY
    }

    def static where(Closure<String> cond) {
        where(cond.call(EMPTY))
    }

    def static cond(Closure<String> cond) {
        defaultOrEmpty(cond.call(EMPTY))
    }

    def static propOrEmpty(param, String name, Closure<String> closure) {
        propOrDef(param, name, EMPTY, closure)
    }

    def static propOrDef(param, String name, String defVal,
                         Closure<String> closure) {
        if (param.hasProperty(name)) {
            def v = param.getProperty(name)
            v ? closure.call(v) : defVal
        } else {
            defVal
        }
    }

    def static propOrEmpty(Object prop, Closure<String> closure) {
        propOrDef(prop, EMPTY, closure)
    }

    def static propOrDef(Object propVal, String defVal, Closure<String> closure) {
        propVal ? closure.call(propVal) : defVal
    }

    def static prop(vo, String name) {
        vo.hasProperty(name) ? vo.getProperty(name) : null
    }

    def static groupBy(String fields,
                       String prefixOverrides = EMPTY,
                       String suffixOverrides = COMMA) {
        trim(fields, GROUP_BY, prefixOverrides, EMPTY, suffixOverrides)
    }

    def static groupBy(String prefixOverrides = EMPTY,
                       String suffixOverrides = COMMA,
                       Closure<String> fields) {
        groupBy(fields.call(EMPTY), prefixOverrides, suffixOverrides)
    }

    def static orderBy(String fields,
                       String prefixOverrides = EMPTY,
                       String suffixOverrides = COMMA) {
        trim(fields, ORDER_BY, prefixOverrides, EMPTY, suffixOverrides)
    }

    def static orderBy(String prefixOverrides = EMPTY,
                       String suffixOverrides = COMMA,
                       Closure<String> fields) {
        orderBy(fields.call(EMPTY), prefixOverrides, suffixOverrides)
    }

    def static limit(Integer size, Integer offset) {
        if (Objects.isNull(size) || size <= 0) {
            return EMPTY
        }
        LIMIT + size + OFFSET + offset
    }

    def static limit(Integer size, offset = 0, Closure<String> closure) {
        if (Objects.isNull(size) || size <= 0) {
            return EMPTY
        }
        if (Objects.isNull(offset) || offset <= 0) {
            offset = 0
        }
        defaultOrEmpty(closure.call(size, offset))
    }

    def static trimOrEmpty(s = '') {
        StringUtils.trimToEmpty(s)
        // StringUtils.stripToEmpty(s)
    }

    def static defaultOrEmpty(s = '') {
        StringUtils.defaultString(s)
    }

    /**
     * trim string with prefix and suffix
     *
     * @param string
     * @param prefix
     * @param prefixOverrides
     * @param suffix
     * @param suffixOverrides
     * @return
     */
    def static trim(String string,
                    String prefix, String prefixOverrides,
                    String suffix, String suffixOverrides) {
        String s = trimOrEmpty(string)
        if (StringUtils.isEmpty(s)) {
            return EMPTY
        }
        String pf = defaultOrEmpty(prefix)
        String sf = defaultOrEmpty(suffix)
        String po = trimOrEmpty(prefixOverrides)
        String so = trimOrEmpty(suffixOverrides)
        s = StringUtils.stripStart(s, po)
        s = StringUtils.stripEnd(s, so)
        pf + s + sf
    }

    def static now(pattern = 'yyyy-MM-dd') {
        DateFormatUtils.format(System.currentTimeMillis(), pattern)
    }
}