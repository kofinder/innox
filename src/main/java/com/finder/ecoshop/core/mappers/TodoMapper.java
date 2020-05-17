package com.finder.ecoshop.core.mappers;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.finder.ecoshop.core.domain.TodoBean;
import com.finder.ecoshop.exception.BusinessException;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@Mapper
public interface TodoMapper {
	
	@Select("select * from todo limit #{offset}, #{limit}")
    List<TodoBean> findAllByUserDefinedWithLimit(final Long limit, final Long offset) throws BusinessException;
	
	@Select("select * from todo where todoSeq = #{todoSeq}")
	TodoBean getByTodoId(final Long todoSeq) throws BusinessException;
	
	@Insert("insert into todo(categorySeq, todoProgress, description) values(#{categorySeq}, #{todoProgress}, #{description})")
	@Options(useGeneratedKeys = true, keyProperty = "todoSeq", keyColumn = "todo_seq")
	int saveTodo(final TodoBean bean) throws SQLException;
	
	@Update("update todo set todoProgress=#{todoProgress}, description=#{description} where todoSeq =#{todoSeq}")
	int updateTodo(final TodoBean bean) throws SQLException;
	
	@Delete("delete from todo where todoSeq =#{todoSeq}")
	int deleteTodo(final Long todoSeq) throws SQLException;
}
