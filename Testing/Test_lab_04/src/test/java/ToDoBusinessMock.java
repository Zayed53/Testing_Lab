import demo.ToDoBusiness;
import demo.ToDoService;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



public class ToDoBusinessMock {
	@Mock
    ToDoService doService;
	@Test  
    public void testusing_Mocks() {  
          
//        ToDoService doService = mock(ToDoService.class);
        this.doService = mock(ToDoService.class);
        List<String> combinedlist = Arrays.asList(" Use Core Java ", " Use Spring Core ", " Use w3eHibernate ", " Use Spring MVC ");
        when(this.doService.getTodos("dummy")).thenReturn(combinedlist);
          
        ToDoBusiness business = new ToDoBusiness(doService);
      
        List<String> alltd = business.getTodosforHibernate("dummy");   
          
        System.out.println(alltd);  
        assertEquals(1, alltd.size()); 
}
	
	@Test   
    public void testList_get() {  
  
List mocklist = mock(List.class);  
  
when(mocklist.get(0)).thenReturn("Mockito");  
 
assertEquals("Mockito", mocklist.get(0));  
System.out.println(mocklist.get(0));  
}  
	
	@Test   
    public void testList_Returns_MultipleValues() {  
      
    List mocklist = mock(List.class);  
    when(mocklist.size()).thenReturn(1).thenReturn(2).thenReturn(3);  
      
    assertEquals(1, mocklist.size());   
    assertEquals(2, mocklist.size());  
    assertEquals(3, mocklist.size());  
      
    System.out.println(mocklist.size());   
    System.out.println(mocklist);  
      
    }  
	
	@Test  
    public void test() {  
          
        List spyArrayList = spy(ArrayList.class);  
        assertEquals(0, spyArrayList.size());  
  
                        // adding an item in the list  
        spyArrayList.add("Mockito");  
        assertEquals(1, spyArrayList.size());  
    }  
}