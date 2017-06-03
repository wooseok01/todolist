package kr.or.connect.todo.model;

import java.io.Serializable;
import java.sql.Date;

public class Todo implements Serializable{
	private int id;
	private String todo;
	private int completed;
	private Date date;
	
	public Todo(){
		this.date = new Date(new java.util.Date().getTime());
		this.completed = 0;
	}

	public Todo(int id, String todo, int completed, Date date) {
		super();
		this.id = id;
		this.todo = todo;
		this.completed = completed;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]\n";
	}
	
	
}
