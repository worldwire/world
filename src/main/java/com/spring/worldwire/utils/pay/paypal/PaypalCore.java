package com.spring.worldwire.utils.pay.paypal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.worldwire.config.PaypalConfig;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaypalCore {

  private static Logger logger = LoggerFactory.getLogger(PaypalCore.class);

  public static APIContext apiContext = null;
  static {
    try {
      OAuthTokenCredential oAuthTokenCredential = new OAuthTokenCredential(
          PaypalConfig.PAYPAL_CLIENT_ID,
          PaypalConfig.PAYPAL_CLIENT_SECRET,
          PaypalConfig.paypalSdkConfig());
      apiContext = new APIContext(oAuthTokenCredential.getAccessToken());
      apiContext.setConfigurationMap(PaypalConfig.paypalSdkConfig());
    } catch (PayPalRESTException e) {
      logger.error("[paypal] 初始化异常 exception=",e);
    }
  }


  public static Payment createPayment(
      Double total,
      String currency,
      PaypalPaymentMethodEnum method,
      PayPalPaymentIntentEnum intent,
      String description
  ) throws PayPalRESTException{
      Amount amount = new Amount();
      amount.setCurrency(currency);
      amount.setTotal(String.format("%.2f", total));

      Transaction transaction = new Transaction();
      transaction.setDescription(description);
      transaction.setAmount(amount);

      List<Transaction> transactions = new ArrayList<>();
      transactions.add(transaction);

      Payer payer = new Payer();
      payer.setPaymentMethod(method.toString());

      Payment payment = new Payment();
      payment.setIntent(intent.toString());
      payment.setPayer(payer);
      payment.setTransactions(transactions);
      RedirectUrls redirectUrls = new RedirectUrls();
      redirectUrls.setCancelUrl(PaypalConfig.DOMAIN+PaypalConfig.CANAEL_URL);
      redirectUrls.setReturnUrl(PaypalConfig.DOMAIN+PaypalConfig.NOTIFY_URL);
      payment.setRedirectUrls(redirectUrls);

      return payment.create(apiContext);
  }


  public static Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
      Payment payment = new Payment();
      payment.setId(paymentId);
      PaymentExecution paymentExecute = new PaymentExecution();
      paymentExecute.setPayerId(payerId);
      return payment.execute(apiContext, paymentExecute);
  }


}
