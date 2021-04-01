package sun.net.httpserver;

class WriteFinishedEvent extends Event {
   WriteFinishedEvent(ExchangeImpl t) {
      super(t);

      assert !t.writefinished;

      t.writefinished = true;
   }
}
