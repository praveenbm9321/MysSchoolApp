package com.aikshika.common;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AdAstraApi {

	public void sms(String otp, String number) {

		try {
			//System.out.println("inside sms");
			String message = "The new generated OTP is " + otp
					+ ".Please do not share it with anyone.";
			String uname = "aikshikatr";
			String pass = "Aikshika";

			String link = "http://u.vsms.in/SendSMS/sendmsg.php?uname=" + URLEncoder.encode(uname, "UTF-8") + "&pass=" + URLEncoder.encode(pass, "UTF-8")
					+ "&send=SMINFO&dest=91" + URLEncoder.encode(number, "UTF-8") + "&msg=" + URLEncoder.encode(message, "UTF-8");
			
			//System.out.println(link);
			//System.out.println("before url");
			URL url = new URL(link);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			
			System.out.println(uc.getResponseMessage());
			uc.disconnect();
			//System.out.println("after sms sent");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	// sending to father phone number
		public void sendingDetailsToParent(String fatherMobileNumber,String fatherUserName, String fatherPassword,
				String stuUserName, String stuPassword) {
			

			try {
				System.out.println("inside sms");

				String message = "Thank you for registering with us."
						+ "Your Login Credentials are," + "Parent Id : " + fatherUserName
						+ ", Password : " + fatherPassword 
						+ ", Student Id: " + stuUserName + ", Password : " + stuPassword
						+ ". Please do not share it with anyone.";
				String uname = "aikshikatr";
				String pass = "Aikshika";

				String link = "http://u.vsms.in/SendSMS/sendmsg.php?uname=" + URLEncoder.encode(uname, "UTF-8") + "&pass="
						+ URLEncoder.encode(pass, "UTF-8") + "&send=SMINFO&dest=91"
						+ URLEncoder.encode(fatherMobileNumber, "UTF-8") + "&msg=" +  URLEncoder.encode(message, "UTF-8");

				// System.out.println(link);
				// System.out.println("before url");
				URL url = new URL(link);
				HttpURLConnection uc = (HttpURLConnection) url.openConnection();

				System.out.println(uc.getResponseMessage());
				uc.disconnect();
				// System.out.println("after sms sent");
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		public void sendingDetailsToTeacher(String mNum, String teachUserName, String teachPassword) {
			try {
				// System.out.println("inside sms");
				
				//For sending to teacher mobile number
				
				String message = "Thank you for registering with us."
						+ " Your Login Credentials are,"
						+ "Teacher Id:" + teachUserName+ ", Password:" + teachPassword + " Please do not share it with anyone.";
				String uname = "aikshikatr";
				String pass = "Aikshika";
				String link = "http://u.vsms.in/SendSMS/sendmsg.php?uname=" + URLDecoder.decode(uname, "UTF-8") + "&pass="
						+ URLDecoder.decode(pass, "UTF-8") + "&send=SMINFO&dest=91" + URLDecoder.decode(mNum, "UTF-8")
						+ "&msg=" + URLEncoder.encode(message, "UTF-8");

				System.out.println(link);
				// System.out.println("before url");
				URL url = new URL(link);
				HttpURLConnection uc = (HttpURLConnection) url.openConnection();

				System.out.println(uc.getResponseMessage());
				uc.disconnect();
				// System.out.println("after sms sent");
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
}