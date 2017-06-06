package kr.or.connect.todo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoTest {
	
	private Logger logger = LoggerFactory.getLogger(TodoTest.class);
	
	@Autowired
	private TodoDao todoDao;
	
	/*
	 * Test를 위한 method로 현재 todoDao가 db에 제대로 연결되어 있는지를 확인하기 위한 method.
	 * Test 목적으로 db에 있는 모든 db-list를 받아와서 logger로 출력.
	 * */
	@Test
	public void getAllListTest(){
		List<Todo> testTodoList = todoDao.getListAll();
		logger.info(testTodoList.toString());
	}
	
	/*
	 * 임시로 만든 todo객체를 db에 넣는 과정에서 문제가 발생하는지를 확인해보기 위한 method. 
	 * */
	@Test
	public void insertTodoTest(){
		Todo todo = new Todo();
		todo.setTodo("insert at insertTodoTest!");
		
		logger.info("test todo insert!");
		todoDao.insertTodo(todo);
		getAllListTest();
	}
}
