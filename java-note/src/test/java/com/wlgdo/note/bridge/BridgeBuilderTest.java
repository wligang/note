package com.wlgdo.note.bridge;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/8/24 0:38
 */
public class BridgeBuilderTest {

    @Test
    public void save() {
        BridgeBuilder bridgeBuilder = new BridgeBuilder();
        bridgeBuilder.setUserInterface(new AuthorUserService());
        bridgeBuilder.save("作者:李");
        bridgeBuilder.setUserInterface(new HidoUserService());
        bridgeBuilder.save("平台：李");
    }
}