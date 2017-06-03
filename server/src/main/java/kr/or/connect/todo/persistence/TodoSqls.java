package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID = //id값을 통해서 todo의 값을 지우는 query문.
			"DELETE "
			+ "FROM todo "
			+ "WHERE id= :id";
	static final String SELECT_ALL = //todo-db에 있는 모든 todo-list를 호출하는 query문.
			"SELECT * "
			+ "FROM todo "
			+ "ORDER BY date DESC";
	static final String UPDATE_TODO_BY_ID = //todo-db에 있는 completed column을 update하는 query문.
			"UPDATE todo "
			+ "SET completed = :completed "
			+ "WHERE id = :id";
	static final String DELETE_COMPLETED_TODO_LIST = //todo-db에서 completed=1 인 값을 지우는 query문.
			"DELETE "
			+ "FROM todo "
			+ "WHERE completed = 1";
}
