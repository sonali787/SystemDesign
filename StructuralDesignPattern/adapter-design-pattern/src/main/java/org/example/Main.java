package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
         //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
          CheckoutService checkoutService = new CheckoutService(new PayUGateway());
          checkoutService.checkout("1" , 18000);
          CheckoutService checkoutService1 = new CheckoutService(new RazorPayAdapter());
          checkoutService1.checkout("2" , 4000);

    }
}
