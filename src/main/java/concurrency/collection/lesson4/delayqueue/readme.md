使用线程安全的、带有延迟元素的列表
==

DelayedQueue类是Java API提供的一种有趣的数据结构，并且你可以用在并发应用程序中。在这个类中，你可以存储带有激活日期的元素。方法返回或抽取队列的元素将忽略未到期的数据元素。它们对这些方法来说是看不见的。

为了获取这种行为，你想要存储到DelayedQueue类中的元素必须实现Delayed接口。这个接口允许你处理延迟对象，所以你将实现存储在DelayedQueue对象的激活日期，这个激活时期将作为对象的剩余时间，直到激活日期到来。这个接口强制实现以下两种方法：

    compareTo(Delayed o)：Delayed接口继承Comparable接口。如果执行这个方法的对象的延期小于作为参数传入的对象时，该方法返回一个小于0的值。如果执行这个方法的对象的延期大于作为参数传入的对象时，该方法返回一个大于0的值。如果这两个对象有相同的延期，该方法返回0。
    getDelay(TimeUnit unit)：该方法返回与此对象相关的剩余延迟时间，以给定的时间单位表示。TimeUnit类是一个枚举类，有以下常量：DAYS、HOURS、 MICROSECONDS、MILLISECONDS、 MINUTES、 NANOSECONDS 和 SECONDS。
    
    


##它是如何工作的…

在这个指南中，我们已实现Event类。这个类只有一个属性（表示事件的激活日期），实现了Delayed接口，所以，你可以在DelayedQueue类中存储Event对象。

getDelay()方法返回在实际日期和激活日期之间的纳秒数。这两个日期都是Date类的对象。你已使用getTime()方法返回一个被转换成毫秒的日期，你已转换那个值为作为参数接收的TimeUnit。DelayedQueue类使用纳秒工作，但这一点对于你来说是透明的。

对于compareTo()方法，如果执行这个方法的对象的延期小于作为参数传入的对象的延期，该方法返回小于0的值。如果执行这个方法的对象的延期大于作为参数传入的对象的延期，该方法返回大于0的值。如果这两个对象的延期相等，则返回0。

你同时实现了Task类。这个类有一个整数属性id。当一个Task对象被执行，它增加一个等于任务ID的秒数作为实际日期，这是被这个任务存储在DelayedQueue类的事件的激活日期。每个Task对象使用add()方法存储100个事件到队列中。

最后，在Main类的main()方法中，你已创建5个Task对象，并用相应的线程来执行它们。当这些线程完成它们的执行，你已使用poll()方法将所有元素写入到控制台。这个方法检索并删除队列的第一个元素。如果队列中没有任务到期的元素，这个方法返回null值。你调用poll()方法，并且如果它返回一个Event类，你增加计数器。当poll()方法返回null值时，你写入计数器的值到控制台，并且令线程睡眠半秒等待更多的激活事件。当你获取存储在队列中的500个事件，这个程序执行结束。


##不止这些…

DelayQueue类提供其他有趣方法，如下：

    clear()：这个方法删除队列中的所有元素。
    offer(E e)：E是代表用来参数化DelayQueue类的类。这个方法插入作为参数传入的元素到队列中。
    peek()：这个方法检索，但不删除队列的第一个元素。
    take()：这具方法检索并删除队列的第一个元素。如果队列中没有任何激活的元素，执行这个方法的线程将被阻塞，直到队列有一些激活的元素。
    
