package sun.net.httpserver;

class Event {
   ExchangeImpl exchange;

   protected Event(ExchangeImpl t) {
      this.exchange = t;
   }
}
