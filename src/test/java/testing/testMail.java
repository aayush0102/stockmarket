package testing;

import util.SendMail;

public class testMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("Sending mail");
		SendMail mail = new SendMail();
		try{
		mail.postMail(SendMail.emailList, SendMail.emailSubjectTxt, SendMail.emailMsgTxt, SendMail.emailFromAddress);
			
		}
		catch( Exception e){
			
			e.printStackTrace();
		}

	}

}
