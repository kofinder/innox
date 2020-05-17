package com.finder.ecoshop.core.domain;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public class TodoBean extends BaseBean {
	private static final long serialVersionUID = -3L;
	private Long todoSeq;
	private Long categorySeq;
	private Integer todoProgress;
	private String description;

	public Long getTodoSeq() {
		return todoSeq;
	}

	public void setTodoSeq(Long todoSeq) {
		this.todoSeq = todoSeq;
	}

	public Long getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(Long categorySeq) {
		this.categorySeq = categorySeq;
	}

	public Integer getTodoProgress() {
		return todoProgress;
	}

	public void setTodoProgress(Integer todoProgress) {
		this.todoProgress = todoProgress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
