package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.domain.TodoBean;
import com.finder.ecoshop.exception.BusinessException;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public interface TodoService {
	List<TodoBean> getAllTodoByUserDefinedWithLimit(final Long limit, final Long offset) throws BusinessException;
	TodoBean getTodoById(final Long todoSeq) throws BusinessException;
	
	Long saveTodo(final TodoBean bean) throws BusinessException;
	int modifyTodo(final TodoBean bean) throws BusinessException;
	int removeTodo(final Long todoSeq) throws BusinessException;
}
