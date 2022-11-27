package edu.jlu.group17.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.mapper.ClientMapper;
import edu.jlu.group17.back.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
}
