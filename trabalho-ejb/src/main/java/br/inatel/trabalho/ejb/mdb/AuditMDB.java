package br.inatel.trabalho.ejb.mdb;

import java.io.StringReader;
import java.time.LocalDateTime;

import br.inatel.trabalho.ejb.service.AuditLocal;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName="destinationType", propertyValue="jakarta.jms.Queue"),
  @ActivationConfigProperty(propertyName="destination", propertyValue="java:/jms/queue/auditQueue")
})
public class AuditMDB implements MessageListener {

  @EJB
  private AuditLocal auditBean;

  @Override
  public void onMessage(Message m) {
    try {
      String txt = ((TextMessage)m).getText();
      JsonObject o = Json.createReader(new StringReader(txt)).readObject();
      String code = o.getString("recordCode");
      String op   = o.getString("operation");
      LocalDateTime ts = LocalDateTime.parse(o.getString("timestamp"));
      auditBean.record(code, op, ts);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
