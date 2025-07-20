## Enthuware - Test 5




photos/../beaches/./cal/a.txt
/beaches/cal/a.txt

nOTES - 21

23 - CREATE a test using the variable inside the method

25 - 40min





47 min


[//]: # (abstract non-sealed interface Value extends Cacheable{ })


4 - ok
7 - ok

13:

Stream bkStrm = List.of("AB").stream();
long count = bkStrm.peek((Stringn x)->x.getTitle()).count(); //DOES NOT COMPILE ?


16: OK


Runnable r = ()-> 5; //COMPILE ERROR?

21: OK

23: OK

CREATE a test using the variable inside the method

34: OK

Consumer x = (String msg)->{ System.out.println(msg); }; //DOES NOT COMPILE?

47: OK

super.processEvents(events.values()); //DOES NOT COMPILE?

----

Expected evaluation:

21 - marked
14 - wrong - 2
12 - wrong
+ 4 vacilos
+ 2 others
  total = 18
--------
50-18=32

Real evaluation:

3  - vacilo
12 - Marked
2 - Trick (24,41)
2 - others