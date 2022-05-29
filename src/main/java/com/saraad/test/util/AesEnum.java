package com.saraad.test.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * @author cuiguibin
 */

@NoArgsConstructor
@AllArgsConstructor
public enum AesEnum {
    AES_DEFAULT("qWERBKZltShFp8lX", "8865281205462399"),
    AES_ACCOUNT("Db84oY7RmD35uo4M", "5955807308184266"),
    AES_FRONT("Fk1lNBYUFGfZ0pBv", "4101251957346940"),
    AES_FRONT_PRO("Qf4nbdsUI87MiO0h", "2670943475102852");
    private String key;
    private String iv;

    public String getKey() {
        return key;
    }

    public String getIv() {
        return iv;
    }
}
