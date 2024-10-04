package org.stotic.dev.com.utilities;

import org.stotic.dev.com.logger.ApiLogger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyUtility {

    private Properties properties;
    private String filePath;

    public PropertyUtility(String filePath) {
        ApiLogger.log.info("[In]");
        this.filePath = filePath;
        properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            // ファイル読み込みに失敗
            System.out.println(String.format("ファイルの読み込みに失敗しました。ファイル名:%s", filePath));
        }
        ApiLogger.log.info("[End]");
    }

    /**
     * プロパティ値を取得する
     *
     * @param key キー
     * @return 値
     */
    public String getProperty(final String key) {
        ApiLogger.log.debug(String.format("Read %s(key=%s)", filePath, key));
        return getProperty(key, "");
    }

    /**
     * プロパティ値を取得する
     *
     * @param key キー
     * @param defaultValue デフォルト値
     * @return キーが存在しない場合、デフォルト値
     *          存在する場合、値
     */
    public String getProperty(final String key, final String defaultValue) {
        ApiLogger.log.debug(String.format("Read %s(key=%s, defaultValue=%s)", filePath, key, defaultValue));
        return properties.getProperty(key, defaultValue);
    }
}
