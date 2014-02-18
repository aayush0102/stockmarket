package execute;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



@RunWith(Parameterized.class)
public class MultiStore {
	
	String username;
	String password;
	String is_correct;
	
	public MultiStore( String username, String password, String is_correct){
		this.username=username;
		this.password=password;
		this.is_correct=is_correct;
		
	}
	
	@Parameters
	public static Collection<Object[]> getData(){
		
		Object[][] data = new Object[2][3];
		
		data[0][0]= "abcd";
		data[0][1]= "efgh";
		data[0][2]="Y";
		
		data[1][0]= "mnop";
		data[1][1]= "qrst";
		data[1][2]="Y";
		
		return Arrays.asList(data);
		
		
	}
	
	@Test
	public void testData(){
		
		System.out.println(username+"-------"+password+"--------"+is_correct);
		
	}

}
