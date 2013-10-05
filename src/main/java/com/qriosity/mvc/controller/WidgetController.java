package com.qriosity.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yoandy
 * @since 10/5/13
 */
@Controller("/widget")
public class WidgetController {

    @RequestMapping(value = "/info/vendorId/{vid}/itemId/{iid}", method = RequestMethod.GET)
    public String VendorInfo(@PathVariable("vid") final String vendorId, @PathVariable("iid") final String itemId) {


        return "";
    }
}
