package org.thisrc;

import org.junit.Test;

public class SelectorsTest {

    Selectors selectors = new Selectors();

    @Test
    public void test() {
        System.out.println(
                selectors.conditions()
                        .stream()
                        .filter(s -> s.test("AUIGFUHJDJ9u"))
                        .findFirst()
                        .map(val->val.value)
                        .orElse("nope" )
        );
    }

}