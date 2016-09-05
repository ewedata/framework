package com.framework.core.utils;

import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.framework.BaseTest;
import com.framework.entry.UserEntry;


public class TransferUtilTest extends BaseTest {

    @Test
    public void testTransferToCamelFormat() {}

    @Test
    public void testTransferToDbFormat() {}

    @Test
    public void testExtractPkName() {
        String pk = TransferUtil.extractPkName(UserEntry.class);
        Assert.assertEquals("check the method for getting pk", "ID", pk);
    }

    @Test
    public void testExtractColumnValue() {

        UserEntry u = new UserEntry();
        u.setUserName("zhangsan");
        u.setId(1L);

        List<Entry<String, String>> extractColumnValue = TransferUtil.extractColumnValue(u, true);

        for (Entry<String, String> entry : extractColumnValue) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

}
