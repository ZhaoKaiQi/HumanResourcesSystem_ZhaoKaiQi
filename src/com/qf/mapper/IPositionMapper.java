package com.qf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qf.entity.Position;

/**
 * @ClassName: IPositionMapper
 * @Description: Position的映射文件接口
 * @author 赵凯琦
 * @date 2017-7-11 下午10:38:10
 */
public interface IPositionMapper {

	@Select("select * from position where posName=#{param1}")
	@ResultType(Position.class)
	List<Position> findPositionByName(String posName);

	@Select("select * from position order by posId DESC")
	@ResultType(Position.class)
	List<Position> queryAll();

	@Delete("delete from position where posId=#{param1}")
	void deletePositionById(int posId);

	@Select("select * from position where posId=#{param1}")
	Position findPositionById(int posId);

	@Update("update position set posName=#{posName},posRemark=#{posRemark} where posId=#{posId}")
	int updatePosition(Position position);

	@Insert("insert into position (posName,posRemark) values(#{posName},#{posRemark})")
	/* @Options(useGeneratedKeys = true, keyProperty = "id") */
	int add(Position position);
}
