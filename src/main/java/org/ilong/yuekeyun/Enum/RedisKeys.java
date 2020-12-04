package org.ilong.yuekeyun.Enum;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-02 11:16
 */
public enum RedisKeys {
    CLIENT_TOKEN("CLIENT_TOKEN");
    private String value;

    RedisKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
