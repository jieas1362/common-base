package com.future.base.component.exception.handler;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import com.future.base.model.common.ExceptionElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import static com.future.base.constant.common.ResponseElement.INTERNAL_SERVER_ERROR;
import static com.future.base.util.base.ClassGetter.getClassesByPackage;
import static com.future.base.util.base.OriginalThrowableGetter.getOriginalThrowable;
import static com.future.base.util.base.ProChecker.isNotNull;
import static com.future.base.util.message.MessageProcessor.resolveToMessage;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

/**
 * global exp processor
 *
 * @author liuyunfei
 */
@SuppressWarnings({"SameParameterValue", "JavaDoc", "AliControlFlowStatementWithoutBraces", "PlaceholderCountMatchesArgumentCount"})
public final class ExceptionProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionProcessor.class);

    /**
     * handlers package
     */
    private static final String DIR_NAME = "com.future.base.component.exception.handler.impl";

    private static final Map<String, ExceptionHandler> MAPPING = generatorMapping(DIR_NAME);

    private static final ExceptionInfo UNKNOWN_EXP_HANDLE_INFO = new ExceptionInfo(INTERNAL_SERVER_ERROR.status,
            INTERNAL_SERVER_ERROR.code, null);

    /**
     * init mapping
     *
     * @param dirName
     * @return
     */
    private static Map<String, ExceptionHandler> generatorMapping(String dirName) {
        List<Class<?>> classes = getClassesByPackage(dirName, true);
        LOGGER.info("Map<String, ExceptionHandler> generatorMapping(String dirName), dirName = {}, classes = {}", dirName, classes);
        String expHandlerName = ExceptionHandler.class.getName();
        return classes
                .stream()
                .filter(clz ->
                        !clz.isInterface() &&
                                of(clz.getInterfaces()).anyMatch(inter -> expHandlerName.equals(inter.getName())))
                .map(clz -> {
                    try {
                        LOGGER.info("generatorMapping(String dirName), Load exception handler class, clz = {}", clz.getName());
                        return (ExceptionHandler) clz.getConstructor().newInstance();
                    } catch (Exception e) {
                        LOGGER.info("generatorMapping(String dirName), Load exception handler class failed, clz = {}, e = {}", clz.getName(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(toMap(ExceptionHandler::exceptionName, eh -> eh, (a, b) -> a));
    }

    private static final ExceptionElement DEFAULT_EXP_ELE = new ExceptionElement();

    private static final BiFunction<List<String>, ExceptionInfo, ExceptionElement> EXP_RES_GETTER = (languages, info) ->
            ofNullable(info)
                    .map(i -> {
                        Integer code = i.getCode();
                        return new ExceptionElement(i.getStatus(), code,
                                resolveToMessage(code, languages, info.getReplacements()));
                    }).orElse(DEFAULT_EXP_ELE);

    /**
     * process
     *
     * @param throwable
     * @return
     */
    public static ExceptionElement handle(Throwable throwable, List<String> languages) {
        LOGGER.info("ExceptionElement handle(Throwable throwable), throwable = {}", throwable);

        Throwable original = getOriginalThrowable(throwable);

        ExceptionHandler handler = MAPPING.get(original.getClass().getName());
        if (isNotNull(handler))
            try {
                return EXP_RES_GETTER.apply(languages, handler.handle(original));
            } catch (Exception e) {
                LOGGER.error("handle(Throwable throwable), Exception handling failed, throwable = {}, e = {}", throwable, e);
            }

        LOGGER.error("handle(Throwable throwable), unknown exception, throwable = {}", throwable);
        return EXP_RES_GETTER.apply(languages, UNKNOWN_EXP_HANDLE_INFO);
    }

}
