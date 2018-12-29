package com.mengyunzhi.synthetical.service;

import com.mengyunzhi.synthetical.entity.Orders;
import com.mengyunzhi.synthetical.entity.User;
import com.mengyunzhi.synthetical.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> findOrdersRunningByUser(User user) {
        List<Orders> runningOrders = ordersRepository.findOrdersByOwnerAndOrderStatus(user,0);
        if(runningOrders.isEmpty())
            return ordersRepository.findOrdersByDriverAndOrderStatus(user,0);
        return runningOrders;
    }

    @Override
    public List<Orders> findOrdersCompletedByUser(User user) {
        List<Orders> completedOrders = ordersRepository.findOrdersByOwnerAndOrderStatus(user,1);
        if(completedOrders.isEmpty())
            return ordersRepository.findOrdersByDriverAndOrderStatus(user,1);
        return completedOrders;
    }
}
