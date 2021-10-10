package org.harmony.toddler.mybatis.groovy;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.harmony.toddler.mybatis.util.PathUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Groovy Driver Config
 */
public class GroovyLangDriverConfig {

    private static String[] roots = new String[]{"groovy/"};

    /**
     * Get Roots Of Groovy Script
     *
     * @return
     */
    public URL[] getRoots() {
        final String[] rs = roots;
        final List<URL> list = new ArrayList<>(rs.length);
        for (String root : rs) {
            addUrl(list, root);
        }
        return list.toArray(new URL[0]);
    }

    private static void addUrl(List<URL> list, String root) {
        URL url = toURL(root);
        if (Objects.nonNull(url)) {
            list.add(appendSlash(url));
        }
    }

    private static URL appendSlash(URL url) {
        try {
            if (url.toString().endsWith("/")) {
                return url;
            }
            return new URL(url + "/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // search path first, then search in the classpath if not found the path,
    // because it has to restart to reload the changes in classpath scripts
    private static URL toURL(String root) {
        URL url = findPath(root);
        if (Objects.isNull(url)) {
            url = GroovyLangDriverConfig.class.getResource(root);
        }
        try {
            if (url != null) {
                url.openConnection();
                return url;
            }
        } catch (IOException ignored) {
        }
        return null;
    }


    private static URL findPath(String root) {
        Path base = PathUtil.basePath();
        try {
            String[] targets = StringUtils.split(root, "/\\");
            Optional<Path> rootPath = PathUtil.findPath(base, targets);
            if (rootPath.isPresent()) {
                return rootPath.get().toUri().toURL();
            }
            return null;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void setRoots(String[] roots) {
        if (ArrayUtils.isEmpty(roots)) {
            throw new RuntimeException("roots required not null");
        }
        GroovyLangDriverConfig.roots = roots;
    }

    public static GroovyLangDriverConfig newInstance() {
        return new GroovyLangDriverConfig();
    }
}
