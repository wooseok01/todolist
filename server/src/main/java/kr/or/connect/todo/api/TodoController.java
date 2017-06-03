package kr.or.connect.todo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	
	public TodoService getTodoService() {return todoService;}
	public void setTodoService(TodoService todoService) {this.todoService = todoService;}

	/*
	 * url : localhost:8080/api/todos
	 * request-method : 'GET'
	 * return : List<Todo>
	 * description : 맨 처음 localhost:8080/으로 접근 시 db에 있는 모든 todo-list를 가져오기 위한 api.
	 * */
	@GetMapping
	public List<Todo> getListAll(){
		List<Todo> list = todoService.getListAll();
		return list;
	}
	
	/*
	 * url : localhost:8080/api/todos/deleteCompleted
	 * request-method : 'GET'
	 * return : Integer
	 * description : 완료된 todo목록을 지워주는 역할을 하는 api로써 db상에서 completed column이 1인 값을 db에서 삭제.
	 * */
	@GetMapping("/deleteCompleted")
	public Integer deleteCompletedList(){
		System.out.println("im in delete completed list function!");
		return todoService.deleteCompletedList();
	}
	
	/*
	 * url : localhost:8080/api/todos
	 * type : 'POST'
	 * parameter : 
	 * 		Todo -> FE에서 사용자가 입력한 todo의 String내용을 Todo객체에 바로 받기 위한 parameter.
	 * 				<FE에서 사용자가 보내는 변수는 todo값이 전부>
	 * return : Integer
	 * description : 사용자가 FE에서 입력한 todo의 내용을 db에 insert하기 위한 용도의 api.
	 * */
	@PutMapping()
	public Integer insertTodo(Todo todo){
		return todoService.insertTodo(todo);
	}
	
	/*
	 * url : localhost:8080/api/todos/id(Integer)
	 * type : 'DELETE'
	 * parameter : 
	 * 		id -> url뒤에 붙는 id를 id variable에 직접 할당하여 사용.
	 * return : Integer
	 * description : FE에서 x버튼을 누른 경우 해당 todo의 id값을 받아서 지우는 목적으로 만든 api.
	 * */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Integer deleteTodo(@PathVariable Integer id){
		return todoService.deleteTodo(id);
	}
	
	/*
	 * url : localhost:8080/api/todos
	 * type : 'POST'
	 * parameter : 
	 * 		Todo -> FE에서 사용자는 id값과 completed값을 보내주면 해당 값을 Todo객체에 넣어서 사용.
	 * return : Integer
	 * description : FE에서 사용자가 완료 체크, 미완료 체크를 한 경우 호출되는 api.
	 * */
	@PostMapping
	public Integer updateTodo(Todo todo){
		return todoService.updateTodo(todo);
	}
}
