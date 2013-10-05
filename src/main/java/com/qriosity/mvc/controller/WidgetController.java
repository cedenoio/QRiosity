package com.qriosity.mvc.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yoandy
 * @since 10/5/13
 */
@Controller
@RequestMapping("/wigdet")
@Scope
public class WidgetController {
    @Resource
    VendorWidgetService vendorWidgetService;

    @RequestMapping(value = "/info/vendorId/{vid}/itemId/{iid}", method = RequestMethod.GET)
    public Map<String, Object> VendorInfo(@PathVariable("vid") final String vendorId, @PathVariable("iid") final String itemId) {
        Map<String, Object> rval = new HashMap<String, Object>();

        return rval;
    }
}
