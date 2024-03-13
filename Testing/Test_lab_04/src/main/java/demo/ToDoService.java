package demo;

import java.util.ArrayList;
import java.util.List;  

public interface ToDoService {  
   
    public List<String> getTodos(String user);

	public void deleteTodos(String todos);
 }   