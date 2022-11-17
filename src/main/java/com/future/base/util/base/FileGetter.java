package com.future.base.util.base;

import com.future.base.model.exps.ProException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static com.future.base.util.base.ProChecker.isNull;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.ResourceUtils.getURL;

/**
 * file getter
 *
 * @author liuyunfei
 */
@SuppressWarnings({"AliControlFlowStatementWithoutBraces", "JavaDoc"})
public final class FileGetter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileGetter.class);

    private static final PathMatchingResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
    private static final String MATCH_ALL_PATH = "/**/*";
    private static final String MATCH_ALL_PREFIX = ".*";

    /**
     * find all files
     *
     * @param files
     * @param file
     * @param recursive
     * @return
     */
    private static List<File> listFile(List<File> files, File file, boolean recursive) {
        if (isNull(files))
            files = new LinkedList<>();

        if (isNull(file))
            return files;

        if (file.isDirectory() && recursive)
            for (File f : ofNullable(file.listFiles()).orElseGet(() -> new File[0]))
                listFile(files, f, true);

        if (file.isFile())
            files.add(file);

        return files;
    }

    /**
     * find files by uri
     *
     * @param uri
     * @param recursive
     * @return
     */
    public static List<File> getFiles(String uri, boolean recursive) {
        File file;
        try {
            file = new File(getURL(uri).getPath());
        } catch (Exception e) {
            LOGGER.error("List<File> getFiles(String pathDir) failed, uri = {}, recursive = {}, e = {0}", uri, recursive, e);
            return emptyList();
        }

        return listFile(new LinkedList<>(), file, recursive);
    }

    /**
     * find file by uri
     *
     * @param uri
     * @return
     */
    public static File getFile(String uri) {
        try {
            return new File(getURL(uri).getPath());
        } catch (Exception e) {
            LOGGER.error("File getFile(String uri) failed, uri = {}, e = {0}", uri, e);
            throw new ProException(BAD_REQUEST);
        }
    }

    /**
     * get resources by class path and prefix
     *
     * @param path
     * @param prefix
     * @return
     */
    public static List<Resource> getResources(String path, String prefix) {
        try {
            return Stream.of(RESOURCE_PATTERN_RESOLVER.getResources(path + MATCH_ALL_PATH + prefix)).collect(toList());
        } catch (Exception e) {
            LOGGER.error("List<Resource> getResources(String path, String prefix) failed, path = {}, prefix = {}, e = {}", path, prefix, e);
            throw new ProException(BAD_REQUEST);
        }
    }

    /**
     * get resources by class path
     *
     * @param path
     * @return
     */
    public static List<Resource> getResources(String path) {
        return getResources(path, MATCH_ALL_PREFIX);
    }

}
