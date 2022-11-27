package edu.jlu.group17.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.jlu.group17.back.entity.Client;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper extends BaseMapper<Client> {
}
