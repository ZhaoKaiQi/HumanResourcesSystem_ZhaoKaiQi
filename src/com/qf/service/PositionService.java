package com.qf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.Position;
import com.qf.mapper.IPositionMapper;

/**
 * @ClassName: PositionService
 * @Description: 职位的业务逻辑层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:39:51
 */
@Service
public class PositionService {
	// 注入PositionMapper
	@Autowired
	private IPositionMapper mapper;

	public List<Position> findPositionByName(String posName) {
		return mapper.findPositionByName(posName);
	}

	public List<Position> queryAll() {
		return mapper.queryAll();
	}

	public void deletePositionById(int posId) {
		if (posId <= 0 || "".equals(posId)) {
			throw new RuntimeException("所输入数据有问题...");
		} else {
			System.out.println("业务逻辑层执行到了" + posId);
			// User user=
			mapper.deletePositionById(posId);
		}
	}

	public Position findPositionById(int posId) {
		return mapper.findPositionById(posId);
	}

	public boolean updatePosition(Position position) {
		return mapper.updatePosition(position) > 0 ? true : false;
	}

	public boolean add(Position position) {
		return mapper.add(position) > 0 ? true : false;
	}
}
