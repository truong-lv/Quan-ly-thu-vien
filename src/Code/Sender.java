/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author n18dc
 */
public class Sender {
    private final String ACCOUNT_SID = "AC03518d4294fe2e36bd1c3aaa0a3a8e5c";
    private final String AUTH_TOKEN = "0d0fbd80285e736e0b6fc9d7b91a80ce";
    private final String FROM_NUMBER = "+19899997161";
    
    public void sendSms(String smsTo, String msg) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message.creator(new PhoneNumber("+84"+smsTo.substring(1, smsTo.length())), new PhoneNumber(FROM_NUMBER), msg)
                .create();
    }
}
