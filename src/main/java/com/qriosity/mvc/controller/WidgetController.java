package com.qriosity.mvc.controller;

import java.util.List;
import javax.annotation.Resource;
import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.service.VendorWidgetService;

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
@RequestMapping("/widget")
@Scope
public class WidgetController {
    @Resource
    VendorWidgetService vendorWidgetService;

    @RequestMapping(value = "/vendor/{vendor}/itemId/{itemId}", method = RequestMethod.GET)
    public List<SharedJsonItem> VendorInfo(@PathVariable("vendor") final String vendor, @PathVariable("itemId") final String itemId) {
        return vendorWidgetService.getProductRelatedData(vendor, itemId);
    }
}
