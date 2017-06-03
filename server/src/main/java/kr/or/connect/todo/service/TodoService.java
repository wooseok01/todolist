package kr.or.connect.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	@Autowired
	private TodoDao todoDao;
	
	public TodoDao getTodoDao() {return todoDao;}
	public void setTodoDao(TodoDao todoDao) {this.todoDao = todoDao;}

	public TodoService(){}
	
	public TodoService(TodoDao todoDao) {
		super();
		this.todoDao = todoDao;
	}
	
	public List<Todo> getListAll() {
		return todoDao.getListAll();
	}
	
	public Integer insertTodo(Todo todo) {
		return todoDao.insertTodo(todo);
	}
	
	public Integer deleteTodo(Integer id) {
		
		return todoDao.deleteTodo(id);
	}
	
	public Integer updateTodo(Todo todo) {
		return todoDao.updateTodo(todo);
	}
	
	public Integer deleteCompletedList() {
		return todoDao.deleteCompletedList();
	}
	
	
}
