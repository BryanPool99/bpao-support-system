package com.bpao.supportsystem.common.domain.util;

import java.util.Optional;

public class StringNormalizerUtil {
    /**
     * Normaliza un texto: quita espacios en los extremos y lo pasa a MAYÚSCULAS.
     */
    public static String normalizeToUpper(String text) {
        return Optional.ofNullable(text)
                .map(String::trim)
                .map(String::toUpperCase)
                .orElse(null);
    }

    /**
     * Normaliza un texto: quita espacios en los extremos y lo pasa a minusculas.
     */
    public static String normalizeToLower(String text) {
        return Optional.ofNullable(text)
                .map(String::trim)
                .map(String::toLowerCase)
                .orElse(null);
    }
}
