package com.future.base.util.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.future.base.util.base.FileGetter.getFiles;
import static com.future.base.util.base.ProChecker.isNotNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * prop processor
 *
 * @author liuyunfei
 */
@SuppressWarnings({"JavaDoc", "unused"})
public final class PropertiesProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesProcessor.class);

    /**
     * load prop
     *
     * @param file
     * @return
     */
    public static Properties loadProp(File file) {
        Properties prop = new Properties();

        if (isNotNull(file) && file.isFile() && file.canRead()) {
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                prop.load(inputStream);
                LOGGER.info("Properties loadProp(File file), file = {}, prop = {}", file, prop);
            } catch (IOException e) {
                LOGGER.error("Properties loadProp(File file) failed, file = {}, e = {}", file, e);
            }
        }

        return prop;
    }

    /**
     * load prop
     *
     * @param resource
     * @return
     */
    public static Properties loadProp(Resource resource) {
        Properties prop = new Properties();

        if (isNotNull(resource) && resource.exists()) {
            try {
                prop.load(resource.getInputStream());
                LOGGER.info("Properties loadProp(Resource resource), resource = {}, prop = {}", resource, prop);
            } catch (IOException e) {
                LOGGER.error("Properties loadProp(Resource resource) failed, resource = {}, e = {}", resource, e);
            }
        }

        return prop;
    }

    /**
     * convert prop to map
     *
     * @param prop
     * @return
     */
    public static Map<String, String> parseProp(Properties prop) {
        return ofNullable(prop)
                .map(p -> p.entrySet().stream().collect(toMap(e -> e.getKey().toString(), e -> e.getValue().toString(), (a, b) -> a)))
                .orElseGet(Collections::emptyMap);
    }

    /**
     * convert prop to map
     *
     * @param file
     * @return
     */
    public static Map<String, String> parseProp(File file) {
        return ofNullable(file)
                .filter(File::isFile)
                .map(PropertiesProcessor::loadProp)
                .map(PropertiesProcessor::parseProp)
                .orElseGet(Collections::emptyMap);
    }

    /**
     * convert resource to map
     *
     * @param resource
     * @return
     */
    public static Map<String, String> parseProp(Resource resource) {
        return ofNullable(resource)
                .filter(Resource::exists)
                .map(PropertiesProcessor::loadProp)
                .map(PropertiesProcessor::parseProp)
                .orElseGet(Collections::emptyMap);
    }

    /**
     * find props by uri
     *
     * @param uri
     * @param recursive
     * @return
     */
    public static List<Properties> getProps(String uri, boolean recursive) {
        return getFiles(uri, recursive).stream().map(PropertiesProcessor::loadProp).collect(toList());
    }

    /**
     * find props by uri
     *
     * @param propName
     * @return
     */
    public static Properties loadProps(String propName) {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertiesProcessor.class.getClassLoader().getResourceAsStream(propName)) {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("propName load failed, e = " + e);
        }

        return properties;
    }

}
