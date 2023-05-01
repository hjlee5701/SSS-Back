package com.example.demo.domain.order.controller;

import com.example.demo.domain.order.controller.form.OrderInfoRegisterForm;
import com.example.demo.domain.order.controller.response.OrderInfoListResponse;
import com.example.demo.domain.order.service.OrderInfoService;
import com.example.demo.domain.order.service.request.OrderStateModifyRequest;
import com.example.demo.domain.utility.TokenBasedController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderInfoController extends TokenBasedController {
    final private OrderInfoService orderInfoService;

    @PostMapping(value = "/register")
    public void orderRegister (@RequestBody OrderInfoRegisterForm orderForm, HttpServletRequest requestToken) {
        log.info("orderRegister()");
        Long memberId = findMemberId(requestToken);

        orderInfoService.orderRegister(memberId, orderForm);
    }

    @PostMapping("/modify/state")
    public Boolean orderStateUpdate(@RequestBody OrderStateModifyRequest stateRequest){
        log.info("orderState Update()");
        return orderInfoService.updateOrderState(stateRequest.getOrderId(), stateRequest.getOrderStateType());
    }

    @GetMapping("/list")
    public List<OrderInfoListResponse> orderInfoListResponses(HttpServletRequest requestToken){
        Long memberId = findMemberId(requestToken);
        return orderInfoService.orderInfoListResponse(memberId);
    }
}
